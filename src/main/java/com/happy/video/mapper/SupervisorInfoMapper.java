package com.happy.video.mapper;

import com.happy.video.pojo.SupervisorInfo;

import java.util.List;

public interface SupervisorInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SupervisorInfo record);

    int insertSelective(SupervisorInfo record);

    List<SupervisorInfo> selectAll();

    SupervisorInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SupervisorInfo record);

    int updateByPrimaryKey(SupervisorInfo record);
}