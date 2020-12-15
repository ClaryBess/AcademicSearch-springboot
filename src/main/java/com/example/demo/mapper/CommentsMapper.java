package com.example.demo.mapper;

import com.example.demo.bean.Comments;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Repository
public interface CommentsMapper extends BaseMapper<Comments> {
    //根据id获取评论
    @Select("select * from Comments where id=#id}")
    public Comments getCommentsByrId(Integer Id);

    //根据文章id获取评论信息
    @Select("select * from Comments where paperId=#{paperId}")
    public Comments getCommentsByPaperId(Integer paperId);

    //根据用户获取评论
    @Select("select * from Comments where Commentator=#{Commentator}")
    public Comments getCommentsByCommentator(Integer Commentator);

    //发布评论
    @Insert("insert into Comments(id,content,paperId,commentator,commentatorName,commentTime) values(#{id},#{content},#{paperId},#{commentator},#{commentatorName},#{commentTime)")
    public int insertComments(Comments comments);

    //删除评论根据id
    @Delete("delete from Comments id=#{id}")
    public int deleteCommentsById(Integer id);

}
