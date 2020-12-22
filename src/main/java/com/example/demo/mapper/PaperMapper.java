package com.example.demo.mapper;

import com.example.demo.bean.Paper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface PaperMapper extends ElasticsearchRepository<Paper, String> {


}
