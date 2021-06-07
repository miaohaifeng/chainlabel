package com.happy.video.pojo;

import java.util.Date;

public class ExchangeInfo {
    private Integer id;

    private String no;

    private String nameZh;

    private String nameEn;

    private String supervisorStatus;

    private String platformToken;

    private String registeredCountry;

    private String registeredCountryIconUrl;

    private String tokenNumber;

    private String setUpTime;

    private String intro;

    private String website;

    private String labels;

    private String exchangeTypes;

    private String socialMedias;

    private String groupRelationship;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh == null ? null : nameZh.trim();
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    public String getSupervisorStatus() {
        return supervisorStatus;
    }

    public void setSupervisorStatus(String supervisorStatus) {
        this.supervisorStatus = supervisorStatus == null ? null : supervisorStatus.trim();
    }

    public String getPlatformToken() {
        return platformToken;
    }

    public void setPlatformToken(String platformToken) {
        this.platformToken = platformToken == null ? null : platformToken.trim();
    }

    public String getRegisteredCountry() {
        return registeredCountry;
    }

    public void setRegisteredCountry(String registeredCountry) {
        this.registeredCountry = registeredCountry == null ? null : registeredCountry.trim();
    }

    public String getRegisteredCountryIconUrl() {
        return registeredCountryIconUrl;
    }

    public void setRegisteredCountryIconUrl(String registeredCountryIconUrl) {
        this.registeredCountryIconUrl = registeredCountryIconUrl == null ? null : registeredCountryIconUrl.trim();
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber == null ? null : tokenNumber.trim();
    }

    public String getSetUpTime() {
        return setUpTime;
    }

    public void setSetUpTime(String setUpTime) {
        this.setUpTime = setUpTime == null ? null : setUpTime.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels == null ? null : labels.trim();
    }

    public String getExchangeTypes() {
        return exchangeTypes;
    }

    public void setExchangeTypes(String exchangeTypes) {
        this.exchangeTypes = exchangeTypes == null ? null : exchangeTypes.trim();
    }

    public String getSocialMedias() {
        return socialMedias;
    }

    public void setSocialMedias(String socialMedias) {
        this.socialMedias = socialMedias == null ? null : socialMedias.trim();
    }

    public String getGroupRelationship() {
        return groupRelationship;
    }

    public void setGroupRelationship(String groupRelationship) {
        this.groupRelationship = groupRelationship == null ? null : groupRelationship.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}