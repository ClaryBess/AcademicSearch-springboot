package com.example.demo.service;

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
    public int CreateDir(String name,Integer user){
        return directoryMapper.CreateDirectory(user,name);
    }

    //展示用户的所有收藏夹
    public List<Directory> ShowDirByUser(Integer user){
        return directoryMapper.getDirectoryByUser(user);
    }

    //删除收藏夹
    public int DeleteDirByDid(Integer Did){
        return directoryMapper.deleteDirectoryById(Did);
    }
}
