package com.example.demo.mapper;

import com.example.demo.bean.Paper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PaperMaper extends ElasticsearchRepository<Paper, Long> {
}
