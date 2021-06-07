package com.happy.video.mapper;

import com.happy.video.pojo.CexInfo;

import java.util.List;

public interface CexInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CexInfo record);

    int insertSelective(CexInfo record);

    CexInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CexInfo record);

    int updateByPrimaryKey(CexInfo record);

    List<CexInfo> selectAll();

}