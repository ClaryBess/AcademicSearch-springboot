package com.example.demo.service;

import com.example.demo.bean.Collection;
import com.example.demo.bean.Directory;
import com.example.demo.mapper.DirectoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryService {
    @Autowired
    DirectoryMapper directoryMapper;
    //新建收藏夹
    public Directory insertDirectory(Directory directory){
        Directory directory1=directoryMapper.getDirectoryByUserAndName(directory.getUser(),directory.getName());
        if(directory1!=null)
            return directory1;
        directoryMapper.insertDirectory(directory);
        return directoryMapper.getDirectoryById(directory.getId());
    }

    //取消关注
    public int deleteByUserAndName(Integer user, char name){
        return directoryMapper.deleteDirectoryByUserAndName(user, name);
    }

    //根据用户和名字获取收藏夹
    public Directory getDirectoryByUserAndName(Integer user, char name){
        return directoryMapper.getDirectoryByUserAndName(user, name);
    }

    //根据id获取收藏夹
    public Directory getDirectoryById(Integer id){
        return directoryMapper.getDirectoryById(id);
    }

    //获取用户的全部收藏夹
    public List<Directory> getDirectoryByUser(Integer user){
        return directoryMapper.getDirectoryByUser(user);
    }
}
