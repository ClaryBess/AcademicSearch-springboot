package com.example.demo.mapper;

import com.example.demo.bean.Apply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApplyMapper {
    /*展示所有申请，按时间顺序*/
    @Select("select * from Apply order by id desc")
    public List<Apply> ShowAll();

    /*展示所有未处理的申请，按时间顺序*/
    @Select("select * from Apply where state='waiting' order by id desc")
    public List<Apply> ShowWaiting();

    /*展示所有拒绝的申请*/
    @Select("select * from Apply where state='reject' order by id desc")
    public List<Apply> ShowReject();

    /*展示所有接受的申请*/
    @Select("select * from Apply where state='accept' order by id desc")
    public List<Apply> ShowPass();

    //根据id获取申请
    @Select("select * from Apply where id=#{id}")
    public Apply getApplyById(Long id);

    /*展示某一用户的所有申请*/
    @Select("select * from Apply where user=#{user} order by id desc")
    public List<Apply> ShowApplyByUser(long user);

    /*接受申请*/
    @Update("update Apply set state='accept' where id=#{id}")
    public Integer Accept(long id);

    /*拒绝申请*/
    @Update("update Apply set state='reject' where id=#{id}")
    public Integer Reject(long id);

    /*发送申请*/
    @Insert("insert into Apply values(DEFAULT,#{user},'waiting',#{feedback},#{researcher})")
    public Integer SendApply(Integer user,String feedback,String researcher);

}
