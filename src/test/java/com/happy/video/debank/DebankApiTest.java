package com.happy.video.debank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.happy.video.toolbox.util.HttpUtils;
import com.happy.video.vo.debank.SupportedProtocolReturn;
import com.happy.video.vo.debankapi.DeBankNFTReturn;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DebankApiTest {

    @Test
    public void nftTest(){
        String url = "https://openapi.debank.com/v1/user/nft_list?id=0x3abedba3052845ce3f57818032bfa747cded3fca&chain_id=eth";
//        String html = HttpUtils.getHtml(url);
        String html = HttpUtils.getHtml(url).replace("Orc #","");

        List<DeBankNFTReturn> nftReturnList = JSON.parseObject(html, new TypeReference<List<DeBankNFTReturn>>(){});

        Set<String> contentTypeSet = new HashSet<>();
        nftReturnList.stream().forEach(obj->{
            contentTypeSet.add(obj.getContent_type());
        });
        System.out.println(JSON.toJSONString(contentTypeSet));
    }
}
