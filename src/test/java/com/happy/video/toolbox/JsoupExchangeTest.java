package com.happy.video.toolbox;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.happy.video.pojo.ContractInfo;
import com.happy.video.pojo.ExchangeInfo;
import com.happy.video.pojo.GroupRelationship;
import com.happy.video.pojo.SupervisorInfo;
import com.happy.video.toolbox.util.HttpUtils;
import com.happy.video.vo.Data;
import com.happy.video.vo.Root;
import lombok.val;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Type;
import java.util.*;

public class JsoupExchangeTest {

    @Test
    public void parseTradeType() {
        String html = HttpUtils.getHtml("https://app.aave.com");


//        JSON.parseObject(html,new Feature[]
        Root data = JSON.parseObject(html, Root.class);
        HashSet<String> set = new HashSet<>();
        data.getData().forEach(u->{
            if(u.getIsselect() == true){
                set.add(u.getName());
            }
        });
        System.out.println(JSON.toJSONString(set));
    }

    @Test
    public void splitTest() {
        String name = "https://pro.coinbase.com/";
        String[] split = name.split("/");
        System.out.println(split);
    }

    @Test
    public void exchangeInfoTest() {


        String html = HttpUtils.getHtml("https://www.wikibit.cn/dr/1234400597851.html");
        Document doc = Jsoup.parse(html);

        Elements elementExchangeType = doc.select("#wrap > div.content > div > div:nth-child(7) > div.dialog_body > div").select("span");
        for (int i = 0; i < elementExchangeType.size(); i++) {
            String text = elementExchangeType.get(i).text();
            System.out.println(text);
        }

    }

    @Test
    public void ExchangeTest() {

        try {
//            String html = HttpUtils.getHtml("https://www.wikibit.cn/dr/1234849452489.html");
//            Document doc = Jsoup.parse(html);
            Document doc = Jsoup.parse(new File("/Users/haifeng/Desktop/ok/spider/200_exchange.html"), "UTF-8");

            Elements elementsUl = doc.select("#content > div > div:nth-child(2) > ul > li");

//            System.out.println(elementsUl.toString());
//            Elements ElementsUl = doc.getElementsByTag("ul");
            for (Element elementLi : elementsUl) {
                Element a = elementLi.select("a").first();
                String href = a.attr("href");
                String exchangeId = href.replace("https://www.wikibit.cn/dr/", "").replace(".html", "");
                String name = a.text();
                String sql = "INSERT INTO `exchange_number` VALUES ('" + name + "', null, '" + exchangeId + "');";

                System.out.println(sql);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void jianguanTest() {

        try {
//            String html = HttpUtils.getHtml("https://www.wikibit.cn/dr/1234849452489.html");
//            Document doc = Jsoup.parse(html);
            Document doc = Jsoup.parse(new File("/Users/haifeng/Desktop/ok/spider/1234849452489-huobi.html"), "UTF-8");

            Elements elementsUl = doc.select("#wrap > div.container > section.exchange_info > div > div.info_left_side > div.info_left_side_top > div.info_left_sign_warp > ul > li");

            LinkedList<SupervisorInfo> supervisorInfoList = new LinkedList<>();

            for (Element elementLi : elementsUl) {

                SupervisorInfo supervisorInfo = new SupervisorInfo();

                Element a = elementLi.select(".left_sign_item_name").first();

                //牌照类型
                String licenceType = null;
                //监管状态
                String currentSuperviseStatus = null;

                Element totalInfo = elementLi.select("div.pop_cate_header").first();
                //牌照发行单位icon
                String licenseReleaseUnitIconUrl = totalInfo.select(".pop_cate_header_l").first().select("img").attr("src");
                //牌照发行单位
                String licenseReleaseUnit = totalInfo.select(".pop_cate_header_r > h3").text();
                //牌照发行单位简介
                String licenseReleaseUnitDesc = totalInfo.select(".pop_cate_header_r > p").text();
                //监管国家
                String superviseCountry = null;
                //监管国家图标
                String superviseCountryIconUrl = null;
                //监管证号
                String supervisorNumber = null;


                Elements infoDetails = elementLi.select("div.pop_cate_xx > ul > li");

                Element element0 = infoDetails.get(0);
                currentSuperviseStatus = element0.select(".pop_cate_green_bg").text();

                Element element1 = infoDetails.get(1);
                licenceType = element1.select(".pop_cate_text").text();

                Element element2 = infoDetails.get(2);
                superviseCountryIconUrl = element2.select(".pop_cate_xx_icon").first().attr("src");
                superviseCountry = element2.select(".pop_cate_text").first().text();

                Element element3 = infoDetails.get(3);
                supervisorNumber = element3.select("span").last().text();


                //持牌机构
                String holdLicenceInstitution = null;
                //持牌机构图标
                String holdLicenceInstitutionIconUrl = null;

                String holdLicenceInstitutionPhone = null;

                String holdLicenceInstitutionEmail = null;

                String holdLicenceInstitutionWebsite = null;

                String holdLicenceInstitutionAddress = null;

                String licenceNumber = null;

                String licenceFileUrl = null;

                String startTime = null;

                String endTime = null;


                Elements institutionDetails = elementLi.select("div.pop_cate_info > ul > li");

                Element elementb0 = institutionDetails.get(0);
                holdLicenceInstitutionIconUrl = elementb0.select(".pop_cate_info_icon").first().attr("src");
                holdLicenceInstitution = elementb0.select(".pop_cate_info_text").first().attr("src");

                Element elementb1 = institutionDetails.get(1);
                startTime = elementb1.select(".pop_cate_info_text").first().text();

                Element elementb2 = institutionDetails.get(2);
                holdLicenceInstitutionEmail = elementb2.select(".pop_cate_info_text").first().text();

                Element elementb3 = institutionDetails.get(3);
//                licenceType = elementb3.select(".pop_cate_info_text").first().text();
                //集团下面的子交易所
                Elements jituanCompanies = elementb3.select(".share_pop_item");

                for (int j = 0; j < jituanCompanies.size(); j++) {
                    Element elementj0 = jituanCompanies.get(0);
                    String zidongsiUrl = elementj0.attr("href");
                    String zigongsiName = elementj0.text();
                }

                Element element4 = institutionDetails.get(4);
                holdLicenceInstitutionWebsite = element4.select(".pop_cate_info_text").first().text();

                Element element5 = institutionDetails.get(5);
                endTime = element5.select(".pop_cate_info_text").first().text();

                Element element6 = institutionDetails.get(6);
                holdLicenceInstitutionAddress = element6.select(".pop_cate_info_width_1").first().text();

                Element element7 = institutionDetails.get(7);
                holdLicenceInstitutionPhone = element7.select(".pop_cate_info_text").first().text();

                Element element8 = institutionDetails.get(8);
                licenceNumber = element8.select(".pop_cate_info_width_1").first().text().replace("[查看文件]", "");
                licenceFileUrl = element8.select("a").attr("href");


                supervisorInfo.setExchangeNo("");//TODO
                supervisorInfo.setLicenseReleaseUnit(licenseReleaseUnit);
                supervisorInfo.setLicenseReleaseUnitDesc(licenseReleaseUnitDesc);
                supervisorInfo.setLicenseReleaseUnitIconUrl(licenseReleaseUnitIconUrl);
                supervisorInfo.setCurrentSuperviseStatus(currentSuperviseStatus);
                supervisorInfo.setLicenceType(licenceType);
                supervisorInfo.setSuperviseCountry(superviseCountry);
                supervisorInfo.setSuperviseCountryIconUrl(superviseCountryIconUrl);
                supervisorInfo.setSupervisorNumber(supervisorNumber);
                supervisorInfo.setHoldLicenceInstitution(holdLicenceInstitution);
                supervisorInfo.setHoldLicenceInstitutionIcon(holdLicenceInstitutionIconUrl);
                supervisorInfo.setStartTime(startTime);
                supervisorInfo.setEndTime(endTime);
                supervisorInfo.setHoldLicenceInstitutionPhone(holdLicenceInstitutionPhone);
                supervisorInfo.setHoldLicenceInstitutionEmail(holdLicenceInstitutionEmail);
                supervisorInfo.setHoldLicenceInstitutionWebsite(holdLicenceInstitutionWebsite);
                supervisorInfo.setHoldLicenceInstitutionAddress(holdLicenceInstitutionAddress);
                supervisorInfo.setLicenceNumber(licenceNumber);
                supervisorInfo.setLicenceFileUrl(licenceFileUrl);

                supervisorInfoList.add(supervisorInfo);
            }


            System.out.println(JSON.toJSONString(supervisorInfoList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
