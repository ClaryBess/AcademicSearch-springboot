package com.example.demo.service;

import com.example.demo.bean.CommonResult;
import com.example.demo.mapper.DirectoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryService {
    @Autowired
    DirectoryMapper directoryMapper;

    //新建收藏夹
    public CommonResult CreateDir(String name,Integer user){
        return new CommonResult(200,"新建成功",directoryMapper.CreateDirectory(user,name));
    }

    //删除收藏夹
    public int deleteDir(Integer Did){
        return directoryMapper.deleteDirectoryById(Did);
    }

    //展示用户的所有收藏夹
    public CommonResult ShowDirByUser(Integer user){
        return new CommonResult(200,"显示收藏夹",directoryMapper.getDirectoryByUser(user));
    }

    //删除收藏夹
    public int DeleteDirByDid(Integer Did){
        return directoryMapper.deleteDirectoryById(Did);
    }
}
