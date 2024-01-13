package com.firstspringboot.myspringapp;

//// CurrencyService.java
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//
//@Service
//public class CurrencyService {
//    @Autowired
//    private CurrencyRepository currencyRepository;
//
//    // 實作 CRUD 操作和其他功能
//}

import java.util.List;

public interface CurrencyService {

    List<Currency> getAllCurrencies();

    Currency getCurrencyByCode(String currencyCode);

    Currency addCurrency(Currency currency);

    Currency updateCurrency(String currencyCode, Currency currency);

    boolean deleteCurrency(String currencyCode);
}