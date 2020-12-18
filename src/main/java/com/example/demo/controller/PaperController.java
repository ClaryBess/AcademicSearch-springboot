package com.example.demo.controller;

import com.example.demo.DTO.PaperCreateDTO;
import com.example.demo.DTO.PaperReturnDTO;
import com.example.demo.bean.*;
import com.example.demo.service.*;

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
    @RequestMapping(value = "/paper/{id}", method = RequestMethod.GET)
    public Object viewDoc(@PathVariable("id") Long id,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            Paper paper = paperService.search(id);
            PaperReturnDTO paperreturnDTO = new PaperReturnDTO();
            BeanUtils.copyProperties(paperreturnDTO, paper);
            if (paper == null) return new CommonResult(400, "The paper does not exist!", null);

            User user=userService.getUserById(paper.getAuthorId());
            paperreturnDTO.setAuthorName(user.getName());

            List<Comments> comments = commentService.selectByPaperId(id);
            paperreturnDTO.setComments(comments);

            CommonResult commonResult=new CommonResult(200, null, paper);
            paperreturnDTO.setResultDTO(commonResult);
            return paperreturnDTO;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 创建新文档(虽然应该没啥用)
     * @param paperCreateDTO
     * @param userId
     * @param request
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/paper/{userId}", method = RequestMethod.POST)
    public Object createDoc(@RequestBody PaperCreateDTO paperCreateDTO,
                            @PathVariable("userId") Integer userId,
                            HttpServletRequest request) {
        Paper paper1 = new Paper();
        BeanUtils.copyProperties(paperCreateDTO, paper1);
        paper1.setAuthorId(userId);
        paperService.save(paper1);
        return paper1;
    }

}
