package com.happy.video;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.happy.video.pojo.*;
import com.happy.video.service.impl.SpiderBlockServiceImpl;
import com.happy.video.toolbox.util.FileUtils;
import com.happy.video.toolbox.util.HttpUtils;
import com.happy.video.toolbox.util.SleepUtils;
import com.happy.video.vo.Root;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderBlockServiceTest {

    @Autowired
    private SpiderBlockServiceImpl spiderBlockService;


    @Test
    public void combinedTest() {
        List<CexInfo> cexInfos = spiderBlockService.selectAllCex();
        HashMap<String, CexInfo> webSiteCexMap = new HashMap<>();
        cexInfos.forEach(cexInfo -> {
            try {
                String[] split = cexInfo.getOfficialUrl().split("/");
                webSiteCexMap.put(split[2], cexInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        HashMap<String, ExchangeInfo> webSiteExchangeInfoMap = new HashMap<>();

        List<ExchangeInfo> exchangeInfos = spiderBlockService.selectAllExchangeInfo();
        exchangeInfos.forEach(exchangeInfo -> {
            try {
                String website = exchangeInfo.getWebsite();
                List<String> strings = JSON.parseArray(website, String.class);
                HashSet<String> set = new HashSet<>();
                strings.forEach(s -> {
                    String[] split = s.split("/");
                    set.add(split[2]);
                });
                webSiteExchangeInfoMap.put(set.toArray()[0].toString(), exchangeInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        webSiteExchangeInfoMap.forEach((webSiteUrl, exchangeInfo) -> {
            if (webSiteCexMap.containsKey(webSiteUrl)) {
                try {
                    CexInfo cexInfo = webSiteCexMap.get(webSiteUrl);

                    String socialMedias = exchangeInfo.getSocialMedias();

                    ContractInfo contractInfo = JSON.parseObject(socialMedias, new TypeReference<ContractInfo>() {
                    });
                    contractInfo.setFacebook(cexInfo.getFacebookUrl());
                    contractInfo.setRedditlink(cexInfo.getRedditlink());
                    contractInfo.setTelegram(cexInfo.getTelegramlink());
                    contractInfo.setTwitter(cexInfo.getTwitterUrl());
                    contractInfo.setWeibo(cexInfo.getWeiboUrl());

                    exchangeInfo.setSocialMedias(JSON.toJSONString(contractInfo));

                    exchangeInfo.setExchangeTypes(JSON.toJSONString(cexInfo.getLabels().split(",")));
                    exchangeInfo.setIntro(cexInfo.getIntro().replace("</p>", "").replace("<p>", ""));

                    exchangeInfo.setSetUpTime(cexInfo.getLaunchedtime());
                    exchangeInfo.setTokenNumber(cexInfo.getPairnum());

                    spiderBlockService.updateBExchangeInfo(exchangeInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Test
    public void spiderExchangeInfoTest() {
        List<ExchangeNumber> exchangeNumbers = spiderBlockService.selectAllExchangeNumber();

        HashSet<String> exchangeNoSet = new HashSet<>();

        exchangeNumbers.forEach(exchangeNumber -> {
            exchangeNoSet.add(exchangeNumber.getExchange());
        });

        final int[] i = {0};
        exchangeNoSet.forEach(exchangeNumber -> {
            i[0]++;
            System.out.println(exchangeNoSet.size() + "," + i[0] + "," + exchangeNumber);
            try {
                ExchangeInfo exchangeInfo = getExchangeInfo(exchangeNumber);
                SleepUtils.sleep();
                spiderBlockService.insertExchangeInfo(exchangeInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }


        });
    }

    @Test
    public void spiderExchangeInfoUpdateTest() {
        List<ExchangeInfo> exchangeInfos = spiderBlockService.selectAllExchangeInfo();
        AtomicInteger i = new AtomicInteger();

        exchangeInfos.forEach(exchangeInfo -> {

            i.getAndIncrement();
            System.out.println(exchangeInfos.size()+","+ i);
            try {
                SleepUtils.sleep();
                String no = exchangeInfo.getNo();

                String html = HttpUtils.getHtml("https://www.wikibit.cn/Ajax/GetTransType?evaluationCode=" + no);


                Root data = JSON.parseObject(html, Root.class);
                HashSet<String> set = new HashSet<>();
                data.getData().forEach(u -> {
                    if (u.getIsselect() == true) {
                        set.add(u.getName());
                    }
                });

                exchangeInfo.setExchangeTypes(JSON.toJSONString(set));

                spiderBlockService.updateBExchangeInfo(exchangeInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    @Test
    public void spiderAllSuperviorInfo() {
        List<ExchangeNumber> exchangeNumbers = spiderBlockService.selectAllExchangeNumber();

        HashSet<String> exchangeNoSet = new HashSet<>();

        exchangeNumbers.forEach(exchangeNumber -> {
            exchangeNoSet.add(exchangeNumber.getExchange());
        });

        AtomicInteger i = new AtomicInteger();
        exchangeNoSet.forEach(exchangeNumber -> {
            i.getAndIncrement();
            try {
                LinkedList<SupervisorInfo> supervisorInfoList = getSupervisorInfoList(exchangeNumber);
                SleepUtils.sleep();
                System.out.println("休眠" + i + "次" + exchangeNoSet.size());
                for (int j = 0; j < supervisorInfoList.size(); j++) {
                    try {
                        spiderBlockService.insert(supervisorInfoList.get(j));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void downloadLicenseFileTest() throws InterruptedException {

        String savePath = "/Users/haifeng/download/licensefile";

        List<SupervisorInfo> supervisorInfos = spiderBlockService.selectAllSupervisorInfo();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < supervisorInfos.size(); i++) {
            SupervisorInfo supervisorInfo = supervisorInfos.get(i);
            if (set.contains(supervisorInfo.getLicenceNumber())) {
                continue;
            }
            SleepUtils.sleep();
            FileUtils.downLoadFromUrl(supervisorInfo.getLicenceFileUrl(), supervisorInfo.getLicenceNumber() + ".png", savePath);
            set.add(supervisorInfo.getLicenceNumber());
        }
    }

    @Test
    public void downloadSuperviseCountryTest() throws InterruptedException {

        String savePath = "/Users/haifeng/download/supervisecountry";

        List<SupervisorInfo> supervisorInfos = spiderBlockService.selectAllSupervisorInfo();
        Set<String> countySet = new HashSet<>();

        for (int i = 0; i < supervisorInfos.size(); i++) {
            SupervisorInfo supervisorInfo = supervisorInfos.get(i);

            countySet.add(supervisorInfo.getSuperviseCountry() + "-" + supervisorInfo.getSuperviseCountryIconUrl());
        }

        Iterator<String> iterator = countySet.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            i++;
            System.out.println(countySet.size() + "," + i);
            String next = iterator.next();
            String[] split = next.split("-");
            SleepUtils.sleep();
            FileUtils.downLoadFromUrl(split[1], split[0] + ".png", savePath);
        }

    }

    @Test
    public void downloadLicensereleaseunitTest() throws InterruptedException {

        String savePath = "/Users/haifeng/download/licensereleaseunit";

        List<SupervisorInfo> supervisorInfos = spiderBlockService.selectAllSupervisorInfo();
        Set<String> set = new HashSet<>();

        for (int i = 0; i < supervisorInfos.size(); i++) {
            SupervisorInfo supervisorInfo = supervisorInfos.get(i);

            set.add(supervisorInfo.getLicenseReleaseUnit() + "-" + supervisorInfo.getLicenseReleaseUnitIconUrl());
        }

        Iterator<String> iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            i++;
            System.out.println(set.size() + "," + i);
            String next = iterator.next();
            String[] split = next.split("-");
            SleepUtils.sleep();
            FileUtils.downLoadFromUrl(split[1], split[0] + ".png", savePath);
        }

    }


    @Test
    public void Test000() throws InterruptedException {


        LinkedList<SupervisorInfo> supervisorInfoList = getSupervisorInfoList("");

        System.out.println(JSON.toJSONString(supervisorInfoList));

    }

    @Test
    public void Test001() throws InterruptedException {

        List<String> exchange = get200Exchange();

        for (int i = 0; i < exchange.size(); i++) {
            LinkedList<SupervisorInfo> supervisorInfoList = getSupervisorInfoList(exchange.get(i));
            SleepUtils.sleep();
            System.out.println("休眠" + i + "次");
            for (int j = 0; j < supervisorInfoList.size(); j++) {
                try {
                    spiderBlockService.insert(supervisorInfoList.get(j));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }

    @Test
    public void exchangeSearchTest() throws InterruptedException, IOException {

        List<CexInfo> cexInfos = spiderBlockService.selectAllCex();

        HashSet<String> platformSet = new HashSet<>();
        HashSet<String> platformNameSet = new HashSet<>();

        for (CexInfo cexInfo : cexInfos) {
            if (StringUtils.isNoneEmpty(cexInfo.getPlatform())) {
                platformSet.add(cexInfo.getPlatform());
            }
            if (StringUtils.isNoneEmpty(cexInfo.getPlatformName())) {
                platformNameSet.add(cexInfo.getPlatformName());
            }
        }
        AtomicInteger i = new AtomicInteger();
        platformSet.forEach(platform -> {
            i.getAndIncrement();
            System.out.println("platformSet:" + platformSet.size() + "," + i);
            try {
                String html = HttpUtils.getHtml("https://www.wikibit.cn/search.html?keyword=" + platform);
                SleepUtils.sleep();
                Document doc = Jsoup.parse(html);
//                Document doc = Jsoup.parse(new File("/Users/haifeng/Desktop/ok/spider/kraken-search.html"), "UTF-8");
                Elements elementsUl = doc.select(".isJYS");
                for (Element elementLi : elementsUl) {

                    String dataCode = elementLi.attr("data-code");
                    if (StringUtils.isNoneEmpty(dataCode)) {
                        ExchangeNumber exchangeNumber = new ExchangeNumber();
                        exchangeNumber.setExchange(dataCode);
                        exchangeNumber.setPlatform(platform);
                        spiderBlockService.insertExchangeNumber(exchangeNumber);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        AtomicInteger j = new AtomicInteger();
        platformNameSet.forEach(platformName -> {
            j.getAndIncrement();
            System.out.println("platformNameSet:" + platformNameSet.size() + "," + j);
            try {
                String html = HttpUtils.getHtml("https://www.wikibit.cn/search.html?keyword=" + platformName);
                SleepUtils.sleep();
                Document doc = Jsoup.parse(html);
//                Document doc = Jsoup.parse(new File("/Users/haifeng/Desktop/ok/spider/kraken-search.html"), "UTF-8");
                Elements elementsUl = doc.select(".isJYS");
                for (Element elementLi : elementsUl) {

                    String dataCode = elementLi.attr("data-code");
                    if (StringUtils.isNoneEmpty(dataCode)) {
                        ExchangeNumber exchangeNumber = new ExchangeNumber();
                        exchangeNumber.setExchange(dataCode);
                        exchangeNumber.setPlatformName(platformName);
                        spiderBlockService.insertExchangeNumber(exchangeNumber);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


    public LinkedList<SupervisorInfo> getSupervisorInfoList(String exchangeNo) {

        LinkedList<SupervisorInfo> supervisorInfoList = new LinkedList<>();

        try {
            String html = HttpUtils.getHtml("https://www.wikibit.cn/dr/" + exchangeNo + ".html");
            Document doc = Jsoup.parse(html);
//            Document doc = Jsoup.parse(new File("/Users/haifeng/Desktop/ok/spider/1234849452489-huobi.html"), "UTF-8");

            String exchangeName = doc.select("#wrap > div.container > section.exchange_banner > div.banner_warp > div.banner_left > div.banner_left_desc > h3").first().text();
            Elements elementsUl = doc.select("#wrap > div.container > section.exchange_info > div > div.info_left_side > div.info_left_side_top > div.info_left_sign_warp > ul > li");


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
                holdLicenceInstitution = elementb0.select(".pop_cate_info_text").first().text();

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


                supervisorInfo.setExchangeName(exchangeName);
                supervisorInfo.setExchangeNo(exchangeNo);//TODO
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


//            System.out.println(JSON.toJSONString(supervisorInfoList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supervisorInfoList;
    }


    public List<String> get200Exchange() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1234849452489");
        list.add("1234454943364");
        list.add("1234792020107");
        list.add("1234529489012");
        list.add("1234580406412");
        list.add("1234742081322");
        list.add("1234356736953");
        list.add("1234762675341");
        list.add("1234121293763");
        list.add("1234958029798");
        list.add("1234824990299");
        list.add("1234414621559");
        list.add("1234510631701");
        list.add("1234161419234");
        list.add("1234967055295");
        list.add("1234182784513");
        list.add("1234270638964");
        list.add("1399229796120");
        list.add("1234886904897");
        list.add("1234276995624");
        list.add("1234138815629");
        list.add("1234915539883");
        list.add("1234991753264");
        list.add("1234423356373");
        list.add("1234392055443");
        list.add("1234922150312");
        list.add("1234498239538");
        list.add("1234185902607");
        list.add("1234651572460");
        list.add("1234860733012");
        list.add("1180789383420");
        list.add("1234103528227");
        list.add("1182300635620");
        list.add("1234440045511");
        list.add("1234323113411");
        list.add("1683449808202");
        list.add("1234530275184");
        list.add("1234273154228");
        list.add("1364096446520");
        list.add("1234323787802");
        list.add("1234589987299");
        list.add("1718410313202");
        list.add("1234404192179");
        list.add("1234748474362");
        list.add("1234732339081");
        list.add("1422021597020");
        list.add("1234958868973");
        list.add("1234358282867");
        list.add("1234463153695");
        list.add("1234748544138");
        list.add("1234802409537");
        list.add("1234437275944");
        list.add("1234611517222");
        list.add("1234560959183");
        list.add("1234296561320");
        list.add("1234821929327");
        list.add("1580000628202");
        list.add("1234108098536");
        list.add("1234171604357");
        list.add("1234750121194");
        list.add("1234510572664");
        list.add("1234759535332");
        list.add("1234776824136");
        list.add("1234924418189");
        list.add("1246828267620");
        list.add("1234946837242");
        list.add("1234787326787");
        list.add("1234707793012");
        list.add("1234367383322");
        list.add("1234179619700");
        list.add("1234895219097");
        list.add("1234581200962");
        list.add("1234259277437");
        list.add("1370053909420");
        list.add("1336732713720");
        list.add("1234183768572");
        list.add("1234365271508");
        list.add("1165557399220");
        list.add("1234179418132");
        list.add("1234858957822");
        list.add("1414386338620");
        list.add("1234135048382");
        list.add("1234700026710");
        list.add("1234282504803");
        list.add("1386948557220");
        list.add("1234827466390");
        list.add("1347404288520");
        list.add("1234687350841");
        list.add("1814698950202");
        list.add("1676050598202");
        list.add("1234822440602");
        list.add("1234338194812");
        list.add("1234944437306");
        list.add("1234762159953");
        list.add("1176315519520");
        list.add("1234375635801");
        list.add("1130620863120");
        list.add("1252983464920");
        list.add("1327602390020");
        list.add("1151604355120");
        list.add("1234130314442");
        list.add("1234871684812");
        list.add("1234920668551");
        list.add("1234497240542");
        list.add("1234308468612");
        list.add("1234136379161");
        list.add("1234116956414");
        list.add("1199158716620");
        list.add("1234995151176");
        list.add("1234825192074");
        list.add("1234441388172");
        list.add("1113508725720");
        list.add("1998686717202");
        list.add("1452806340202");
        list.add("1234574789727");
        list.add("1374455108120");
        list.add("1417976301420");
        list.add("1234440617402");
        list.add("1411898649020");
        list.add("1295708923720");
        list.add("1303377177520");
        list.add("1375632314920");
        list.add("1265992279920");
        list.add("1370866109320");
        list.add("1234362071887");
        list.add("1672424912202");
        list.add("1235328149620");
        list.add("1234713796011");
        list.add("1234162436150");
        list.add("1199163092520");
        list.add("1402625315920");
        list.add("1234966574804");
        list.add("1234389208629");
        list.add("1234393087369");
        list.add("1234109294517");
        list.add("1247368536420");
        list.add("1125692229120");
        list.add("1316510550420");
        list.add("1810316829202");
        list.add("1307802303420");
        list.add("1187164644220");
        list.add("1234850720202");
        list.add("1234598071754");
        list.add("1418247850720");
        list.add("1329581321320");
        list.add("1234486894943");
        list.add("1296792643820");
        list.add("1234255551322");
        list.add("1193488629620");
        list.add("1156853084320");
        list.add("1421960230220");
        list.add("1450584633202");
        list.add("1234199052309");
        list.add("1234569992061");
        list.add("1349114465120");
        list.add("1234462136293");
        list.add("1259684399120");
        list.add("1129014139220");
        list.add("1234792592746");
        list.add("1234317356063");
        list.add("1234838790252");
        list.add("1274119601120");
        list.add("1322856589620");
        list.add("1391348020220");
        list.add("1234107565924");
        list.add("1234379333822");
        list.add("1147888619420");
        list.add("1103173776202");
        list.add("1234988105967");
        list.add("1234593318447");
        list.add("1286173259820");
        list.add("1234669593898");
        list.add("1234817803365");
        list.add("1380093692420");
        list.add("1234268298734");
        list.add("1234691813072");
        list.add("1234540698083");
        list.add("1234766952890");
        list.add("1378964469720");
        list.add("1393254773720");
        list.add("1234520617995");
        list.add("1234264089174");
        list.add("1234476157981");
        list.add("1166509536120");
        list.add("1234419793492");
        list.add("1234302688735");
        list.add("1234622708043");
        list.add("1234880035035");
        list.add("1241445664120");
        list.add("1234465246260");
        list.add("1234938355101");
        list.add("1151677043920");
        list.add("1425739731920");
        list.add("1234668581562");
        list.add("1234232305277");
        list.add("1234669434989");
        list.add("1234133166896");
        list.add("1317443399720");
        list.add("1234219904979");
        list.add("1179303005320");
        return list;
    }

    public ExchangeInfo getExchangeInfo(String no) {
        String nameZh = null;
        String nameEn = null;
        String platformToken = null;
        String supervisorStatus = "";
        String registeredCountry = null;
        String registeredCountryIconUrl = null;
        String tokenNumber = null;
        String setUpTime = null;
        String intro = null;
        String website = null;
        String labels = null;
        String exchangeTypes = null;
        String socialMedias = null;
        String groupRelationship = null;

        String html = HttpUtils.getHtml("https://www.wikibit.cn/dr/" + no + ".html");
        Document doc = Jsoup.parse(html);
        nameZh = doc.select("#wrap > div.container > section.exchange_info > div > div.info_left_side > div.info_left_side_top > div.exchange_info_text_warp > div:nth-child(1) > strong").first().text();
        nameEn = doc.select("#wrap > div.container > section.exchange_info > div > div.info_left_side > div.info_left_side_top > div.exchange_info_text_warp > div:nth-child(2) > a").first().text();
        supervisorStatus = doc.select("#wrap > div.container > section.exchange_info > div > div.info_left_side > div.info_left_side_top > div.exchange_info_text_warp > div:nth-child(4) > strong").first().text();
        registeredCountry = doc.select("#wrap > div.container > section.exchange_info > div > div.info_left_side > div.info_left_side_top > div.exchange_info_text_warp > div:nth-child(3) > strong").first().text();
        registeredCountryIconUrl = doc.select("#wrap > div.container > section.exchange_info > div > div.info_left_side > div.info_left_side_top > div.exchange_info_text_warp > div:nth-child(3) > strong").first().select("img").first().attr("src");

        String email = doc.select("#wrap > div.container > section.exchange_info > div > div.info_left_side > div.info_left_side_top > div.exchange_info_text_warp > div:nth-child(5) > strong").first().text();
        String telephone = doc.select("#wrap > div.container > section.exchange_info > div > div.info_left_side > div.info_left_side_top > div.exchange_info_text_warp > div:nth-child(6) > strong").first().text();

        ContractInfo contractInfo = new ContractInfo(email, telephone);
        socialMedias = JSON.toJSONString(contractInfo);

        Set<String> webSiteList = new HashSet<>();
        Elements elementsa = doc.select("#wrap > div.container > section.exchange_banner > div.banner_warp > div.banner_left > div.banner_left_desc > div.exchang_link_warp > div:nth-child(1) > div.link_item_content > div > div > a");
        for (int i = 0; i < elementsa.size(); i++) {
            String websitSingle = elementsa.get(i).text();
            webSiteList.add(websitSingle);
        }
        String webSiteCurrent = doc.select("#wrap > div.container > section.exchange_banner > div.banner_warp > div.banner_left > div.banner_left_desc > div.exchang_link_warp > div:nth-child(1) > div.link_item_top > span > a").first().text();
        webSiteList.add(webSiteCurrent);
        website = JSON.toJSONString(webSiteList);

        Set<String> labelList = new HashSet<>();
        Elements elementsLabel = doc.select("#wrap > div.container > section.exchange_banner > div.banner_warp > div.banner_left > div.banner_left_desc > p.exchange_label > i");
        for (int i = 0; i < elementsLabel.size(); i++) {
            String label = elementsLabel.get(i).text();
            labelList.add(label);
        }
        labels = JSON.toJSONString(labelList);

        Elements elementExchangeType = doc.select("#wrap > div.content > div > div:nth-child(8) > div.dialog_body > div");
        Set<String> exchangeTypeList = new HashSet<>();
        for (int i = 0; i < elementExchangeType.size(); i++) {
            Element element = elementExchangeType.get(i);
            if (element.attr("class").contains("label_active")) {
                exchangeTypeList.add(element.text());
            }
        }
        exchangeTypes = JSON.toJSONString(exchangeTypeList);

        try {
            GroupRelationship gr = new GroupRelationship();
            String parentChildFlag = doc.select("#wrap > div.container > section.exchange_banner > div.banner_warp > div.banner_left > div.banner_left_desc > div.exchang_link_warp > div:nth-child(2) > div.link_item_top > span > span").first().text();
            if ("所有子公司".equals(parentChildFlag)) {

                HashSet<String> child = new HashSet<>();
                Elements select = doc.select("#wrap > div.container > section.exchange_banner > div.banner_warp > div.banner_left > div.banner_left_desc > div.exchang_link_warp > div:nth-child(2) > div.link_item_content > div > div > a");
                for (int i = 0; i < select.size(); i++) {
                    String href = select.get(i).attr("href").replace(".html", "").replace("/dr/", "");
                    child.add(href);
                }
                gr.setChild(child);
            } else if ("隶属于".equals(parentChildFlag)) {
                String href = doc.select("#wrap > div.container > section.exchange_banner > div.banner_warp > div.banner_left > div.banner_left_desc > div.exchang_link_warp > div:nth-child(2) > div.link_item_top > span > a").first().select("a").first().attr("href").replace(".html", "").replace("/dr/", "");
                gr.setParent(href);
            }
            groupRelationship = JSON.toJSONString(gr);
        } catch (Exception e) {
            System.out.println("获取集团关系失败");
        }


        ExchangeInfo exchangeInfo = new ExchangeInfo();
        exchangeInfo.setNo(no);
        exchangeInfo.setNameZh(nameZh);
        exchangeInfo.setNameEn(nameEn);
        exchangeInfo.setSupervisorStatus(supervisorStatus);
        exchangeInfo.setRegisteredCountry(registeredCountry);
        exchangeInfo.setRegisteredCountryIconUrl(registeredCountryIconUrl);
        exchangeInfo.setWebsite(website);
        exchangeInfo.setLabels(labels);
        exchangeInfo.setExchangeTypes(exchangeTypes);
        exchangeInfo.setSocialMedias(socialMedias);
        exchangeInfo.setGroupRelationship(groupRelationship);
        return exchangeInfo;
    }

}
