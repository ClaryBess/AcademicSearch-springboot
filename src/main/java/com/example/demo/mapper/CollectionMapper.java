package com.example.demo.mapper;

import com.example.demo.bean.Collection;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollectionMapper {
    //展示一个收藏夹里面的所有收藏
    @Select("select * from Collection where directory=#{directory} order by id desc")
    public List<Collection> getCollectionByDirectory(Integer directory);

    //在一个收藏夹里添加一个新的收藏
    @Insert("insert into Collection values(#{directory},#{paper},DEFAULT,#{user})")
    public int insertCollection(Integer direction,long paper,Integer user);

    //删除一个收藏
    @Delete("delete from Collection where id=#{id}")
    public int deleteCollectionById(Integer id);

    //删除一个收藏夹里面的所有收藏
    @Delete("delete from Collection directory=#{directory}")
    public int deleteCollectionByDir(Integer directory);

    //查找一个用户对某文献的收藏情况
    @Select("select * from Collection where paper={paper} and user={user}")
    public Collection CheckCollectionStatus(long paper,Integer user);

    //直接在文献界面取消收藏
    @Delete("delete from Follow where paper={paper} and user={user}")
    public int deleteCollectionInPaper(long paper,Integer user);

}
