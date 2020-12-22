package com.example.demo.mapper;

import com.example.demo.bean.Researcher;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsResearcherMapper extends ElasticsearchRepository<Researcher, String> {
}
