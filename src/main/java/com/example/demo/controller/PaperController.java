package com.example.demo.controller;

import com.example.demo.DTO.PaperReturnDTO;
import com.example.demo.bean.Comments;
import com.example.demo.bean.CommonResult;
import com.example.demo.bean.Paper;
import com.example.demo.bean.User;
import com.example.demo.service.CommentsService;
import com.example.demo.service.PaperService;

import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class PaperController {
    @Autowired
    PaperService paperService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentsService commentService;



    /**
     * 查看文档
     * @param id
     * @param request
     * @param response
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/doc/{id}", method = RequestMethod.GET)
    public Object viewDoc(@PathVariable("id") Long id,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            Paper paper = paperService.search(id);
            PaperReturnDTO paperreturnDTO = new PaperReturnDTO();
            BeanUtils.copyProperties(paperreturnDTO, paper);
            if (paper == null) return new CommonResult(400, "The paper does not exist!", null);

            List<Comments> comments = commentService.selectByPaperId(id);
            paperreturnDTO.setComments(comments);
            //论文作者获取

            CommonResult commonResult=new CommonResult(200, null, paper);
            paperreturnDTO.setResultDTO(commonResult);
            return paperreturnDTO;
        } catch (IOException e) {
            return null;
        }
    }
}
