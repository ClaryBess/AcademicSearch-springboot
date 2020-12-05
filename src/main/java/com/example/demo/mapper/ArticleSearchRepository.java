package com.example.demo.mapper;

import com.example.demo.bean.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
 
//泛型的参数分别是实体类型和主键类型
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Long>{
 
}