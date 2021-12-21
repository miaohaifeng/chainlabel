package com.happy.video.service.impl;

import com.happy.video.mapper.*;
import com.happy.video.pojo.*;
import com.happy.video.service.DebankService;
import com.happy.video.service.SpiderBlockService;
import com.happy.video.toolbox.util.ClearNullUtil;
import com.happy.video.toolbox.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DebankServiceImpl implements DebankService {
    @Autowired
    DebankSupportProtocalMapper debankSupportProtocalMapper;
    @Autowired
    DappradarAppMapper dappradarAppMapper;

    @Autowired
    BscAppsMapper bscAppsMapper;

    @Autowired
    DebankDataMapper debankDataMapper;

    @Autowired
    HuobiAppMapper huobiAppMapper;

    @Autowired
    ProjectAllPlatformMapper projectAllPlatformMapper;

    @Override
    public int insertSupportProtocal(DebankSupportProtocal record) {

        return debankSupportProtocalMapper.insert(record);
    }

    public List<DebankSupportProtocal> selectByDate(String date) {
        return debankSupportProtocalMapper.selectByDate(date);
    }

    public List<DappradarApp> selectDappradarByDate(String date) {
        return dappradarAppMapper.selectByDate(date);
    }

    public void insetDappradarRecord(DappradarApp record) {
        dappradarAppMapper.insert(record);
    }

    public void updateDappradarRecord(DappradarApp record) {
        dappradarAppMapper.updateByPrimaryKeySelective(record);
    }

    public void insertBscAppRecord(BscApps bscApps) {
        bscAppsMapper.insert(bscApps);
    }

    public void insertDeBankDataRecord(DebankData debankData) {
        debankDataMapper.insert(debankData);
    }


    public void updateDebankInfo() {

        List<DebankSupportProtocal> list = debankSupportProtocalMapper.selectAll();

        list.forEach(record->{
            record.setDebankId(record.getDebankId().replace(record.getChain()+"_",""));
            debankSupportProtocalMapper.updateByPrimaryKey(record);
        });
    }

    public void union(){
        String spiderDate = "2021-09-12";

        List<HuobiApp> huobiProjectList = selectAllHuobiProject();
        huobiProjectList.stream().forEach(obj->{
            obj.setSiteUrl(ClearNullUtil.formatWebsite(obj.getSiteUrl()));
            String chain = obj.getChain().toLowerCase(Locale.ROOT);
            if ("oec".equals(chain)) {
                chain = "okt";
            } else if ("polygon".equals(chain)) {
                chain = "matic";
            }
            obj.setChain(chain);
        });

        List<DebankSupportProtocal> deBankProjectList = selectByDate(spiderDate);
        List<DappradarApp> dappradarProjectList = selectDappradarByDate(spiderDate);
        dappradarProjectList.stream().forEach(obj->{
            obj.setChain("eth");
        });

        Map<String, List<DebankSupportProtocal>> deBankChainAndList = deBankProjectList.stream().collect(Collectors.groupingBy(obj -> obj.getChain()));
        Map<String, List<HuobiApp>> huobiChainAndList = huobiProjectList.stream().collect(Collectors.groupingBy(obj -> obj.getChain()));

        String chainFLag = "eth";
        List<DebankSupportProtocal> debankChainFlagList = deBankChainAndList.get(chainFLag);
        List<HuobiApp> huobiChainFlagList = huobiChainAndList.get(chainFLag);

        dappradarProjectList.stream().forEach(dapObj->{
            String siteUrl = dapObj.getSiteUrl();
            ProjectAllPlatform projectAllPlatform = new ProjectAllPlatform();
            projectAllPlatform.setChain(chainFLag);
            projectAllPlatform.setDapparId(dapObj.getDappradarId());
            projectAllPlatform.setDapparName(dapObj.getName());
            projectAllPlatform.setDapparSiteUrl(dapObj.getSiteUrl());

            debankChainFlagList.stream().forEach(deo->{
                if(deo.getSiteUrl().equals(siteUrl)){
                    projectAllPlatform.setDebankName(deo.getNameEn());
                    projectAllPlatform.setDebankSiteUrl(deo.getSiteUrl());
                    projectAllPlatform.setDebankId(deo.getDebankId());
                };
            });

            huobiChainFlagList.stream().forEach(huoo->{
                if(huoo.getSiteUrl().equals(siteUrl)){
                    projectAllPlatform.setHuobiName(huoo.getName());
                    projectAllPlatform.setHuobiSiteUrl(huoo.getSiteUrl());
                    projectAllPlatform.setHuobiIb(huoo.getId()+"");
                };
            });

            projectAllPlatformMapper.insert(projectAllPlatform);
        });



    }

    private List<HuobiApp> selectAllHuobiProject() {
        return  huobiAppMapper.selectAll();
    }
}
