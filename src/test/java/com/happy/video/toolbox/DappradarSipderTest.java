package com.happy.video.toolbox;

import com.happy.video.toolbox.util.HttpUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.Random;

public class DappradarSipderTest {
    @Test
    public void exchangeInfoTest() {


        String html = HttpUtils.getHtml("https://dappradar.com/rankings/category/games/5");
        Document doc = Jsoup.parse(html);
        String text6 = doc.select(".rankings-table").text();


        Elements elementsUl = doc.select("#root > div.App.lang-cn > div.rankings-page > div.Container.css-119jrnd > section > div.sc-iboAsT.kOeftV > div.sc-cBIieI.jfaXkA.rankings-table > a");
        String text = doc.select("rankings-table").text();
        Elements select = doc.select("rankings-table > a");
//            System.out.println(elementsUl.toString());
//            Elements ElementsUl = doc.getElementsByTag("ul");
        for (Element elementLi : elementsUl) {
            Element a = elementLi.select("a").first();
            String href = a.attr("href");

            System.out.println("href:" + href);

        }
    }

    @Test
    public void getDeeplinkTest() {
        String url = HttpUtils.getHtml("https://dappradar.com/deeplink/4096");
    }
}
