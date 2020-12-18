package com.example.demo.mapper;

import com.example.demo.bean.Researcher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResearcherMapper {

    @Select("select * from User where id=#{id} and role=2")
    public Researcher getResearcherById(Long id);

    @Select("select * from User where name=#{name} and role=2")
    public Researcher getResearcherByName(String name);

    @Select("select * from User where email=#{email} and role=2")
    public Researcher getResearcherByEmail(String email);

    @Select("select * from User where organization=#{organization} and role=2")
    public List<Researcher> getResearcherByOrganization(String organization);

    @Select("select * from User where name=%#{keyword}% and role=2")
    public List<Researcher> getResearcherByKeyword(String keyword);

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
