package com.example.demo;

import com.example.demo.bean.Article;
import com.example.demo.bean.Author;
import com.example.demo.bean.Tutorial;
import com.example.demo.mapper.ArticleSearchRepository;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ElasticSearchTest {
 
	@Autowired
	private ArticleSearchRepository articleSearchRepository;
	@Autowired
	DataSource dataSource;

	@Test
	public void contextLoads() throws SQLException {
		System.out.println(dataSource.getClass());

		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		connection.close();
	}
	/**
	 * 存入测试
	 */
	@Test
	public void testSaveArticleIndex(){
		Author author=new Author();
		author.setId(1L);
		author.setName("tianshouzhi");
		author.setRemark("java developer");
		
		Tutorial tutorial=new Tutorial();
		tutorial.setId(1L);
		tutorial.setName("elastic search");
		
		Article article =new Article();
		article.setId(1L);
		article.setTitle("springboot integreate elasticsearch");
		article.setAbstracts("springboot integreate elasticsearch is very easy");
		article.setTutorial(tutorial);
		article.setAuthor(author);
		article.setContent("elasticsearch based on lucene,"
				+ "spring-data-elastichsearch based on elaticsearch"
				+ ",this tutorial tell you how to integrete springboot with spring-data-elasticsearch");
		article.setPostTime(new Date());
		article.setClickCount(1L);
		
		articleSearchRepository.save(article);

	}
	/**
	 * 存入测试
	 */
	@Test
	public void testDelArticleIndex(){
//		Author author=new Author();
//		author.setId(1L);
//		author.setName("tianshouzhi");
//		author.setRemark("java developer");
//
//		Tutorial tutorial=new Tutorial();
//		tutorial.setId(1L);
//		tutorial.setName("elastic search");

		Article article =new Article();
		article.setId(1L);//只要有ID就可删除
//		article.setTitle("springboot integreate elasticsearch");
//		article.setAbstracts("springboot integreate elasticsearch is very easy");
//		article.setTutorial(tutorial);
//		article.setAuthor(author);
//		article.setContent("elasticsearch based on lucene,"
//				+ "spring-data-elastichsearch based on elaticsearch"
//				+ ",this tutorial tell you how to integrete springboot with spring-data-elasticsearch");
//		article.setPostTime(new Date());
//		article.setClickCount(1L);

		articleSearchRepository.delete(article);

	}


	/**
	 * 搜索测试
	 */
	@Test
	public void testSearch(){
		System.out.println("开始搜索" );

		String queryString="springboot";//搜索关键字
		QueryStringQueryBuilder builder=new QueryStringQueryBuilder(queryString);
		Iterable<Article> searchResult = articleSearchRepository.search(builder);
		Iterator<Article> iterator = searchResult.iterator();
		while(iterator.hasNext()){
			System.out.println("结果"+  iterator.next().toString());
		}
	}
	
}