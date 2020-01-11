package com.yangcofi.Seckill.controller;


import com.yangcofi.Seckill.domain.SeckillUser;
import com.yangcofi.Seckill.redis.RedisService;
import com.yangcofi.Seckill.service.SeckillUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoodsController {

    @Autowired
    SeckillUserService seckillUserService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_list")
    public String to_list(Model model, SeckillUser seckillUser){
//                          @CookieValue(value = SeckillUserService.COOKIE_NAME_TOKEN,required = false) String cookieToken,
//                          @RequestParam(value = SeckillUserService.COOKIE_NAME_TOKEN,required = false) String paraToken,
//                          HttpServletResponse httpServletResponse){
//        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paraToken)){
//            return "login";
//        }
//        String token = StringUtils.isEmpty(paraToken)?cookieToken:paraToken;
//        SeckillUser seckillUser = seckillUserService.getByToken(token, httpServletResponse);
        model.addAttribute("seckillUser",seckillUser);
        return "goods_list";
    }

//    @RequestMapping("/to_detail")
//    //在商品的详情页里面有时候也需要这个用户的信息，比如用户是否已经登录进来了
//    public String detail(HttpServletResponse response, Model model,
//                         @CookieValue(value = SeckillUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
//                         @RequestParam(value = SeckillUserService.COOKIE_NAME_TOKEN, required = false) String paramToken){
//
//    }

}
