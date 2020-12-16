package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;


    @GetMapping("/user/login")
    public String login(User user) {
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getId())) {
            return "请输入用户名和密码！";
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getName(),
                user.getPwd()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            //验证是权限
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (UnknownAccountException e) {

            return "用户名不存在！";
        } catch (AuthenticationException e) {

            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            return "没有权限";
        }
        return "login success";
    }

    @GetMapping("/logout")
    public String logout(){
        return "返回退出页面";
    }

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

    @PostMapping("/user/getUser")
    public User getUser(){
        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        return user;
    }

    @PostMapping("/user/getName/{UserID}")
    public String getName(@PathVariable Integer UserID){
        return  userService.getUserById(UserID).getName();
    }
}
