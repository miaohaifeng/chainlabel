/**
  * Copyright 2021 bejson.com 
  */
package com.happy.video.vo.debank.chart;

/**
 * Auto-generated: 2021-09-09 11:2:24
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DeBankChartDataReturn {

    private int _cache_seconds;
    private double _seconds;
    private boolean _use_cache;
    private Data data;
    private int error_code;
    public void set_cache_seconds(int _cache_seconds) {
         this._cache_seconds = _cache_seconds;
     }
     public int get_cache_seconds() {
         return _cache_seconds;
     }

    public void set_seconds(double _seconds) {
         this._seconds = _seconds;
     }
     public double get_seconds() {
         return _seconds;
     }

    public void set_use_cache(boolean _use_cache) {
         this._use_cache = _use_cache;
     }
     public boolean get_use_cache() {
         return _use_cache;
     }

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

    public void setError_code(int error_code) {
         this.error_code = error_code;
     }
     public int getError_code() {
         return error_code;
     }

}