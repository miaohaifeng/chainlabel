package com.happy.video.vo;

public class Data {
    private String name;

    private String value;

    private boolean isselect;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    public void setIsselect(boolean isselect){
        this.isselect = isselect;
    }
    public boolean getIsselect(){
        return this.isselect;
    }

}