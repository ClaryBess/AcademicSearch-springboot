package com.example.demo.mapper;


import com.example.demo.bean.User;
import org.apache.ibatis.annotations.*;
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
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into User(name,email,pwd,role,avatar) values(#{name},#{email},#{pwd},#{role},#{avatar})")
    public int insertUser(User user);

    //根据id删除用户
    @Delete("delete from User where id=#{id}")
    public int deleteUserById(Integer id);

    //更新密码
    @Update("update User set pwd=#{pwd} where id=#{id}")
    public int updatePwd(User user);

    //更新真实姓名
    @Update("update User set trueName=#{trueName} where id=#{id}")
    public int updateTrueName(User user);

    //更新个人简介
    @Update("update User set info=#{info} where id=#{id}")
    public int updateInfo(User user);

    //更新头像
    @Update("update User set avatar=#{avatar} where id=#{id}")
    public int updateAvatar(User user);

    //更新权限
    @Update("update User set role=#{role} where id=#{id}")
    public int updateRole(User user);

    //更新学者id
    @Update("update User set researcherId=#{researcherId} where id=#{id}")
    public int updateResearcherId(User user);
}
