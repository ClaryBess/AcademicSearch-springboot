package com.example.demo.mapper;

import com.example.demo.bean.Directory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DirectoryMapper {
    //展示某id的目录名称和所属用户
    @Select("select * from Directory where id=#{id}")
    public Directory getDirectoryById(Integer id);

    //展示某用户的所有收藏夹
    @Select("select * from Directory where user=#{user} order by name")
    public List<Directory> getDirectoryByUser(Integer user);

    //创建一个新的收藏夹
    @Insert("insert into Directory values(DEFAULT,#{name},#{user})")
    public int CreateDirectory(Integer user, String name);

    //删除收藏夹
    @Delete("delete from Directory where id=#{id}")
    public int deleteDirectoryById(Integer id);

    //删除某用户的所有收藏夹
    @Delete("delete from Directory where user=#{user}")
    public int deleteDirectoryByUser(Integer user);

    //删除某用户的名为name的收藏夹
    @Delete("delete from Directory where user=#{user} and name=#{name}")
    public int deleteDirectoryByUserAndName(Integer user, String name);
}
