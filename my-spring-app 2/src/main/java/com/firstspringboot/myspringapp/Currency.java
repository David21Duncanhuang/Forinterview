package com.firstspringboot.myspringapp;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {

    @Id
    private String currencyCode;

    private String chineseName;

    public Currency() {
        // 空的建構子，通常需要在使用JPA的情況下
    }

    public Currency(String currencyCode, String chineseName) {
        this.currencyCode = currencyCode;
        this.chineseName = chineseName;
    }

    // getters and setters

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }
}


//// Currency.java
//import javax.persistence.Entity;
//import javax.persistence.Id;
//
//@Entity
//public class Currency {
//    @Id
//    private String code;
//    private String chineseName;
//    private double exchangeRate;
//
//    // Getters and setters
//}