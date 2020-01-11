package com.yangcofi.Seckill.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YangCoFi
 * Pattern类用于创建一个正则表达式，也可以说是创建一个匹配模式，可以通过两个静态方法创建：compile(String regex)和
 * compile(String regex,int flags)，其中regex是正则表达式，flags为可选模式(如：Pattern.CASE_INSENSITIVE 忽略大小写)
 *
 * */

public class ValidatorUtil {
    //正则表达式
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");
    public static boolean isMobile(String src){
        if (StringUtils.isEmpty(src)){
            return false;
        }
        //Matcher类提供了对正则表达式的分组支持,以及对正则表达式的多次匹配支持，要想得到更丰富的正则匹配操作,那就需要将Pattern与Matcher联合使用。
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobile("18912341234"));
        System.out.println(isMobile("1891234123"));
    }
}




/**
 * Pattern pattern = Pattern.compile("Java");
 * String test="123Java456Java789Java";
 * String[] result = pattern.split(test);
 * for(String s : result)
 *  System.out.println(s);
 *
 *  Result:
 *  123
 *  456
 *  789
 *
 *
 *  https://blog.csdn.net/weixin_42868638/article/details/82721489
 *  Pattern和Matcher类详解
 *  */