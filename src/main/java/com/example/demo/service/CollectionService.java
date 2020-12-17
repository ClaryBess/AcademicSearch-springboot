package com.example.demo.service;

import com.example.demo.bean.Collection;
import com.example.demo.mapper.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {
    @Autowired
    CollectionMapper collectionMapper;

    //添加收藏
    public int insertCollection(Integer Did,long Pid,Integer user){
        return collectionMapper.insertCollection(Did,Pid,user);
    }

    //在文献页面取消收藏
    public int DeleteCollectionInPaper(Integer user, long paper){
        return collectionMapper.deleteCollectionInPaper(paper,user);
    }

    //在收藏页面取消收藏
    public int DeleteCollectionInDir(Integer Cid){
        return collectionMapper.deleteCollectionById(Cid);
    }

    //展示收藏夹中的所有收藏
    public List<Collection> ShowCollectionByDir(Integer Did){
        return collectionMapper.getCollectionByDirectory(Did);
    }

    //删除某收藏夹里的所有收藏
    public int DeleteCollectionByDir(Integer Did){
        return collectionMapper.deleteCollectionByDir(Did);
    }

    //展示某文献的收藏状态
    public int ShowCollectionStatus(long paper,Integer user){
        if (collectionMapper.CheckCollectionStatus(paper,user)==null)
            return 0;
        else
            return 1;
    }
}
