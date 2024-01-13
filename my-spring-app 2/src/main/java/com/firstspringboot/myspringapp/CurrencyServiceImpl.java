package com.firstspringboot.myspringapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency getCurrencyByCode(String currencyCode) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyCode);
        return optionalCurrency.orElse(null);
    }

    @Override
    public Currency addCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Currency updateCurrency(String currencyCode, Currency updatedCurrency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyCode);
        if (optionalCurrency.isPresent()) {
            Currency existingCurrency = optionalCurrency.get();
            existingCurrency.setChineseName(updatedCurrency.getChineseName());
            return currencyRepository.save(existingCurrency);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteCurrency(String currencyCode) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyCode);
        if (optionalCurrency.isPresent()) {
            currencyRepository.deleteById(currencyCode);
            return true;
        } else {
            return false;
        }
    }
}


