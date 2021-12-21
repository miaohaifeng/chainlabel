package com.happy.video.toolbox.util;

import com.alibaba.fastjson.JSON;
import com.happy.video.toolbox.json.JsonUtil;
import com.happy.video.vo.debank.chart.Data;
import com.happy.video.vo.debank.chart.DeBankChartDataReturn;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.net.SocketException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author itcast
 * Date 2020/5/26 16:05
 * Desc 封装HttpClient工具,方便爬取网页内容
 */
public abstract class HttpUtils {
    private static PoolingHttpClientConnectionManager cm = null;//声明httpClient管理器对象(HttpClient连接池)
    private static RequestConfig config = null;
    private static List<String> userAgentList = null;
    static HttpHost proxy = new HttpHost("183.164.255.115", 443, "http");

    static int wait = 10;

    //静态代码块会在类被加载的时候执行
    static {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(1);

        config = RequestConfig.custom()
                .setSocketTimeout(wait * 1000)
                .setConnectTimeout(wait * 1000)
                .setConnectionRequestTimeout(wait * 1000)
//                .setProxy(proxy)
                .build();

        userAgentList = new ArrayList<String>();
        userAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        userAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:73.0) Gecko/20100101 Firefox/73.0");
        userAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.5 Safari/605.1.15");
        userAgentList.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299");
        userAgentList.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        userAgentList.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0");

    }

    public static String getHtml(String url) {
        //1.从连接池中获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //2.创建HttpGet对象
        HttpGet httpGet = new HttpGet(url);

        //3.设置请求配置对象和请求头
        httpGet.setConfig(config);
        httpGet.setHeader("User-Agent", userAgentList.get(new Random().nextInt(userAgentList.size())));
        //4.发起请求
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
            //5.获取响应内容
            if (response.getStatusLine().getStatusCode() == 200) {
                String html = "";
                if (response.getEntity() != null) {
                    html = EntityUtils.toString(response.getEntity(), "UTF-8");
                }
                return html;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
                //httpClient.close();//注意:这里的HttpClient是从cm(连接池)中获取的,不需要关闭
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getDeeplinkHost(String url) {
        String host = null;
        //1.从连接池中获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //2.创建HttpGet对象
        HttpGet httpGet = new HttpGet(url);

        //3.设置请求配置对象和请求头
        httpGet.setConfig(config);
        httpGet.setHeader("User-Agent", userAgentList.get(new Random().nextInt(userAgentList.size())));
        //4.发起请求
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
            System.out.println(JSON.toJSONString(response));
            Header[] allHeaders = response.getAllHeaders();
            HeaderElement[] elements = allHeaders[4].getElements();
            host = elements[0].getName();
            host = "https://" + host.replace("https://", "").split("/")[0];
            return host;
        } catch (HttpHostConnectException e) {
            String hostName = e.getHost().getHostName();
            String schemeName = e.getHost().getSchemeName();
            host = schemeName + "://" + hostName;
            return host;
        } catch (ConnectTimeoutException e) {
            String hostName = e.getHost().getHostName();
            String schemeName = e.getHost().getSchemeName();
            host = schemeName + "://" + hostName;
            return host;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }


    public static String getRealLink(String link) {

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpClientContext context = HttpClientContext.create();
        HttpGet httpget = new HttpGet(link);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget, context);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            HttpHost target = context.getTargetHost();
            List<URI> redirectLocations = context.getRedirectLocations();
            URI location = URIUtils.resolve(httpget.getURI(), target, redirectLocations);
            System.out.println("Final HTTP location: " + location.toASCIIString());
            return "https://" + location.toString().replace("https://", "").split("/")[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean deBankChartDataHas(String projectId, String type) {
        String dataUrl = "https://api.debank.com/project/chart?id=" + projectId + "&type=" + type;

        String dataTypeHtml = HttpUtils.getHtml(dataUrl);
        DeBankChartDataReturn deBankChartDataReturn = JSON.parseObject(dataTypeHtml, DeBankChartDataReturn.class);
        if (null == deBankChartDataReturn.getData() || deBankChartDataReturn.getData().getData().isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        String link = "https://dappradar.com/deeplink/3340?medium=general&campaign=clickout";
        String html = "";
//                html = HttpUtils.getDeeplinkHost(link);
//        System.out.println(html);

//        aaa(link);
        html = getRealLink(link);
        System.out.println(html);

    }
}
