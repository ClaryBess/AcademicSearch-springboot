package com.example.demo.controller;

import com.example.demo.DTO.CommentItem;
import com.example.demo.DTO.PaperAuthorDTO;
import com.example.demo.bean.*;
import com.example.demo.service.*;

import org.apache.shiro.SecurityUtils;
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
    private ResearcherService researcherService;
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
    public Object viewDoc(@PathVariable("id") String id,
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
                         @RequestParam String  Author[],
                         @RequestParam String keyWord[],
                         @RequestParam String url,
                         @RequestParam String Abstract,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        Paper paper = new Paper(title,citation,year,Author, keyWord,url,Abstract);

        paperService.save(paper);
        CommonResult commonResult=new CommonResult(200, null, paper);
        return commonResult;
    }

    /**
     * 更新文档
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/paper/update", method = RequestMethod.POST)
    public Object UpdateDoc(@RequestParam String id,
                         @RequestParam(required = false) String title,
                         @RequestParam(required = false) Integer citation,
                         @RequestParam (required = false) Integer year,
                         @RequestParam(required = false)  String  Author[],
                         @RequestParam (required = false) String keyWord[],
                         @RequestParam (required = false) String url,
                         @RequestParam (required = false) String Abstract,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        Paper paper = new Paper(id,title,citation,year,Author, keyWord,url,Abstract);
        paperService.update(id,paper);
        CommonResult commonResult=new CommonResult(200, null, paper);
        return commonResult;
    }

    @RequestMapping("/paper/get/{id}")
    public CommonResult getPaperById(@PathVariable("id") String id) throws IOException {
        Paper paper = paperService.search(id);
        if(paper.getAuthor()!=null){
            List<String> authors=java.util.Arrays.asList(paper.getAuthor());
            paper.setAuthorShow(String.join(", ",authors));
        }
        if(paper.getKeywords()!=null){
            List<String> keyWords=java.util.Arrays.asList(paper.getKeywords());
            paper.setKeyWordShow(String.join(", ",keyWords));
        }
        return new CommonResult(200,"success",paper);
    }


    @RequestMapping(value = "/paper/comment/{id}")
    public CommonResult getCommentByPaper(@PathVariable("id") String id){
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

    @RequestMapping(value = "/paper/all")
    public CommonResult getAllPaper() throws IOException {
        List<Paper> paperList = paperService.searchALLPaper();
        return new CommonResult(200,"success",paperList);
    }

    /**
     * 查看作者
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/paper/author/{id}", method = RequestMethod.GET)
    public Object PaperAuthor(@PathVariable("id") String id,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            Paper paper=paperService.search(id);
            String name=paper.getAuthor()[0];
            List<Researcher> r=researcherService.searchResearcherByName(name);
            if(r.size()==0) return new CommonResult(400, "this author not exist", name);
            //用第一个
            Researcher researcher= r.get(0);
            PaperAuthorDTO paperAuthorDTO=new PaperAuthorDTO();
            if (researcher == null) return new CommonResult(400, "The researcher does not exist!", null);
            paperAuthorDTO.setId(researcher.getId());

            String field=String.join(", ",researcher.getField());
            if(field.equals("")) paperAuthorDTO.setField(null);
            else paperAuthorDTO.setField(researcher.getField().get(0));
//            if(researcher.getField()==null) paperAuthorDTO.setField(null);
//            else{
//                //只要第一个
//                String field=researcher.getField().get(0);
//                //String.join(", ",researcher.getField());
//                paperAuthorDTO.setField(field);
//            }
            paperAuthorDTO.setName(researcher.getName());
            if(researcher.getOrganization().equals("")) paperAuthorDTO.setWork(null);
            else paperAuthorDTO.setWork(researcher.getOrganization());
            CommonResult commonResult=new CommonResult(200, null, paperAuthorDTO);
            return commonResult;
        } catch (IOException e) {
            return new CommonResult(400,"error",null);
        }
    }
    /**
     * 删除论文
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/paper/delete/{id}", method = RequestMethod.GET)
    public CommonResult CommentDelete(@PathVariable("id") String paperId) throws IOException {
        Paper paper=paperService.search(paperId);
        if(paper==null) return new CommonResult(200,null,paper);
        paperService.delete(paperId);
        return new CommonResult(200,null,null);
    }




}
