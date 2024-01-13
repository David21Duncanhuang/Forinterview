package com.firstspringboot.myspringapp;

// CurrencyController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.List;


@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyConversionService) {
        this.currencyService = currencyConversionService;
    }


    @RequestMapping(value = "/GetAllcurrency", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        List<Currency> currencies = currencyService.getAllCurrencies();
        return ResponseEntity.ok(currencies);
    }


    @GetMapping("/{currencyCode}")
    public ResponseEntity<Currency> getCurrencyByCode(@PathVariable String currencyCode) {
        Currency currency = currencyService.getCurrencyByCode(currencyCode);
        if (currency != null) {
            return ResponseEntity.ok(currency);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/AddCurrency", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<Currency> addCurrency(@RequestBody Currency currency) {
        Currency addedCurrency = currencyService.addCurrency(currency);
        return ResponseEntity.ok(addedCurrency);
    }

    @PutMapping(value = "/UpdateCurrency/{currencyCode}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<Currency> updateCurrency(@PathVariable String currencyCode, @RequestBody Currency currency) {
        Currency updatedCurrency = currencyService.updateCurrency(currencyCode, currency);
        if (updatedCurrency != null) {
            return ResponseEntity.ok(updatedCurrency);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/DeleteCurrency/{currencyCode}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<Void> deleteCurrency(@PathVariable String currencyCode) {
        boolean deleted = currencyService.deleteCurrency(currencyCode);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 實作 API 端點
    @GetMapping("/ShowConvertCoindesk")
    public String ShowConvertCoindesk() {
        // coindesk API URL
        String coindeskApiUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";

        // 使用RestTemplate呼叫API
        RestTemplate restTemplate = new RestTemplate();
        String coindeskApiResponse = restTemplate.getForObject(coindeskApiUrl, String.class);

        // 使用Jackson解析JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 將JSON轉換為JsonNode
            JsonNode jsonNode = objectMapper.readTree(coindeskApiResponse);

            // 進行相關的JSON解析，例如獲取更新時間、幣別資訊等
            String updateTime = jsonNode.get("time").get("updated").asText();
            String usdRate = jsonNode.get("bpi").get("USD").get("rate").asText();
            String gbpRate = jsonNode.get("bpi").get("GBP").get("rate").asText();
            String eurRate = jsonNode.get("bpi").get("EUR").get("rate").asText();

            // 檢查日期時間格式
            ZonedDateTime zonedDateTime;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm:ss z", new Locale("ENGLISH"));
                zonedDateTime = ZonedDateTime.parse(updateTime, formatter);
            } catch (DateTimeParseException e) {
                e.printStackTrace();
                return "Invalid date format"; // 或其他適當的處理方式
            }

            // 將更新時間轉換為指定格式
            String formattedUpdateTime = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

            // 創建CoindeskInfo對象
            CoindeskInfo coindeskInfo = new CoindeskInfo();
            coindeskInfo.setUpdateTime(updateTime);

            // 創建CurrencyInfo對象
            CurrencyInfo usdInfo = new CurrencyInfo("USD", "美元", usdRate);
            CurrencyInfo gbpInfo = new CurrencyInfo("GBP", "英鎊", gbpRate);
            CurrencyInfo eurInfo = new CurrencyInfo("EUR", "歐元", eurRate);

            coindeskInfo.setUpdateTime(formattedUpdateTime);
            // 設置CurrencyInfo對象到CoindeskInfo
            coindeskInfo.setUsdInfo(usdInfo);
            coindeskInfo.setGbpInfo(gbpInfo);
            coindeskInfo.setEurInfo(eurInfo);


            // 創建ObjectMapper
            ObjectMapper objm1 = new ObjectMapper();

            try {
                // 使用ObjectMapper將CoindeskInfo轉換為JSON字符串
                String coindeskInfoJson = objm1.writeValueAsString(coindeskInfo);

                // 返回JSON字符串
                return coindeskInfoJson;
            } catch (IOException e) {
                e.printStackTrace();
                // 在實際應用中，應該進一步處理異常
                return null;
            }
            // 返回CoindeskInfo對象
//            return coindeskInfo;

        } catch (IOException e) {
            e.printStackTrace();
            // 在實際應用中，應該進一步處理異常
            return null;
        }
    }


    // 實作 API 端點
    @GetMapping(path = "/ShowCoindesk")
    public String ShowCoindesk()
    {
        // coindesk API URL
        String coindeskApiUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";

        // 使用RestTemplate呼叫API
        RestTemplate restTemplate = new RestTemplate();
        String coindeskApiResponse = restTemplate.getForObject(coindeskApiUrl, String.class);
        return coindeskApiResponse;
    }
}