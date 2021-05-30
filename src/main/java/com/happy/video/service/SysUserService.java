package com.happy.video.service;

import com.happy.video.pojo.SysUser;

/**
 * Created by zhonglq on 2019/1/10.
 */
public interface SysUserService {
    int deleteByPrimaryKey(Integer userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}
