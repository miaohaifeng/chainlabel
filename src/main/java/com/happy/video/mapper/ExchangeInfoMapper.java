package com.happy.video.mapper;

import com.happy.video.pojo.ExchangeInfo;

import java.util.List;

public interface ExchangeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExchangeInfo record);

    int insertSelective(ExchangeInfo record);

    ExchangeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExchangeInfo record);

    int updateByPrimaryKey(ExchangeInfo record);

    List<ExchangeInfo> selectAll();
}