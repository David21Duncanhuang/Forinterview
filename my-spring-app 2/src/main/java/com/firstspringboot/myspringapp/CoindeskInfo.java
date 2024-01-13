package com.firstspringboot.myspringapp;

public class CoindeskInfo {

    private String updateTime;
    private CurrencyInfo usdInfo;
    private CurrencyInfo gbpInfo;
    private CurrencyInfo eurInfo;

    public CurrencyInfo getUsdInfo() {
        return usdInfo;
    }

    public void setUsdInfo(CurrencyInfo usdInfo) {
        this.usdInfo = usdInfo;
    }

    public CurrencyInfo getGbpInfo() {
        return gbpInfo;
    }

    public void setGbpInfo(CurrencyInfo gbpInfo) {
        this.gbpInfo = gbpInfo;
    }

    public CurrencyInfo getEurInfo() {
        return eurInfo;
    }

    public void setEurInfo(CurrencyInfo eurInfo) {
        this.eurInfo = eurInfo;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
// getters and setters
}
