package com.happy.video.mapper;

import com.happy.video.pojo.DappradarApp;

import java.util.List;

public interface DappradarAppMapper {
    int insert(DappradarApp record);

    int insertSelective(DappradarApp record);

    int updateByPrimaryKeySelective(DappradarApp record);

    List<DappradarApp> selectByDate(String date);
}