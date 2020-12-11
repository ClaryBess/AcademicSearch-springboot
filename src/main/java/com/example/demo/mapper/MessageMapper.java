package com.example.demo.mapper;

import com.example.demo.bean.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Select("select * from Message where id=#{id}")
    public Message getMessageById(Integer id);

    @Select("select * from Message where from=#{from}")
    public List<Message> getMessageByFrom(Integer from);

    @Select("select * from Message where to=#{to}")
    public List<Message> getMessageByTo(Integer to);

    @Insert("insert into Message(id,from,to,text,read,time) values(#{id},#{from},#{to},#{text},#{read},#{time})")
    public int insertMessage(Message message);

    @Delete("delete from Message where id=#{id}")
    public int deleteMessageById(Integer id);


}
