package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @PostMapping("/user/regisiter")
    public CommonResult register(@RequestBody User user){
        //System.out.println("UserName="+user.getUserName());
        User user1=userService.getUserByEmail(user.getEmail());
        if(user1!=null){
            return new CommonResult(500,"email already exists!",null);
        }
        User user2=userService.getUserByName(user.getName());
        if(user2!=null){
            return new CommonResult(400,"username already exists!",null);
        }
        User result =userService.Register(user);
        System.out.println("R1:User="+user.toString());
        return new CommonResult(200,null,result);
    }

    @RequestMapping(value="/user/login",method = RequestMethod.POST)
    public CommonResult login(@RequestBody User user){
        User user1=userMapper.getUserByName(user.getName());
        //需要先判断user1 是否为null
        System.out.println("UserName="+user.getName());
        System.out.println("Password="+user.getPwd());
        System.out.println("Password2="+user1.getPwd());
        if(user1!=null&&user.getPwd().equals(user1.getPwd())){
            return new CommonResult(200,"success",user1);
        }
        else {
            return new CommonResult(500,"failure",null);
        }
    }



}
