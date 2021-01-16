package com.mrtergn.conversion.service;

import com.mrtergn.conversion.model.entity.ExchangeRate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface IExchangeRateService {

    ExchangeRate getExchangeRate(String base, String symbol) throws URISyntaxException, MalformedURLException;

}
