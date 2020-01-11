package com.yangcofi.Seckill.redis;

public class SeckillUserKey extends BasePrefix{
    public static final int TOKEN_EXPIRE=3600*24*2;//3600S*24*2    =2天
    public SeckillUserKey(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }
    public static SeckillUserKey token=new SeckillUserKey(TOKEN_EXPIRE,"tk");
    //对象缓存一般没有有效期，永久有效
    public static SeckillUserKey getById=new SeckillUserKey(0,"id");
}
