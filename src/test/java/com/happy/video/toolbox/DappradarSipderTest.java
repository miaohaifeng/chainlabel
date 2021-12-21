package com.happy.video.toolbox;

import com.happy.video.toolbox.util.HttpUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.Random;

public class DappradarSipderTest {
    @Test
    public void exchangeInfoTest() {


        String html = HttpUtils.getHtml("https://dappradar.com/defi/protocol/ethereum/1");
        Document doc = Jsoup.parse(html);
        String text6 = doc.select(".rankings-table").text();

        String elements = doc.select("#root > div > div.cms-page > div:nth-child(5) > section > div.sc-Fyfyc.sc-eFubAy.fTMjUf.iizEdg.rankings-table").text();
//        for (int i = 0; i < elements.size(); i++) {
//            String text = elements.get(i).text();
//            System.out.println(text);
//        }

        System.out.println(elements);
    }

    @Test
    public void getDeeplinkTest() {
        String url = HttpUtils.getHtml("https://dappradar.com/deeplink/4096");
    }
}
