package com.yangcofi.Seckill.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    //对明文字符串做一个MD5
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }


    //用户端固定的salt 用于和用户输入的密码做一次拼装。
    private static final String salt = "1a2b3c4d";

    //两次MD5
    //把用户输入的密码进行MD5
    public static String inputPassToFormPass(String inputPass){
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    //存入数据库里面的 我们用一个随机的salt
    public static String formPassToDBPass(String formPass, String salt){
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass, String saltDB){
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass,saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));
        System.out.println(formPassToDBPass(inputPassToFormPass("123456"),"1a2b3c4d"));
        System.out.println(inputPassToDBPass("123456","1a2b3c4d"));
    }
}
