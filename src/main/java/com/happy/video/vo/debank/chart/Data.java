/**
  * Copyright 2021 bejson.com 
  */
package com.happy.video.vo.debank.chart;
import java.util.List;

/**
 * Auto-generated: 2021-09-09 11:2:24
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private String count_24h;
    private List<Data> data;
    private String symbol;
    private String title;
    private String unit;
    public void setCount_24h(String count_24h) {
         this.count_24h = count_24h;
     }
     public String getCount_24h() {
         return count_24h;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setSymbol(String symbol) {
         this.symbol = symbol;
     }
     public String getSymbol() {
         return symbol;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setUnit(String unit) {
         this.unit = unit;
     }
     public String getUnit() {
         return unit;
     }

}