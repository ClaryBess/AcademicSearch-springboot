package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.bean.Comments;
import com.example.demo.bean.Paper;
import com.example.demo.bean.Researcher;
import com.example.demo.bean.User;
import com.example.demo.mapper.CommentsMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    public void insertComments(Comments comments, User user){
        comments.setCommentator(user.getId());
        comments.setCommentatorName(user.getName());
        comments.setCommentTime(new Timestamp(new Date().getTime()));
        commentsMapper.insert(comments);
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


}
