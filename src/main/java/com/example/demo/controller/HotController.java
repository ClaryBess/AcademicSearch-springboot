package com.example.demo.controller;

import com.example.demo.DTO.HotDTO;
import com.example.demo.bean.CommonResult;
import com.example.demo.bean.Paper;
import com.example.demo.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class HotController {
    @Autowired
    PaperService paperService;

    @ResponseBody
    @RequestMapping(value ="/hot/paper",method = RequestMethod.GET)
    public CommonResult getHotPaper(){
        try {
            List<HotDTO> hot=paperService.OrderByCitation();
            return new CommonResult(200,"success",hot);
        }catch (IOException o){
            return new CommonResult(400,"error",null);
        }
    }

    @ResponseBody
    @RequestMapping(value ="/hot/field",method = RequestMethod.GET)
    public CommonResult getHotField() {
        try {
            List<String> hotfiled = paperService.HotField();
            return new CommonResult(200, "success", hotfiled);
        } catch (IOException o) {
            return new CommonResult(400, "error", null);
        }
    }

}
