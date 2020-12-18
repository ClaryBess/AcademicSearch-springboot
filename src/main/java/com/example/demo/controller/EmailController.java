package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    EmailService emailService;

    //邮箱验证功能，返回base64形式的验证码
    @PostMapping("/send")
    public CommonResult send(@RequestParam("email") String email){
        return emailService.send(email);
    }
}
