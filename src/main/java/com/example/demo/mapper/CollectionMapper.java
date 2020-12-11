package com.example.demo.mapper;

import com.example.demo.bean.Collection;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectionMapper {
    @Select("select * from Collection where id=#{id}")
    public Collection getCollectionById(Integer id);

    @Select("select * from Collection where directory=#{directory} and paper=#{paper}")
    public Collection getCollectionByDirectoryAndPaper(Integer directory, Integer paper);

    @Select("select * from Collection where directory=#{directory}")
    public List<Collection> getCollectionByDirectory(Integer directory);

    @Insert("insert into Collection(directory,paper) values(#{directory},#{paper})")
    public int insertCollection(Collection collection);

    @Delete("delete from Collection id=#{id}")
    public int deleteCollectionById(Integer id);

    @Delete("delete from Collection directory=#{directory}")
    public int deleteCollectionByDirectory(Integer directory);

    @Delete("delete from Follow where  directory=#{directory} and paper=#{paper}")
    public int deleteCollectionByByDirectoryAndPaper(Integer directory, Integer paper);
}
