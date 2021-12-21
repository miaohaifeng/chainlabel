package com.happy.video.mapper;

import com.happy.video.pojo.ProjectAllPlatform;

public interface ProjectAllPlatformMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectAllPlatform record);

    int insertSelective(ProjectAllPlatform record);

    ProjectAllPlatform selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectAllPlatform record);

    int updateByPrimaryKey(ProjectAllPlatform record);
}