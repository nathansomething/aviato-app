package com.aviato.userlogin;

public class FacebookJson {
    String picUrl;
    
    //constructor
    public FacebookJson(String picUrl) {
        this.picUrl = picUrl;
    }
    
    //setters and getters 
    
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    
    public String getPicUrl() {
        return this.picUrl;
    }
}
