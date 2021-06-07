package com.happy.video.service;

import com.happy.video.pojo.*;

import java.util.List;

public interface SpiderBlockService {

    int insert(SupervisorInfo record);

    int insertExchangeNumber(ExchangeNumber record);

    List<SupervisorInfo> selectAllSupervisorInfo();

    List<CexInfo> selectAllCex();

    List<ExchangeInfo> selectAllExchangeInfo();

    List<ExchangeNumber> selectAllExchangeNumber();

    int insertExchangeInfo(ExchangeInfo record);

    int updateBExchangeInfo(ExchangeInfo record);
}
