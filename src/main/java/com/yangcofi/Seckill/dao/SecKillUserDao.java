package com.yangcofi.Seckill.dao;

import com.yangcofi.Seckill.domain.SeckillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * domain类是我们用于与数据库映射的实体类，通常在将实体数据序列化发送到客户端时，我们不会吧domain类去序列化，
 * 而是将domain类转成一个model，将model序列化作为响应给浏览器的数据。
 * 当我们每次需要处理一个Chapter对象时，都需要创建一个model，将chapter里的数据对应塞进model里，抽成方法就是：entityToModel，而@Mapper就是这个作用，它只需要你去创建一个接口或抽象类(ChapterMapper)，然后定义这个entityToModel方法（并不需要去实现），因为它会自动创建继承类(ChapterMapperImpl)，并实现这个方法（entityToModel）。事实上，实现方法的内部同样是调用简单set/get，但这为我们省了不少时间。
 * */

//添加了@Mapper注解之后这个接口在编译时会生成相应的实现类
//@Mapper就是为了生成bean

@Mapper
public interface SecKillUserDao {
    @Select("select * from seckill_user where id = #{id}")
    public SeckillUser getById(@Param("id") long id);        //根据手机号进行获取

}


/**
 * @MapperScan
 * 作用：指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类
 * 添加位置：是在Springboot启动类上面添加，
 * mybatis支持的映射方式有基于xml的mapper.xml文件、基于java的使用Mapper接口class
 *
 *
 * https://blog.csdn.net/u010376788/article/details/77512992
 * MyBatis学习笔记
 * */