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
    @Select("select * from User where id=#{id}")
    public User getUserById(Integer id);

    @Insert("insert into User(id,name,organization,paperCount,index,email,pwd,info,role) values(#{id},#{name},#{organization},#{paperCount},#{index},#{email},#{pwd},#{info},#{role})")
    public User insertUser(User user);

    @Delete("delete from User where id=#{id}")
    public int deleteUserById(Integer id);
}
