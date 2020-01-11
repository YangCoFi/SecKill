package com.yangcofi.Seckill.redis;

/**
 *做缓存的前缀接口
 */

//在interface中所有的方法都是public abstract的，即使你没有申明它是public abstract的。在interface中所有的数据成员都是public static final的，即使你没有申明.
public interface KeyPrefix {
    //有效期
    public int expireSeconds();
    //前缀
    public String getPrefix();
}


