package com.happy.video.mapper;

import com.happy.video.pojo.TSignLibrary;

public interface TSignLibraryMapper {
    int deleteByPrimaryKey(Integer fId);

    int insert(TSignLibrary record);

    int insertSelective(TSignLibrary record);

    TSignLibrary selectByPrimaryKey(Integer fId);

    int updateByPrimaryKeySelective(TSignLibrary record);

    int updateByPrimaryKey(TSignLibrary record);
}