package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    //添加用户
    public User addUser(Integer id, String name, String organization, String email, Integer paperCount, Integer index, String pwd, String info, Integer role) {
        User newUser = userMapper.insertUser(new User(id, name, organization, email, paperCount, index, pwd, info, role));
        return newUser;
    }

    //用id搜索用户
    public User getUserById(Integer id) {
        User newUser = userMapper.getUserById(id);
        return newUser;
    }

    //删除用户
    public int deleteUser(Integer id) {
        int a = userMapper.deleteUserById(id);
        return a;
    }
}
