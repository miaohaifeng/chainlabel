/**
  * Copyright 2021 bejson.com 
  */
package com.happy.video.vo.huobiapi;
import java.util.List;

/**
 * Auto-generated: 2021-10-08 16:39:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class HuobiAPIReturnData {

    private int code;
    private List<HuobiAPIReData> data;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setData(List<HuobiAPIReData> data) {
         this.data = data;
     }
     public List<HuobiAPIReData> getData() {
         return data;
     }

}