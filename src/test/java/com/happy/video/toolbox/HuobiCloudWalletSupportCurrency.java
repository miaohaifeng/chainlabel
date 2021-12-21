package com.happy.video.toolbox;

import com.alibaba.fastjson.JSON;
import com.happy.video.toolbox.util.HttpUtils;
import com.happy.video.vo.debank.SupportedProtocolReturn;
import com.happy.video.vo.huobiapi.ChuihuiList;
import com.happy.video.vo.huobiapi.ChunhuiCurrency;
import com.happy.video.vo.huobiapi.HuobiAPIReturnData;
import org.junit.Test;

import java.io.*;
import java.util.List;

public class HuobiCloudWalletSupportCurrency {

    @Test
    public void supportCurrencyTest() throws Exception {
//        File file = new File("/Users/miaohaifeng/Desktop/huobi/需求/云钱包二期/hbitex-global-trading-tokens-mapping.txt");
//        String chunuhi = txt2String(file).replace("\n","");
//
//        ChuihuiList chuihuiList = JSON.parseObject(chunuhi,  ChuihuiList.class);
//
//        chuihuiList.getList().forEach(obj->{
//            System.out.println(obj.getSymbolAlias());
//        });

        String json = HttpUtils.getHtml("https://api.huobi.pro/v2/reference/currencies");
        HuobiAPIReturnData huobiAPIReturnData = JSON.parseObject(json, HuobiAPIReturnData.class);

        huobiAPIReturnData.getData().forEach(single -> {
            String currency = single.getCurrency();
            single.getChains().forEach(chain -> {
                System.out.print(currency + "\t" + chain.getChain() + "\t" + chain.getDepositStatus() + "\t" + chain.getWithdrawStatus());
                System.out.println();
            });
        });
    }

    public static String txt2String(File file) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result = result + "\n" + s;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



}
