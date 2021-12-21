package com.happy.video.debank;

import com.alibaba.fastjson.JSON;
import com.happy.video.pojo.BscApps;
import com.happy.video.pojo.DappradarApp;
import com.happy.video.pojo.DebankData;
import com.happy.video.pojo.DebankSupportProtocal;
import com.happy.video.service.impl.DebankServiceImpl;
import com.happy.video.toolbox.DeBankDataTypeEnum;
import com.happy.video.toolbox.util.ClearNullUtil;
import com.happy.video.toolbox.util.DateUtil;
import com.happy.video.toolbox.util.HttpUtils;
import com.happy.video.vo.bsc.BscReturn;
import com.happy.video.vo.bscapi.BscApiReturn;
import com.happy.video.vo.dappradar.DappradarReturn;
import com.happy.video.vo.dappradar.Dapps;
import com.happy.video.vo.debank.SupportedProtocolReturn;
import com.happy.video.vo.debank.category.DeBankCategoryReturn;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderBlockServiceTest {

    @Autowired
    private DebankServiceImpl debankService;

    @Test
    public void debankUpdateRecord() {
        debankService.updateDebankInfo();
    }

    @Test
    public void union() {

        debankService.union();
    }

    @Test
    public void spiderDeBankSupportedProtocol() {
        String url = "https://api.debank.com/project/supported_protocol/list";
        String html = HttpUtils.getHtml(url);
        SupportedProtocolReturn supportedProtocolReturn = JSON.parseObject(html, SupportedProtocolReturn.class);
        supportedProtocolReturn.getData().forEach(protocal -> {

            DebankSupportProtocal record = new DebankSupportProtocal();
            record.setPriority(protocal.getPriority());
            String chain = protocal.getChain().trim();
//            if ("okt".equals(chain)) {
//                chain = "OEC";
//            } else if ("matic".equals(chain)) {
//                chain = "POLYGON";
//            }
            record.setChain(chain);
            record.setDebankId(protocal.getId().replace(protocal.getChain()+"_","").trim());
            record.setNameCh(protocal.getName().getCh());
            record.setNameEn(protocal.getName().getEn());
            record.setPlatformTokenId(protocal.getPlatform_token_id());
            record.setSiteUrl(ClearNullUtil.formatWebsite(protocal.getSite_url()));
            record.setSpiderDate(DateUtil.getDate());

            debankService.insertSupportProtocal(record);
        });
    }

    @Test
    public void spiderDeBankDataType() {
        String url = "https://api.debank.com/project/supported_protocol/list";
        String html = HttpUtils.getHtml(url);

        String categoryUrl = "https://api.debank.com/project/tag_list";
        String categoryJson = HttpUtils.getHtml(categoryUrl);
        DeBankCategoryReturn deBankCategoryReturn = JSON.parseObject(categoryJson, DeBankCategoryReturn.class);

        HashMap<String, String> categoryMap = new HashMap<>();
        deBankCategoryReturn.getData().getTag_list().forEach(pojo -> {
            categoryMap.put(pojo.getId(), pojo.getName().getCh());
        });


        SupportedProtocolReturn supportedProtocolReturn = JSON.parseObject(html, SupportedProtocolReturn.class);
        final int[] i = {0};
        supportedProtocolReturn.getData().forEach(protocal -> {

            try {
                i[0]++;
                System.out.println("当前：" + i[0]);
                String projectId = protocal.getId();

                DebankData record = new DebankData();
                record.setPriority(protocal.getPriority());
                record.setChain(protocal.getChain().trim());
                record.setDebankId(protocal.getId().trim());
                record.setNameCh(protocal.getName().getCh());
                record.setNameEn(protocal.getName().getEn());
                record.setPlatformTokenId(protocal.getPlatform_token_id());
                record.setSiteUrl(ClearNullUtil.formatWebsite(protocal.getSite_url()));
                record.setSpiderDate(DateUtil.getDate());


                boolean tvlFlag = HttpUtils.deBankChartDataHas(projectId, DeBankDataTypeEnum.tvl.getCode());
                boolean borrowFlag = HttpUtils.deBankChartDataHas(projectId, DeBankDataTypeEnum.borrow.getCode());
                boolean tradeVolumeFlag = HttpUtils.deBankChartDataHas(projectId, DeBankDataTypeEnum.trade_volume.getCode());
                boolean tradeCountFlag = HttpUtils.deBankChartDataHas(projectId, DeBankDataTypeEnum.trade_count.getCode());
                boolean tradeUserFlag = HttpUtils.deBankChartDataHas(projectId, DeBankDataTypeEnum.trade_user.getCode());
                boolean liquidateFlag = HttpUtils.deBankChartDataHas(projectId, DeBankDataTypeEnum.liquidate.getCode());
                boolean oracleContrFlag = HttpUtils.deBankChartDataHas(projectId, DeBankDataTypeEnum.oracle_contract.getCode());
                boolean oracleCallFlag = HttpUtils.deBankChartDataHas(projectId, DeBankDataTypeEnum.oracle_call.getCode());
                boolean contractUserFlag = HttpUtils.deBankChartDataHas(projectId, DeBankDataTypeEnum.contract_user.getCode());
                boolean contractCallFlag = HttpUtils.deBankChartDataHas(projectId, DeBankDataTypeEnum.contract_call.getCode());

                record.setTvl(tvlFlag + "");
                record.setBorrow(borrowFlag + "");
                record.setTradeVolume(tradeVolumeFlag + "");
                record.setTradeCount(tradeCountFlag + "");
                record.setOracleCall(oracleCallFlag + "");
                record.setOracleContr(oracleContrFlag + "");
                record.setLiquidate(liquidateFlag + "");
                record.setTradeUser(tradeUserFlag + "");
                record.setContractUser(contractUserFlag + "");
                record.setContractCall(contractCallFlag + "");

                record.setDebankProjectUrl("https://debank.com/projects/" + projectId);

                List<String> tag_ids = protocal.getTag_ids();
                if (!CollectionUtils.isEmpty(tag_ids)) {
                    String categoryEn = tag_ids.get(0);
                    tag_ids.remove(0);
                    record.setCategoryEn(categoryEn);
                    record.setCategoryCh(categoryMap.get(categoryEn));
                }

                debankService.insertDeBankDataRecord(record);
                if (tag_ids.isEmpty() == false) {
                    tag_ids.forEach(tag -> {
                        DebankData debankDataTemp = new DebankData();
                        BeanUtils.copyProperties(record, debankDataTemp);
                        debankDataTemp.setCategoryCh(categoryMap.get(tag));
                        debankDataTemp.setCategoryEn(tag);
                        debankService.insertDeBankDataRecord(debankDataTemp);
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }


    @Test
    public void debankJiexiReport() {
        String date1 = "2021-10-21";
        String date2 = "2021-11-03";

        List<DebankSupportProtocal> list1 = debankService.selectByDate(date1);
        List<DebankSupportProtocal> list2 = debankService.selectByDate(date2);

        Map<String, List<DebankSupportProtocal>> chainCount1 = list1.stream().collect(Collectors.groupingBy(DebankSupportProtocal::getChain));

        int ETH1 = chainCount1.get("eth").size();
        int BSC1 = chainCount1.get("bsc").size();
        int OEC1 = chainCount1.get("okt").size();
        int Fantom1 = chainCount1.get("ftm").size();
        int Polygon1 = chainCount1.get("matic").size();
        int XDai1 = chainCount1.get("xdai").size();
        int heco1 = chainCount1.get("heco").size();
        int avax = chainCount1.get("avax").size();
        int arb = chainCount1.get("arb").size();
        int op = chainCount1.get("op").size();
        int celo =0; //chainCount1.get("celo").size();


        Map<String, List<DebankSupportProtocal>> chainCount2 = list2.stream().collect(Collectors.groupingBy(DebankSupportProtocal::getChain));

        int ETH2 = chainCount2.get("eth").size();
        int BSC2 = chainCount2.get("bsc").size();
        int OEC2 = chainCount2.get("okt").size();
        int Fantom2 = chainCount2.get("ftm").size();
        int Polygon2 = chainCount2.get("matic").size();
        int XDai2 = chainCount2.get("xdai").size();
        int heco2 = chainCount2.get("heco").size();
        int avax2 = chainCount2.get("avax").size();
        int arb2 = chainCount2.get("arb").size();
        int op2 = chainCount2.get("op").size();
        int celo2 = chainCount2.get("celo").size();

        String report = "debank(https://debank.com/protocol)" + date2 + "\n" +
                "ETH链共：" + ETH2 + "个（相较" + date1 + "统计新增" + (ETH2 - ETH1) + "个）\n" +
                "BSC链共：" + BSC2 + "个（相较" + date1 + "统计新增" + (BSC2 - BSC1) + "个）\n" +
                "Fantom链：" + Fantom2 + "个（相较" + date1 + "统计新增" + (Fantom2 - Fantom1) + "个）\n" +
                "Heco链：" + heco2 + "个（相较" + date1 + "统计新增" + (heco2 - heco1) + "个）\n" +
                "Polygon链：" + Polygon2 + "个（相较" + date1 + "统计新增" + (Polygon2 - Polygon1) + "个）\n" +
                "OEC：" + OEC2 + "个（相较" + date1 + "统计新增" + (OEC2 - OEC1) + "个）\n" +
                "XDai：" + XDai2 + "个 （相较" + date1 + "统计新增" + (XDai2 - XDai1) + "个）\n" +
                "avax：" + avax2 + "个 （相较" + date1 + "统计新增" + (avax2 - avax) + "个）\n" +
                "arb：" + arb2 + "个 （相较" + date1 + "统计新增" + (arb2 - arb) + "个）\n" +
                "op：" + op2 + "个 （相较" + date1 + "统计新增" + (op2 - op) + "个）\n" +
                "celo：" + celo2 + "个 （相较" + date1 + "统计新增" + (celo2 - celo) + "个）\n" +

                "共计：" + list2.size() + "个 （相较" + date1 + "统计新增" + (list2.size() - list1.size()) + "个）";


        System.out.println(report);

        List<DebankSupportProtocal> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(Collectors.toList());

        reduce2.stream().collect(Collectors.groupingBy(DebankSupportProtocal::getChain)).forEach((key, list) -> {
            System.out.println("链：" + key);
            list.stream().forEach(o -> {
                System.out.println("\t" + o.getNameEn());
            });
        });


    }

    @Test
    public void dappradarDownload() {
        String urlPage1 = "https://dappradar.com/v2/api/dapps?params=WkdGd2NISmhaR0Z5Y0dGblpUMHlKbk5uY205MWNEMXRZWGdtWTNWeWNtVnVZM2s5VlZORUptaHBaR1V0WVdseVpISnZjSE05TUNabVpXRjBkWEpsWkQweEpuSmhibWRsUFdSaGVTWndjbTkwYjJOdmJEMWxkR2hsY21WMWJTWnpiM0owUFhWelpYSW1iM0prWlhJOVpHVnpZeVpzYVcxcGREMHlOZz09";
//        String urlPage1 = "https://dappradar.com/v2/api/dapps?params=WkdGd2NISmhaR0Z5Y0dGblpUMHhKbk5uY205MWNEMXRZWGdtWTNWeWNtVnVZM2s5VlZORUptaHBaR1V0WVdseVpISnZjSE05TUNabVpXRjBkWEpsWkQweEpuQnliM1J2WTI5c1BXVjBhR1Z5WlhWdEpuTnZjblE5ZFhObGNpWnZjbVJsY2oxa1pYTmpKbXhwYldsMFBUSTI=";
        String html1 = HttpUtils.getHtml(urlPage1);
        html1 = html1.replace("%", "");
        DappradarReturn dappradarReturn1 = JSON.parseObject(html1, DappradarReturn.class);
        List<Dapps> dapps1 = dappradarReturn1.getDapps();
        dapps1.remove(0);
        LinkedList<Dapps> dappradarApps = new LinkedList<>();
        dappradarApps.addAll(dapps1);

        dappradarApps.forEach(data -> {
            DappradarApp record = new DappradarApp();
            record.setDappradarId(data.getId() + "");
            String deepLink = data.getDeepLink();
//            String siteUrl = HttpUtils.getRealLink(deepLink);
//            System.out.println(data.getName() + ",deepLink:" + deepLink + ",siteUrl:" + siteUrl);
            record.setDeeplink(deepLink);
//            record.setSiteUrl(ClearNullUtil.formatWebsite(siteUrl));
            record.setCategory(data.getCategory().trim());
            record.setName(data.getName().replace(".Finance", "").replace(".finance", "").replace("Finance", "").replace("finance", "").trim());
            record.setProtocal("eth");
            record.setSpiderDate(DateUtil.getDate());
            debankService.insetDappradarRecord(record);

        });
    }

    @Test
    public void dappradarDeepLink() {

        List<DappradarApp> dappradarApps = debankService.selectDappradarByDate("2021-09-12");
        AtomicInteger i = new AtomicInteger();
        dappradarApps.forEach(data -> {
            i.getAndIncrement();
            System.out.println("当前：" + i);
            try {
                String siteUrl = HttpUtils.getRealLink(data.getDeeplink());
                System.out.println(data.getName() + ",deepLink:" + data.getDeeplink() + ",siteUrl:" + siteUrl);
                data.setSiteUrl(siteUrl);

                debankService.updateDappradarRecord(data);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    @Test
    public void bscAppdownload() {
        String urlPage1 = "https://api.staked.xyz/apiBscProject/getBscProjectTokensRank?v=2.0&tagID=0&sort=tvl&order=desc&limit=50&page=1";

        String html1 = HttpUtils.getHtml(urlPage1);
        BscReturn bscReturn = JSON.parseObject(html1, BscReturn.class);
        bscReturn.getData().getList().forEach(single -> {
            BscApps bscApps = new BscApps();
            bscApps.setBscId(single.getID());
            String detailUrl = "https://api.staked.xyz/apiBscProject/getProjectById?v=1.0&id=" + single.getID();
//            String htmlDetail = HttpUtils.getHtml(detailUrl);
//            BscApiReturn bscApiReturn = JSON.parseObject(htmlDetail, BscApiReturn.class);
//            bscApps.setSiteUrl(ClearNullUtil.formatWebsite(bscApiReturn.getData().getWebsite()));
            bscApps.setName(single.getTitle().trim());
            bscApps.setCategory(single.getTags().toString());
            bscApps.setSpiderDate(DateUtil.getDate());
            debankService.insertBscAppRecord(bscApps);

        });
    }

    public void getBscProjectInfoTest() throws IOException {
        String id = 500 + "";
        String url = "https://bscproject.org/#/project/" + id;

        Document doc = Jsoup.parse(new File(url), "UTF-8");
        //System.out.println(doc);

        //根据id获取元素getElementById
        Element element = doc.getElementById("city_bj");
        String text = element.text();
        //System.out.println(text);
        //根据标签获取元素getElementsByTag
        Elements elements = doc.getElementsByTag("title");
        Element titleElement = elements.first();
        String title = titleElement.text();
        //System.out.println(title);
        //根据class获取元素getElementsByClass
        Element element1 = doc.getElementsByClass("s_name").last();
        //System.out.println(element1.text());
        //根据属性获取元素
        String abc = doc.getElementsByAttribute("abc").first().text();
        System.out.println(abc);
    }
}
