package com.yangcofi.Seckill.service;


import com.yangcofi.Seckill.dao.SecKillUserDao;
import com.yangcofi.Seckill.domain.SeckillUser;
import com.yangcofi.Seckill.exception.GlobalException;
import com.yangcofi.Seckill.redis.RedisService;
import com.yangcofi.Seckill.redis.SeckillUserKey;
import com.yangcofi.Seckill.result.CodeMsg;
import com.yangcofi.Seckill.util.MD5Util;
import com.yangcofi.Seckill.util.UUIDUtil;
import com.yangcofi.Seckill.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class SeckillUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    SecKillUserDao secKillUserDao;

    @Autowired
    RedisService redisService;      //把我们的私人信息存入到第三方的缓存当中


    public SeckillUser getById(long id){
        return secKillUserDao.getById(id);
    }

    public Boolean login(HttpServletResponse httpServletResponse, LoginVo loginVo) {     //返回一个真正代表业务的东西 而不是一个CodeMsg
        if (loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        SeckillUser seckillUser = getById(Long.parseLong(mobile));
        if (seckillUser == null){
            throw new GlobalException(CodeMsg.MOBILE_NOTEXIST);
        }
        //验证密码
        String dbPass = seckillUser.getPwd();
        String saltDB = seckillUser.getSalt();
        String clacPass = MD5Util.formPassToDBPass(formPass,saltDB);      //这个就是我们计算出来的PASS
        //判断我们计算出来的pass和数据库里的是否一样
        if (!clacPass.equals(dbPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);      //抛出去 异常处理器会去处理
        }
        String token = UUIDUtil.uuid();
        addCookie(seckillUser, token, httpServletResponse);
        return true;
    }

    private void addCookie(SeckillUser seckillUser, String token, HttpServletResponse httpServletResponse){
        //生成cookie
//        String token = UUIDUtil.uuid();
        redisService.set(SeckillUserKey.token,token,seckillUser);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(SeckillUserKey.token.expireSeconds());
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);      //这样就把cookie写到我们的客户端里面去了
    }

    public SeckillUser getByToken(String token, HttpServletResponse httpServletResponse) {
        if (StringUtils.isEmpty(token)){
            return null;
        }
        SeckillUser user = redisService.get(SeckillUserKey.token,token,SeckillUser.class);
        //延长有效期
        //1是重新往缓存里面设置一下这个值；2是生成一个新的cookie
        if (user != null){
            addCookie(user, token, httpServletResponse);
        }
        return user;
    }
}
