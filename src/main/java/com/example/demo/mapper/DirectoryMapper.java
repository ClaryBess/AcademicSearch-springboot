package com.example.demo.mapper;

import com.example.demo.bean.Directory;
import com.example.demo.bean.Follow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DirectoryMapper {
    @Select("select * from Directory where id=#{id}")
    public Directory getDirectoryById(Integer id);

    @Select("select * from Follow where user=#{user} and name=#{name}")
    public Directory getDirectoryByUserAndName(Integer user, char name);

    @Select("select * from Follow where user=#{user}")
    public List<Directory> getDirectoryByUser(Integer user);

    @Insert("insert into Directory(user,name) values(#{user},#{name})")
    public int insertDirectory(Directory directory);

    @Delete("delete from Directory id=#{id}")
    public int deleteDirectoryById(Integer id);

    @Delete("delete from Directory user=#{user}")
    public int deleteDirectoryByUser(Integer user);

    @Delete("delete from Directory where  user=#{user} and name=#{name}")
    public int deleteDirectoryByUserAndName(Integer user, char name);
}
