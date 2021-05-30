package com.happy.video.service;

import com.happy.video.pojo.User;

/**
 * Created by zhonglq on 2019/1/10.
 */
public interface UserService {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
