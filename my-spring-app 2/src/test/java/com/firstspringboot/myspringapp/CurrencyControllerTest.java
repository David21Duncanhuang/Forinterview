package com.firstspringboot.myspringapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CurrencyController.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql("/schema.sql")
class CurrencyControllerTest {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyRepository currencyRepository;

    @MockBean
    private CurrencyService currencyService;

    @Autowired
    private ObjectMapper objectMapper;

//    @BeforeEach
//    void init() {
//        // Perform your initialization here (e.g., insert data into the database)
//        currencyRepository.save(new Currency("TWD", "新台幣"));
//        currencyRepository.save(new Currency("USD", "美元"));
//        currencyRepository.save(new Currency("EUR", "歐元"));
//        // Add more initialization as needed
//    }

    @Test
    void testAddCurrencyEndpoint() throws Exception {
        // Create a sample Currency object
        Currency currencyToAdd = new Currency();
        currencyToAdd.setCurrencyCode("HKD");
        currencyToAdd.setChineseName("港幣");

        // Mock the behavior of currencyService.addCurrency
        when(currencyService.addCurrency(any(Currency.class))).thenReturn(currencyToAdd);

        // Perform a POST request to the endpoint
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.post("/api/currency/AddCurrency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")  // Set character encoding here
                        .content(objectMapper.writeValueAsString(currencyToAdd)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        // You can access the response content using result1.getResponse().getContentAsString()
        String content1 = result1.getResponse().getContentAsString();
        System.out.println("Response content for ShowCoindesk: " + content1);

    }

    @Test
    public void testCurrencyEndpoint() throws Exception {

        // Your test logic here
        MvcResult result1 = mockMvc.perform(get("/api/currency/ShowCoindesk"))
                .andExpect(status().isOk()).andReturn();

        // You can access the response content using result1.getResponse().getContentAsString()
        String content1 = result1.getResponse().getContentAsString();
        System.out.println("Response content for ShowCoindesk: " + content1);

        // Add more assertions as needed

        MvcResult result2 = mockMvc.perform(get("/api/currency/ShowConvertCoindesk"))
                .andExpect(status().isOk()).andReturn();

        // You can access the response content using result1.getResponse().getContentAsString()
        String content2 = result2.getResponse().getContentAsString();
        System.out.println("Response content for ShowConvertCoindesk: " + content2);

        MvcResult result3 = mockMvc.perform(get("/api/currency/GetAllcurrency"))
                .andExpect(status().isOk()).andReturn();

        // You can access the response content using result1.getResponse().getContentAsString()
        String content3 = result3.getResponse().getContentAsString();
        System.out.println("Response content for ShowConvertCoindesk: " + content3);
    }

    @Test
    void testUpdateCurrencyEndpoint() throws Exception {
        // Create a sample Currency object
        Currency updatedCurrency = new Currency();
        updatedCurrency.setCurrencyCode("USD");
        updatedCurrency.setChineseName("美元");

        // Mock the behavior of currencyService.updateCurrency
        when(currencyService.updateCurrency(anyString(), any(Currency.class))).thenReturn(updatedCurrency);

        // Perform a PUT request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.put("/api/currency/UpdateCurrency/USD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content("{\"currencyCode\":\"HKD\",\"chineseName\":\"港幣\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that currencyService.updateCurrency was called with the correct arguments
        verify(currencyService, times(1)).updateCurrency(eq("USD"), any(Currency.class));
    }

    @Test
    void testDeleteCurrencyEndpoint() throws Exception {
        // Mock the behavior of currencyService.deleteCurrency
        when(currencyService.deleteCurrency(anyString())).thenReturn(true);

        // Perform a DELETE request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/currency/DeleteCurrency/USD"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Verify that currencyService.deleteCurrency was called with the correct argument
        verify(currencyService, times(1)).deleteCurrency(eq("USD"));
    }
}
