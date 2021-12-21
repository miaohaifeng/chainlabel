package com.happy.video.pojo;

import java.util.Date;

public class DappradarApp {
    private String dappradarId;

    private String name;

    private String category;

    private String protocal;

    private String spiderDate;

    private String siteUrl;

    private Date createTime;

    private String deeplink;

    private String chain;

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getDappradarId() {
        return dappradarId;
    }

    public void setDappradarId(String dappradarId) {
        this.dappradarId = dappradarId == null ? null : dappradarId.trim();
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

    public String getProtocal() {
        return protocal;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal == null ? null : protocal.trim();
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

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink == null ? null : deeplink.trim();
    }
}