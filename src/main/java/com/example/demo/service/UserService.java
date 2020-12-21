package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User Register(User user){
        User user1 = userMapper.getUserByName(user.getName());
        User user2 = userMapper.getUserByEmail(user.getEmail());
        if(user1==null && user2==null){
            userMapper.insertUser(user);
            //return userMapper.getUserById(user.getId());
           return userMapper.getUserByName(user.getName());
        }
        else
            return null;
    }

    //用id搜索用户
    public User getUserById(Integer id) {
        User newUser = userMapper.getUserById(id);
        return newUser;
    }

    //用email搜索用户
    public User getUserByEmail(String email){
        User user =userMapper.getUserByEmail(email);
        return user;
    }

    //用用户名搜索用户
    public User getUserByName(String name){
        User user=userMapper.getUserByName(name);
        return user;
    }

    //删除用户
    public int deleteUser(Integer id) {
        return userMapper.deleteUserById(id);
    }

    //更新密码
    public int updateUserPwd(User user) {

        return userMapper.updatePwd(user);

    }

    //更新真实姓名
    public int updateTrueName(User user){
        return userMapper.updateTrueName(user);
    }

    //更新个人简介
    public int updateInfo(User user){
        return userMapper.updateInfo(user);
    }

    //更新头像
    public int updateAvatar(User user){
        return userMapper.updateAvatar(user);
    }

    //将用户设置为学者
    public int setResearcher(User user){
        user.setRole(2);
        return userMapper.updateRole(user);
    }

    public int updateResearcherId(User user){
        return userMapper.updateResearcherId(user);
    }
}
