/**
  * Copyright 2021 bejson.com 
  */
package com.happy.video.vo.dappradar;

/**
 * Auto-generated: 2021-08-11 20:17:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Dapps {

    private int id;
    private String name;
    private String slug;

//    private Statistic statistic;
    private String godzillaId;
    private String logo;
    private String deepLink;
    private boolean mobileFriendly;
    private boolean featured;
    private String protocol;
    private String protocolSlug;
    private String category;
    private boolean tracked;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setSlug(String slug) {
         this.slug = slug;
     }
     public String getSlug() {
         return slug;
     }

//    public void setStatistic(Statistic statistic) {
//         this.statistic = statistic;
//     }
//     public Statistic getStatistic() {
//         return statistic;
//     }

    public void setGodzillaId(String godzillaId) {
         this.godzillaId = godzillaId;
     }
     public String getGodzillaId() {
         return godzillaId;
     }

    public void setLogo(String logo) {
         this.logo = logo;
     }
     public String getLogo() {
         return logo;
     }

    public void setDeepLink(String deepLink) {
         this.deepLink = deepLink;
     }
     public String getDeepLink() {
         return deepLink;
     }

    public void setMobileFriendly(boolean mobileFriendly) {
         this.mobileFriendly = mobileFriendly;
     }
     public boolean getMobileFriendly() {
         return mobileFriendly;
     }

    public void setFeatured(boolean featured) {
         this.featured = featured;
     }
     public boolean getFeatured() {
         return featured;
     }

    public void setProtocol(String protocol) {
         this.protocol = protocol;
     }
     public String getProtocol() {
         return protocol;
     }

    public void setProtocolSlug(String protocolSlug) {
         this.protocolSlug = protocolSlug;
     }
     public String getProtocolSlug() {
         return protocolSlug;
     }

    public void setCategory(String category) {
         this.category = category;
     }
     public String getCategory() {
         return category;
     }

    public void setTracked(boolean tracked) {
         this.tracked = tracked;
     }
     public boolean getTracked() {
         return tracked;
     }

}