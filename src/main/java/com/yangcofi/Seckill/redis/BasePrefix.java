package com.yangcofi.Seckill.redis;

import com.yangcofi.Seckill.redis.KeyPrefix;

//定义成抽象类
//抽象类可以有构造方法，只是不能直接创建抽象类的实例对象而已。
//在继承了抽象类的子类中通过super(参数列表)调用抽象类中的构造方法

//BasePrefix 抽象类：简单的实现一下KeyPrefix，定义成抽象类原因，防止不小心被创建，我们不希望BasePrefix被实例化，因为抽象类不允许实例化。我们只希望它被继承。不同模块的前缀类都继承他。
public abstract class BasePrefix implements KeyPrefix {
    private int expireSeconds;
    private String prefix;
    public BasePrefix() {
    }
    public BasePrefix(String prefix) {
        //this(0, prefix);//默认使用0，不会过期
        this.expireSeconds=0;
        this.prefix=prefix;
    }
    public BasePrefix(int expireSeconds,String prefix) {//覆盖了默认的构造函数
        this.expireSeconds=expireSeconds;
        this.prefix=prefix;
    }
    //默认为0代表永不过期
    //当一个方法被定义为抽象方法时，意味着这个类要被子类重写
    public int expireSeconds() {
        return expireSeconds;
    }

    //前缀为类名:+prefix
    public String getPrefix() {
        String className=getClass().getSimpleName();
        return className+":"+prefix;
    }
}
