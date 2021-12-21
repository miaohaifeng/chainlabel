/**
  * Copyright 2021 bejson.com 
  */
package com.happy.video.vo.debank;
import java.util.List;

/**
 * Auto-generated: 2021-08-11 17:8:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SupportedProtocolReturn {

    private int _cache_seconds;
    private double _seconds;
    private boolean _use_cache;
    private List<Data> data;
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

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setError_code(int error_code) {
         this.error_code = error_code;
     }
     public int getError_code() {
         return error_code;
     }

}