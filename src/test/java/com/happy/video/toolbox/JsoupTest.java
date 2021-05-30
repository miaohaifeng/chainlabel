package com.happy.video.toolbox;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;

/**
 * Author itcast
 * Date 2020/5/27 10:02
 * Desc 演示使用Jsoup实现页面解析
 */
public class JsoupTest {
    @Test
    public void testGetDoument() throws Exception {
        //Document doc = Jsoup.connect("http://www.itcast.cn/").get();
        //Document doc = Jsoup.parse(new URL("http://www.itcast.cn/"), 1000);
        //Document doc = Jsoup.parse(new File("jsoup.html"), "UTF-8");
        String htmlStr = FileUtils.readFileToString(new File("jsoup.html"), "UTF-8");
        Document doc = Jsoup.parse(htmlStr);
        System.out.println(doc);
        Element titleElement = doc.getElementsByTag("title").first();
        String title = titleElement.text();
        System.out.println(title);
    }
    @Test
    public void testGetElement() throws Exception {
        Document doc = Jsoup.parse(new File("jsoup.html"), "UTF-8");
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
    @Test
    public void testElementOperator() throws Exception {
        Document doc = Jsoup.parse(new File("jsoup.html"), "UTF-8");
        Element element = doc.getElementsByAttributeValue("class", "city_con").first();
        //获取元素中的id
        String id = element.id();
        System.out.println(id);
        //获取元素中的classname
        String className = element.className();
        System.out.println(className);
        //获取元素中的属性值
        String id1 = element.attr("id");
        System.out.println(id1);
        //获取元素中所有的属性
        String attrs = element.attributes().toString();
        System.out.println(attrs);
        //获取元素中的文本内容
        String text = element.text();
        System.out.println(text);
    }
    @Test
    public void testSelect() throws Exception {
        Document doc = Jsoup.parse(new File("jsoup.html"), "UTF-8");
        //根据标签名获取元素
        Elements spans = doc.select("span");
        for (Element span : spans) {
            //System.out.println(span.text());
        }
        //根据id获取元素
        String text = doc.select("#city_bj").text();
        //System.out.println(text);
        //根据class获取元素
        String text1 = doc.select(".class_a").text();
        //System.out.println(text1);
        //根据属性获取元素
        String text2 = doc.select("[abc]").text();
        //System.out.println(text2);
        //根据属性值获取元素
        String text3 = doc.select("[class=s_name]").text();
        System.out.println(text3);

    }

    @Test
    public void testSelect2() throws Exception {
        Document doc = Jsoup.parse(new File("jsoup.html"), "UTF-8");
        //根据标签名+id组合选取元素
        String text = doc.select("li#test").text();
        //System.out.println(text);
        //根据标签名+class
        String text1 = doc.select("li.class_a").text();
        //System.out.println(text1);
        //根据标签名+元素名
        String text2 = doc.select("span[abc]").text();
        //System.out.println(text2);
        //任意组合
        String text3 = doc.select("span[abc].s_name").text();
        //System.out.println(text3);
        //查找某个元素下的直接子元素
        String text4 = doc.select(".city_con > ul > li").text();
        //System.out.println(text4);
        //查找某个元素下的所有子元素
        String text5 = doc.select(".city_con li").text();
        //System.out.println(text5);
        //查找某个元素下的所有直接子元素
        String text6 = doc.select(".city_con > *").text();
        System.out.println(text6);
    }
}
