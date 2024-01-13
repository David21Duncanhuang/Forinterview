package com.firstspringboot.myspringapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeData();
    }

    private void initializeData() {
        // 檢查資料庫中是否已經有資料
        if (currencyRepository.count() == 0) {
            // 如果沒有，則進行初始化資料的動作
            currencyRepository.save(new Currency("TWD", "新台幣"));
            currencyRepository.save(new Currency("USD", "美元"));
            currencyRepository.save(new Currency("EUR", "歐元"));
            // 可以添加更多的初始化資料
        }
    }
}
