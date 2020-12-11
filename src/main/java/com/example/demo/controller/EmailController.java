package com.example.demo.controller;

import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    EmailService emailService;

    //发送验证码，返回base64格式的验证码
    @RequestMapping("/send")
    public String send(){
        return emailService.send();
    }
}
