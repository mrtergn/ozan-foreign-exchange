package com.mrtergn.exchangerate.service;

import com.mrtergn.exchangerate.model.repsonse.RatesApiResponse;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface IRatesApiService {

    RatesApiResponse getLatestExchange(String base, String symbol) throws URISyntaxException, MalformedURLException;

}
