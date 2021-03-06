package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    @Value("${file.upload.path}")
    private String filePath;
    @Autowired
    UserService userService;

    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    EmailService emailService;


    @PostMapping("/user/login")
    public CommonResult login(@RequestBody User user) {
        System.out.println(user.getName());
        if (user.getName()==null) {
            return new CommonResult(500,"empty",null);
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
        } catch (AuthenticationException e) {
            return new CommonResult(500,"wrong",null);
        }
        String name=(String) SecurityUtils.getSubject().getPrincipal();
        User user1=userService.getUserByName(name);
        return new CommonResult(200,"success",user1);
    }

    @PostMapping("/user/logout")
    public CommonResult logout(){
        return new CommonResult(200,"logout success",null);
    }

    @PostMapping("/user/register")
    public CommonResult register(@RequestParam("string1") String string1,@RequestParam("string2")String string2, @RequestBody User user){
        User user1=userService.getUserByEmail(user.getEmail());
        if(user1!=null){
            return new CommonResult(500,"email already exists!",null);
        }
        User user2=userService.getUserByName(user.getName());
        if(user2!=null){
            return new CommonResult(400,"username already exists!",null);
        }
        String base64 = Base64.encodeBase64URLSafeString(string1.getBytes());
        if (!base64.equals(string2)){
            return new CommonResult(300,"code error!",null);
        }
        user.setRole(1);
        user.setAvatar("/file/avatar.jpg");//默认头像
        User result =userService.Register(user);
        System.out.println("R1:User="+user.toString());
        return new CommonResult(200,null,result);
    }

    @PostMapping("/user/changePassword")
    public CommonResult changePassword(@RequestParam("id")Integer id,@RequestParam("string1") String string1,@RequestParam("string2") String string2){
        if (string1.equals(userService.getUserById(id).getPwd())){
            User user=userService.getUserById(id);
            user.setPwd(string2);
            userService.updateUserPwd(user);
            return new CommonResult(200,"success",userService.getUserById(id));
        }
        else
            return new CommonResult(500,"password error",null);
    }

    @RequestMapping("/user/save/{id}")
    public User materialPictureAndText(@PathVariable Integer id, HttpServletResponse response, @RequestParam(value="file", required=false) MultipartFile file){
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (StringUtils.isEmpty(file)) {
            return userService.getUserById(id);
        }

        String filename = file.getOriginalFilename();
        String path = filePath+"/file";
        File filepath = new File(path,filename);
        if(!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            file.transferTo(new File(path + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        User user=userService.getUserById(id);
        user.setAvatar("/file/"+filename);
        userService.updateAvatar(user);
        return userService.getUserById(id);
    }

    @PostMapping("/user/getUser")
    public User getUser(@RequestParam("id")Integer id){
        User user = userService.getUserById(id);
        return user;
    }

    @PostMapping("/user/info/change")
    public CommonResult infoChange(@RequestBody User user){
        userService.updateTrueName(user);
        userService.updateInfo(user);
        user=userService.getUserById(user.getId());
        return new CommonResult(200,null,user);
    }

    @PostMapping("/user/send2")
    public CommonResult send2(@RequestParam("email") String email){
        User user=userService.getUserByEmail(email);
        if (user==null)
            return new CommonResult(500,"email not found",null);
        return emailService.send(email);
    }

    @PostMapping("/user/verify")
    public CommonResult verify(@RequestParam("string1") String string1,@RequestParam("string2")String string2){
        String base64 = Base64.encodeBase64URLSafeString(string1.getBytes());
        if (!base64.equals(string2)){
            return new CommonResult(300,"code error!",null);
        }
        return new CommonResult(200,"verify success",null);
    }

    @PostMapping("/user/reset")
    public CommonResult reset(@RequestParam("pwd")String pwd,@RequestParam("email")String email){
        User user=userService.getUserByEmail(email);
        user.setPwd(pwd);
        userService.updateUserPwd(user);
        return new CommonResult(200,"success",null);
    }

    @PostMapping("/user/getName/{UserID}")
    public String getName(@PathVariable Integer UserID){
        return  userService.getUserById(UserID).getName();
    }
}
