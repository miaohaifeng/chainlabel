package com.happy.video.mapper;

import com.happy.video.pojo.ExchangeNumber;
import com.happy.video.pojo.SupervisorInfo;

import java.util.List;

public interface ExchangeNumberMapper {
    int insert(ExchangeNumber record);

    int insertSelective(ExchangeNumber record);

    List<ExchangeNumber> selectAll();

}