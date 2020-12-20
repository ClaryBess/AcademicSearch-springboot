package com.example.demo.controller;


import com.example.demo.DTO.CommentDTO;
import com.example.demo.bean.Comments;
import com.example.demo.bean.CommonResult;
import com.example.demo.bean.User;
import com.example.demo.service.CommentsService;
import com.example.demo.service.PaperService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentController {

    @Autowired
    private CommentsService commentService;

    @Autowired
    private PaperService paperService;


    @Autowired
    private UserService userService;

    /**
     * 进行评论
     * @param commentDTO
     * @param request
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/comment/{userId}", method = RequestMethod.POST)
    public Object Comment(@RequestBody CommentDTO commentDTO,
                          @PathVariable("userId") Integer userId,
                          HttpServletRequest request) {
        User user = userService.getUserById(userId);
        if(user==null) return new CommonResult(400,"The user does not exist!",null);
        commentService.insertComments(commentDTO, user);
        return new CommonResult(200,null,commentDTO);
    }

}
