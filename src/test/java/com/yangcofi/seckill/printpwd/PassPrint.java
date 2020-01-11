package com.yangcofi.seckill.printpwd;

import static com.yangcofi.Seckill.util.MD5Util.md5;

public class PassPrint {
    public static void main(String[] args) {
        String pwd = "12345678";
        String salt="1a2b3c4d";
        String str = ""+salt.charAt(0)+salt.charAt(2) + pwd +salt.charAt(4) + salt.charAt(5);

        String password = md5(str);
        String str2 = "" + salt.charAt(0) + salt.charAt(2) + password + salt.charAt(5) + salt.charAt(4);
        System.out.println(md5(str2));
    }
}
