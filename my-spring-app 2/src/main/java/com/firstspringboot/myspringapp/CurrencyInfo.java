package com.firstspringboot.myspringapp;

public class CurrencyInfo {

    private String code;
    private String chineseName;
    private String rate;

    public CurrencyInfo(String code, String chineseName, String rate) {
        this.code = code;
        this.chineseName = chineseName;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
// getters and setters
}