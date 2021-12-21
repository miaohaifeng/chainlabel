package com.happy.video.mapper;

import com.happy.video.pojo.DebankSupportProtocal;

import java.util.List;


public interface DebankSupportProtocalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DebankSupportProtocal record);

    int insertSelective(DebankSupportProtocal record);

    DebankSupportProtocal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DebankSupportProtocal record);

    int updateByPrimaryKey(DebankSupportProtocal record);

    List<DebankSupportProtocal> selectByDate(String date);

    List<DebankSupportProtocal> selectAll();
}