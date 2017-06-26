package com.zonsim.yixue.banner;

/**
 * ^-^
 * Created by tang-jw on 2017/6/26.
 */

public class Banner {
    private String url;
    private int resId;
    
    public Banner(String url, int resId) {
        this.url = url;
        this.resId = resId;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public int getResId() {
        return resId;
    }
    
    public void setResId(int resId) {
        this.resId = resId;
    }
}
