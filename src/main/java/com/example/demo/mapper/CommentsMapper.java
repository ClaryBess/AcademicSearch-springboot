package com.example.demo.mapper;

import com.example.demo.bean.Comments;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

@Repository
public interface CommentsMapper extends BaseMapper<Comments> {
    //根据id获取评论
    @Select("select * from Comments where id=#{id}")
    public Comments getCommentsByrId(Integer Id);

    //根据文章id获取评论信息
    @Select("select * from Comments where paperId=#{paperId}")
    public List<Comments> getCommentsByPaperId(String paperId);

    //根据用户获取评论
    @Select("select * from Comments where Commentator=#{Commentator}")
    public List<Comments> getCommentsByCommentator(Integer Commentator);

    //发布评论

    @Insert("insert into Comments(content,Commentator,paperId,commentatorName,commentTime) values(#{content},#{Commentator},#{paperId},#{commentatorName},#{commentTime})")
    public int insertComments(Comments comments);

    //删除评论根据id
    @Delete("delete from Comments where id=#{id}")
    public int deleteCommentsById(Integer id);

}
