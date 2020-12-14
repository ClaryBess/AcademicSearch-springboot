package com.example.demo.mapper;


import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Mapper
public interface UserMapper {
    //根据id获取用户
    @Select("select * from User where id=#{id}")
    public User getUserById(Integer id);

    //根据email获取用户
    @Select("select * from User where email=#{email}")
    public User getUserByEmail(String email);

    //根据用户名获取用户
    @Select("select * from User where name=#{name}")
    public  User getUserByName(String name);

    //添加用户
    @Insert("insert into User(id,name,organization,paperCount,index,email,pwd,info,role) values(#{id},#{name},#{organization},#{paperCount},#{index},#{email},#{pwd},#{info},#{role})")
    public User insertUser(User user);

    //根据id删除用户
    @Delete("delete from User where id=#{id}")
    public int deleteUserById(Integer id);
}
