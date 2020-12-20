package com.example.demo.mapper;

import com.example.demo.bean.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MessageMapper {

    //展示所有系统信息，即管理员发送的信息
    @Select("select Message.id as Mid,text,read,time,name from Message,User where from=User.id and role=0 and to=#{id} and read!=3")
    public List<Object> ShowAllSysMessage(Integer id);

    //展示所有学者私信
    @Select("select Message.id as Mid,User.id as Uid,text,read,time,name from Message,User where from=User.id and role=2 and to=#{id} and read!=3")
    public List<Object> ShowAllMessageFromRe(Integer id);

    //展示所有我的私信
    @Select("select Message.id as Mid,User.id as Uid,text,read,time,name from Message,User where to=User.id and role=2 and from=#{id} and read!=2")
    public List<Object> ShowMyMessage(Integer id);

    //向学者发送私信
    @Insert("insert into Message values(DEFAULT,#{from},#{to},#{text},0,NOW())")
    public int insertMessage(Integer from, Integer to, String text);

    //展示私信内容
    @Select("select * from Message where id=#{id}")
    public Message ShowMessageDetail(Integer id);

    //读取私信内容
    @Update("update Message set read=1 where id=#{id} and read=0")
    public int ReadMessage(Integer id);

    //获取私信送达用户
    @Select("select to from where id=#{id}")
    public Object GetToUser(Integer id);

    //接收方删除私信1
    @Update("update Message set read=3 where id=#{id}")
    public int DeleteMessageByReceiver1(Integer id);

    //接收方删除私信2
    @Delete("delete from Message where read=2 and id=#{id}")
    public int DeleteMessageByReceiver2(Integer id);

    //发送方删除私信1
    @Update("update Message set read=2 where id=#{id}")
    public int DeleteMessageBySender1(Integer id);

    //发送方删除私信2
    @Delete("delete from Message where read=3 and id=#{id}")
    public int DeleteMessageBySender2(Integer id);

    //删除私信(可能没用)
    @Delete("delete from Message where id=#{id}")
    public int deleteMessageById(Integer id);
}
