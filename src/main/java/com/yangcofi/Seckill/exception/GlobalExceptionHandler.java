package com.yangcofi.Seckill.exception;


import com.yangcofi.Seckill.result.CodeMsg;
import com.yangcofi.Seckill.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**@author : YangCoFi
 * @Date:2019/06/26
 * 全局异常处理
 * */


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    //instanceof是Java的一个二元操作符，和==，>，<是同一类东东。由于它是由字母组成的，所以也是Java的保留关键字。它的作用是测试它左边的对象是否是它
    //右边的类的实例，返回boolean类型的数据。

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e){    //系统传过来的request和异常
        e.printStackTrace();
        if (e instanceof GlobalException){
            GlobalException ex = (GlobalException)e;
            return Result.error(ex.getCm());
        }

        if (e instanceof BindException){
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);          //为了方便我们这里就取得是第一个
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        }else {             //如果不是一个绑定异常 我们就返回一个通用的服务端异常
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }


}
