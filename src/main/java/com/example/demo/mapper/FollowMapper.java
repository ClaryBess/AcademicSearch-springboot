package com.example.demo.mapper;

import com.example.demo.bean.Follow;
import com.example.demo.bean.Researcher;
import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FollowMapper {

    @Select("select * from Follow where id=#{id}")
    public Follow getFollowById(Integer id);

    @Select("select * from Follow where user=#{user} and researcher=#{researcher}")
    public Follow getFollowByUserAndResearcher(Integer user, String researcher);

    @Select("select * from Follow where user=#{user}")
    public List<Follow> getFollowByUser(Integer user);

    @Select("select * from Follow where researcher=#{researcher}")
    public List<Follow> getFollowByResearcher(Integer researcher);

    @Insert("insert into Follow(user,researcher) values(#{user},#{researcher})")
    public int insertFollow(Follow follow);

    @Delete("delete from Follow id=#{id}")
    public int deleteFollowById(Integer id);

    @Delete("delete from Follow user=#{user}")
    public int deleteFollowByUser(Integer user);

    @Delete("delete from Follow where researcher=#{researcher}")
    public int deleteFollowByResearcher(String researcher);

    @Delete("delete from Follow where  user=#{user} and researcher=#{researcher}")
    public int deleteFollowByUserAndResearcher(Integer user, String researcher);
}
