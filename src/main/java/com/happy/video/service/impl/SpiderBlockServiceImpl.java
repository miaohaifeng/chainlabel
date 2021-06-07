package com.happy.video.service.impl;

import com.happy.video.mapper.CexInfoMapper;
import com.happy.video.mapper.ExchangeInfoMapper;
import com.happy.video.mapper.ExchangeNumberMapper;
import com.happy.video.mapper.SupervisorInfoMapper;
import com.happy.video.pojo.CexInfo;
import com.happy.video.pojo.ExchangeInfo;
import com.happy.video.pojo.ExchangeNumber;
import com.happy.video.pojo.SupervisorInfo;
import com.happy.video.service.SpiderBlockService;
import com.happy.video.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpiderBlockServiceImpl implements SpiderBlockService {
    @Autowired
    SupervisorInfoMapper supervisorInfoMapper;
    @Autowired
    CexInfoMapper cexInfoMapper;
    @Autowired
    ExchangeNumberMapper exchangeNumberMapper;
    @Autowired
    ExchangeInfoMapper exchangeInfoMapper;

    @Override
    public int insert(SupervisorInfo record) {

        return supervisorInfoMapper.insert(record);
    }

    @Override
    public int insertExchangeNumber(ExchangeNumber record) {
        return exchangeNumberMapper.insert(record);
    }

    @Override
    public List<SupervisorInfo> selectAllSupervisorInfo() {
        return supervisorInfoMapper.selectAll();
    }

    @Override
    public List<CexInfo> selectAllCex() {
        return cexInfoMapper.selectAll();
    }

    @Override
    public List<ExchangeInfo> selectAllExchangeInfo() {
        return exchangeInfoMapper.selectAll();
    }
    @Override
    public List<ExchangeNumber> selectAllExchangeNumber() {
        return exchangeNumberMapper.selectAll();
    }

    @Override
    public int insertExchangeInfo(ExchangeInfo record){
        return exchangeInfoMapper.insert(record);
    }

    @Override
    public int updateBExchangeInfo(ExchangeInfo record){
        return exchangeInfoMapper.updateByPrimaryKey(record);
    }
}
