package com.example.demo.mapper;

import com.example.demo.bean.Researcher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResearcherMapper {

    @Select("select * from Researcher where id=#{id}")
    public Researcher getResearcherById(Integer id);

    @Select("select * from Researcher where name=#{name}")
    public Researcher getResearcherByName(String name);

    @Select("select * from Researcher where organization=#{organization}")
    public List<Researcher> getResearcherByOrganization(String organization);

    @Insert("insert into Researcher(name,organization,paperCount,edu,email,pwd,info) values(#{name},#{organization},#{paperCount},#{edu},#{email},#{pwd},#{info})")
    public int insertResearcher(Researcher researcher);

    @Delete("delete from Researcher where id=#{id}")
    public int deleteResearcherById(Integer id);

    @Update("update Researcher set organization=#{organization} where id=#{id}")
    public int updateOrganization(Researcher researcher);

    @Update("update Researcher set paperCount=#{paperCount} where id=#{id}")
    public int updatePaperCount(Researcher researcher);

    @Update("update Researcher set edu=#{edu} where id=#{id}")
    public int updateEdu(Researcher researcher);

    @Update("update Researcher set pwd=#{pwd} where id=#{id}")
    public int updatePwd(Researcher researcher);

    @Update("update Researcher set info=#{info} where id=#{id}")
    public int updateInfo(Researcher researcher);
}
