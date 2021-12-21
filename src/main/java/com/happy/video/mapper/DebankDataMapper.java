package com.happy.video.mapper;

import com.happy.video.pojo.DebankData;

public interface DebankDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DebankData record);

    int insertSelective(DebankData record);

    DebankData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DebankData record);

    int updateByPrimaryKey(DebankData record);
}