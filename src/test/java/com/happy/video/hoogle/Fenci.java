package com.happy.video.hoogle;
import com.alibaba.fastjson.JSON;
import com.happy.video.mapper.TSignLibraryMapper;
import com.happy.video.pojo.TSignLibrary;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Fenci {
    @Autowired
    TSignLibraryMapper tSignLibraryMapper;



    @Test
    public void updateAction(){
        List<String> actionList = new ArrayList<>();
        actionList.add("burn      ");
        actionList.add("remove");
        actionList.add("add");
        actionList.add("mint");
        actionList.add("swap");
        actionList.add("payout");
        actionList.add("claim");
        actionList.add("create");
        actionList.add("transfer");
        actionList.add("redeem");
        actionList.add("authorize");
        actionList.add("stake");
        actionList.add("vote");
        actionList.add("exchange");
        actionList.add("purchase");
        actionList.add("withdraw");
        actionList.add("approve");
        actionList.add("prepare");
        actionList.add("migrate");
        actionList.add("grant");
        actionList.add("delegate");
        actionList.add("verify");
        actionList.add("write");
        actionList.add("send");
        actionList.add("delete");
        actionList.add("Unstake");
        actionList.add("exit");
        actionList.add("havest   ");

        int start = 220498;
        int end = 717697;
        TSignLibrary tSignLibrary = tSignLibraryMapper.selectByPrimaryKey(220498);
        System.out.println(JSON.toJSONString(tSignLibrary));


    }
}
