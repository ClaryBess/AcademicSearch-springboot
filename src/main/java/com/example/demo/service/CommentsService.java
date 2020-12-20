package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.DTO.CommentDTO;
import com.example.demo.bean.Comments;
import com.example.demo.bean.User;
import com.example.demo.mapper.CommentsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    UserService userService;

    @Autowired
    PaperService paperService;


    //添加评论
    public Comments insertComments(Long paperId,String content, User user){

        Comments comment = new Comments();
        comment.setContent(content);
        comment.setPaperId(paperId);
        comment.setCommentator(user.getId());
        comment.setCommentatorName(user.getName());
        comment.setCommentTime(new Timestamp(new Date().getTime()));
        commentsMapper.insertComments(comment);
        return  comment;
    }

    //通过ID获得评论
    public Comments selectById(Integer id) {
        return commentsMapper.getCommentsByrId(id);
    }


    //获得文章评论
    public List<Comments> selectByPaperId(Long id) {
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("paperId",id);
        return commentsMapper.selectList(queryWrapper);
    }

    //获得用户评论
    public List<Comments> selectByCommentator(Integer id) {
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Commentator",id);
        return commentsMapper.selectList(queryWrapper);
    }

    //删除评论
    public int deleteById(Integer id) {
        return commentsMapper.deleteCommentsById(id);
    }



}
