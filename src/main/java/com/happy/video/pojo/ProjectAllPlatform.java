package com.happy.video.pojo;

import java.util.Date;

public class ProjectAllPlatform {
    private Integer id;

    private String uniqueSiteUrl;

    private String debankId;

    private String debankName;

    private String debankSiteUrl;

    private String bscId;

    private String bscName;

    private String bscSiteUrl;

    private String huobiIb;

    private String huobiName;

    private String huobiSiteUrl;

    private String dapparId;

    private String dapparName;

    private String dapparSiteUrl;

    private String chain;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniqueSiteUrl() {
        return uniqueSiteUrl;
    }

    public void setUniqueSiteUrl(String uniqueSiteUrl) {
        this.uniqueSiteUrl = uniqueSiteUrl == null ? null : uniqueSiteUrl.trim();
    }

    public String getDebankId() {
        return debankId;
    }

    public void setDebankId(String debankId) {
        this.debankId = debankId == null ? null : debankId.trim();
    }

    public String getDebankName() {
        return debankName;
    }

    public void setDebankName(String debankName) {
        this.debankName = debankName == null ? null : debankName.trim();
    }

    public String getDebankSiteUrl() {
        return debankSiteUrl;
    }

    public void setDebankSiteUrl(String debankSiteUrl) {
        this.debankSiteUrl = debankSiteUrl == null ? null : debankSiteUrl.trim();
    }

    public String getBscId() {
        return bscId;
    }

    public void setBscId(String bscId) {
        this.bscId = bscId == null ? null : bscId.trim();
    }

    public String getBscName() {
        return bscName;
    }

    public void setBscName(String bscName) {
        this.bscName = bscName == null ? null : bscName.trim();
    }

    public String getBscSiteUrl() {
        return bscSiteUrl;
    }

    public void setBscSiteUrl(String bscSiteUrl) {
        this.bscSiteUrl = bscSiteUrl == null ? null : bscSiteUrl.trim();
    }

    public String getHuobiIb() {
        return huobiIb;
    }

    public void setHuobiIb(String huobiIb) {
        this.huobiIb = huobiIb == null ? null : huobiIb.trim();
    }

    public String getHuobiName() {
        return huobiName;
    }

    public void setHuobiName(String huobiName) {
        this.huobiName = huobiName == null ? null : huobiName.trim();
    }

    public String getHuobiSiteUrl() {
        return huobiSiteUrl;
    }

    public void setHuobiSiteUrl(String huobiSiteUrl) {
        this.huobiSiteUrl = huobiSiteUrl == null ? null : huobiSiteUrl.trim();
    }

    public String getDapparId() {
        return dapparId;
    }

    public void setDapparId(String dapparId) {
        this.dapparId = dapparId == null ? null : dapparId.trim();
    }

    public String getDapparName() {
        return dapparName;
    }

    public void setDapparName(String dapparName) {
        this.dapparName = dapparName == null ? null : dapparName.trim();
    }

    public String getDapparSiteUrl() {
        return dapparSiteUrl;
    }

    public void setDapparSiteUrl(String dapparSiteUrl) {
        this.dapparSiteUrl = dapparSiteUrl == null ? null : dapparSiteUrl.trim();
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain == null ? null : chain.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}