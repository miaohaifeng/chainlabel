/**
  * Copyright 2021 bejson.com 
  */
package com.happy.video.vo.bscapi;

/**
 * Auto-generated: 2021-08-22 21:27:45
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class BscApiReturn {

    private boolean success;
    private Data data;
    private String message;
    public void setSuccess(boolean success) {
         this.success = success;
     }
     public boolean getSuccess() {
         return success;
     }

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

}