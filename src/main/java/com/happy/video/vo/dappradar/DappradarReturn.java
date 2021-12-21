/**
  * Copyright 2021 bejson.com 
  */
package com.happy.video.vo.dappradar;
import java.util.List;

/**
 * Auto-generated: 2021-08-11 20:17:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DappradarReturn {

    private int page;
    private int resultsPerPage;
    private int pageCount;
    private int resultCount;
    private List<Dapps> dapps;
    private Ad ad;
    public void setPage(int page) {
         this.page = page;
     }
     public int getPage() {
         return page;
     }

    public void setResultsPerPage(int resultsPerPage) {
         this.resultsPerPage = resultsPerPage;
     }
     public int getResultsPerPage() {
         return resultsPerPage;
     }

    public void setPageCount(int pageCount) {
         this.pageCount = pageCount;
     }
     public int getPageCount() {
         return pageCount;
     }

    public void setResultCount(int resultCount) {
         this.resultCount = resultCount;
     }
     public int getResultCount() {
         return resultCount;
     }

    public void setDapps(List<Dapps> dapps) {
         this.dapps = dapps;
     }
     public List<Dapps> getDapps() {
         return dapps;
     }

    public void setAd(Ad ad) {
         this.ad = ad;
     }
     public Ad getAd() {
         return ad;
     }

}