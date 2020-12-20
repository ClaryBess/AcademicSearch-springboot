package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.service.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.Kernel;
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


    /**
     * 添加文档
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/paper/add", method = RequestMethod.POST)
    public Object AddDoc(@RequestParam String title,
                         @RequestParam Integer citation,
                         @RequestParam Integer year,
                         @RequestParam String field1,
                         @RequestParam String  Author[],
                         @RequestParam String keyWord[],
                         @RequestParam String url,
                         @RequestParam String Abstract,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        Paper paper = new Paper(title,citation,year,field1,Author, keyWord,url,Abstract);

        paperService.save(paper);
        CommonResult commonResult=new CommonResult(200, null, paper);
        return commonResult;
    }



    @RequestMapping("/paper/get/{id}")
    public CommonResult getPaperById(@PathVariable("id") Long id) throws IOException {
        Paper paper = paperService.search(id);
        List<String> authors=java.util.Arrays.asList(paper.getAuthor());
        paper.setAuthorShow(String.join(", ",authors));
        List<String> keyWords=java.util.Arrays.asList(paper.getKeyWord());
        paper.setKeyWordShow(String.join(", ",keyWords));
        return new CommonResult(200,"success",paper);
    }


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
