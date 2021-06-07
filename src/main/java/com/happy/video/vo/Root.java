package com.happy.video.vo;

import java.util.List;
public class Root {
    private List<Data> data ;

    private boolean isSuccess;

    private String requestId;

    private String message;

    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
    public void setIsSuccess(boolean isSuccess){
        this.isSuccess = isSuccess;
    }
    public boolean getIsSuccess(){
        return this.isSuccess;
    }
    public void setRequestId(String requestId){
        this.requestId = requestId;
    }
    public String getRequestId(){
        return this.requestId;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }

}