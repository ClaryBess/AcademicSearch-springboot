package com.example.demo.mapper;

import com.example.demo.bean.Researcher;
import com.example.demo.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

@Mapper
public interface ResearcherMapper {

    @Select("select * from User where role=2")
    public List<Researcher> getAllResearcher();

    @Select("select * from User where researcherId=#{researcherId} and role=2")
    public User getResearcherById(Long researcherId);

    @Select("select * from User where name=#{name} and role=2")
    public User getResearcherByName(String name);

    @Select("select * from User where email=#{email} and role=2")
    public User getResearcherByEmail(String email);

    @Select("select * from User where organization=#{organization} and role=2")
    public List<User> getResearcherByOrganization(String organization);

    @Select("select * from User where name=%#{keyword}% and role=2")
    public List<User> getResearcherByKeyword(String keyword);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into User(name,organization,paperCount,index,email,pwd,info,role) values(#{name},#{organization},#{paperCount},#{index},#{email},#{pwd},#{info},2)")
    public int insertResearcher(Researcher researcher);

    @Delete("delete from User where id=#{id}")
    public int deleteResearcherById(Integer id);

    @Update("update User set organization=#{organization} where id=#{id}")
    public int updateOrganization(Researcher researcher);

    @Update("update User set paperCount=#{paperCount} where id=#{id}")
    public int updatePaperCount(Researcher researcher);

    @Update("update User set edu=#{edu} where id=#{id}")
    public int updateEdu(Researcher researcher);

    @Update("update User set pwd=#{pwd} where id=#{id}")
    public int updatePwd(Researcher researcher);

    @Update("update User set info=#{info} where id=#{id}")
    public int updateInfo(Researcher researcher);

}
