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
    public Collection insertCollection(Collection collection){
        Collection collection1=collectionMapper.getCollectionByDirectoryAndPaper(collection.getDirectory(),collection.getPaper());
        if(collection1!=null)
            return collection1;
        collectionMapper.insertCollection(collection);
        return collectionMapper.getCollectionById(collection.getId());
    }

    //取消收藏
    public int deleteCollectionByByDirectoryAndPaper(Integer directory, Integer paper){
        return collectionMapper.deleteCollectionByByDirectoryAndPaper(directory,paper);
    }

    //根据收藏夹和文献获取收藏
    public Collection getCollectionByDirectoryAndPaper(Integer directory, Integer paper){
        return collectionMapper.getCollectionByDirectoryAndPaper(directory, paper);
    }

    //根据id获取收藏
    public Collection getCollectionById(Integer id){
        return collectionMapper.getCollectionById(id);
    }

    //获取收藏夹的全部收藏夹
    public List<Collection> getCollectionByDirection(Integer directory){
        return collectionMapper.getCollectionByDirectory(directory);
    }
}
