package com.happy.video.toolbox;

import com.happy.video.toolbox.util.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;

public class JsoupExchangeTest {

    @Test
    public void ExchangeTest() {

        try {
//            String html = HttpUtils.getHtml("https://www.wikibit.cn/forex.html");
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
                System.out.println(name + "," + href + "," + exchangeId);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
