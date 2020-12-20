package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.service.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PaperController {
    @Autowired
    PaperService paperService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentsService commentService;

    @Autowired
    private CollectionService collectionService;
    @Autowired
    private DirectoryService directoryService;


    /**
     * 查看文档
     * @param id
     * @param request
     * @param response
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/paper/detail/{id}", method = RequestMethod.GET)
    public Object viewDoc(@PathVariable("id") Long id,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            Paper paper = paperService.search(id);
            if (paper == null) return new CommonResult(400, "The paper does not exist!", null);

            CommonResult commonResult=new CommonResult(200, null, paper);
            return commonResult;
        } catch (IOException e) {
            return new CommonResult(400,"error",null);
        }
    }

<<<<<<< HEAD
    @RequestMapping("/paper/get/{id}")
    public CommonResult getPaperById(@PathVariable("id") Long id) throws IOException {
        Paper paper = paperService.search(id);
        return new CommonResult(200,"success",paper);
    }
=======
>>>>>>> b804f53d2bc9a3eea15201acecbf5d807a2eb6a1

    @RequestMapping(value = "/paper/comment/{id}")
    public CommonResult getCommentByPaper(@PathVariable("id") Long id){
        List<CommentItem> commentItems = new ArrayList<CommentItem>();
        List<Comments> comments = commentService.selectByPaperId(id);
        for(Comments comments1 : comments){
            User user = userService.getUserById(comments1.getCommentator());
            String profileUrl = user.getAvatar();
            CommentItem commentItem = new CommentItem(profileUrl, comments1.getCommentatorName(), comments1.getContent(), comments1.getCommentTime());
            commentItems.add(commentItem);
        }
        if(commentItems.size() == 0)
            return new CommonResult(400,"this paper have no comment",null);
        else
            return new CommonResult(200,"success",commentItems);
    }

}
