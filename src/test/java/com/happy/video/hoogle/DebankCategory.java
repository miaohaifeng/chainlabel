package com.happy.video.hoogle;

import com.alibaba.fastjson.JSON;
import com.happy.video.toolbox.util.HttpUtils;
import com.happy.video.vo.debank.category.DeBankCategoryReturn;
import org.junit.Test;

import java.util.HashMap;

public class DebankCategory {

    @Test
    public void getDebankCategroyTest(){
        String categoryUrl = "https://api.debank.com/project/tag_list";
        String categoryJson = HttpUtils.getHtml(categoryUrl);
        DeBankCategoryReturn deBankCategoryReturn = JSON.parseObject(categoryJson, DeBankCategoryReturn.class);

        HashMap<String, String> categoryMap = new HashMap<>();
        deBankCategoryReturn.getData().getTag_list().forEach(pojo -> {
            System.out.println(pojo.getId()+"\t"+ pojo.getName().getCh()+"\t"+ pojo.getName().getEn());
        });
    }
}
