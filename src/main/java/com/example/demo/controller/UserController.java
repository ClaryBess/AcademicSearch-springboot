package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @PostMapping("/user/register")
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

        user.setRole(1);
        User result =userService.Register(user);
        System.out.println("R1:User="+user.toString());
        return new CommonResult(200,null,result);
    }

    @RequestMapping(value="/user/login",method = RequestMethod.POST)
    public CommonResult login(@RequestBody User user){
        Subject currentUser= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getName(),user.getPwd());
        try{
            currentUser.login(token);
            if(currentUser.isAuthenticated())
                return new CommonResult(200,"success",userMapper.getUserByName(user.getName()));
        }catch (AuthenticationException e){
            e.printStackTrace();
            System.out.println("login error");
        }
        return new CommonResult(500,"failure",null);
    }

    @RequestMapping("/user/quit")
    public CommonResult quit(@RequestBody User user){
        return new CommonResult(200,"success",null);
    }

    @PostMapping("/user/getUser")
    public User getUser(@RequestBody Integer UserID){
        return userService.getUserById(UserID);
    }

    @PostMapping("/user/getUserByName")
    public User getUserByName(@RequestBody String UserName){
        return userService.getUserByName(UserName);
    }

    @PostMapping("/user/getName/{UserID}")
    public String getName(@PathVariable Integer UserID){
        return  userService.getUserById(UserID).getName();
    }
}
