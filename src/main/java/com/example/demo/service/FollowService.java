package com.example.demo.service;

import com.example.demo.bean.Follow;
import com.example.demo.mapper.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    @Autowired
    FollowMapper followMapper;

    //添加关注
    public Follow insertFollow(Follow follow){
        Follow follow1=followMapper.getFollowByUserAndResearcher(follow.getUser(),follow.getResearcher());
        if(follow1!=null)
            return follow1;
        followMapper.insertFollow(follow);
        return followMapper.getFollowById(follow.getId());
    }

    //取消关注
    public int deleteByUserAndResearcher(Integer user, Integer researcher){
        return followMapper.deleteFollowByUserAndResearcher(user,researcher);
    }

    //根据用户和学者获取
    public Follow getFollowByUserAndResearcher(Integer user, Integer researcher){
        return followMapper.getFollowByUserAndResearcher(user,researcher);
    }

    //根据id获取
    public Follow getFollowById(Integer id){
        return followMapper.getFollowById(id);
    }

    //获取用户的全部关注
    public List<Follow> getFollowByUser(Integer user){
        return followMapper.getFollowByUser(user);
    }

    //获取学者的全部关注者
    public List<Follow> getFollowByResearcher(Integer researcher){
        return followMapper.getFollowByResearcher(researcher);
    }
}
