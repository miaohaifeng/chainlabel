package com.happy.video.pojo;

import java.util.Date;

public class BscApps {
    private String bscId;

    private String name;

    private String category;

    private String spiderDate;

    private String siteUrl;

    private Date createTime;

    public String getBscId() {
        return bscId;
    }

    public void setBscId(String bscId) {
        this.bscId = bscId == null ? null : bscId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getSpiderDate() {
        return spiderDate;
    }

    public void setSpiderDate(String spiderDate) {
        this.spiderDate = spiderDate == null ? null : spiderDate.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}