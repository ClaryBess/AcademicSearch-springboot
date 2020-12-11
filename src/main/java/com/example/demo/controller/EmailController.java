package com.example.demo.controller;

import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    EmailService emailService;

    //邮箱验证功能，返回base64形式的验证码
    @ResponseBody
    @RequestMapping("/send")
    public String send(@RequestParam("email") String email){
        return emailService.send(email);
    }
}
