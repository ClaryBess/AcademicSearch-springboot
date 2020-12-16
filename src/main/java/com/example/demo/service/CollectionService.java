package com.example.demo.service;

import com.example.demo.bean.CommonResult;
import com.example.demo.mapper.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {
    @Autowired
    CollectionMapper collectionMapper;

    //添加收藏
    public CommonResult insertCollection(Integer Did,long Pid,Integer user){
        return new CommonResult(200,"收藏成功",collectionMapper.insertCollection(Did,Pid,user));
    }

    //在文献页面取消收藏
    public CommonResult DeleteCollectionInPaper(Integer user, long paper){
        return new CommonResult(200,"取消收藏成功",collectionMapper.deleteCollectionInPaper(paper,user));
    }

    //在收藏页面取消收藏
    public CommonResult DeleteCollectionInDir(Integer Cid){
        return new CommonResult(200,"取消收藏成功",collectionMapper.deleteCollectionById(Cid));
    }

    //展示收藏夹中的所有收藏
    public CommonResult ShowCollectionByDir(Integer Did){
        return new CommonResult(200,"展示收藏",collectionMapper.getCollectionByDirectory(Did));
    }

    //删除某收藏夹里的所有收藏
    public CommonResult DeleteCollectionByDir(Integer Did){
        return new CommonResult(200,"删除成功",collectionMapper.deleteCollectionByDir(Did));
    }

    //展示某文献的收藏状态
    public int ShowCollectionStatus(long paper,Integer user){
        if (collectionMapper.CheckCollectionStatus(paper,user)==null)
            return 0;
        else
            return 1;
    }
}
