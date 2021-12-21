package com.happy.video.mapper;

import com.happy.video.pojo.HuobiApp;

import java.util.List;

public interface HuobiAppMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HuobiApp record);

    int insertSelective(HuobiApp record);

    HuobiApp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HuobiApp record);

    int updateByPrimaryKey(HuobiApp record);

    List<HuobiApp> selectAll();
}