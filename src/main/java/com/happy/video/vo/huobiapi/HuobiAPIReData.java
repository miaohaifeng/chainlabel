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
public class HuobiAPIReData {

    private String currency;
    private int assetType;
    private List<HuobiAPIChains> chains;
    private String instStatus;
    public void setCurrency(String currency) {
         this.currency = currency;
     }
     public String getCurrency() {
         return currency;
     }

    public void setAssetType(int assetType) {
         this.assetType = assetType;
     }
     public int getAssetType() {
         return assetType;
     }

    public void setChains(List<HuobiAPIChains> chains) {
         this.chains = chains;
     }
     public List<HuobiAPIChains> getChains() {
         return chains;
     }

    public void setInstStatus(String instStatus) {
         this.instStatus = instStatus;
     }
     public String getInstStatus() {
         return instStatus;
     }

}