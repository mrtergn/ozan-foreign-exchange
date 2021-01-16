package com.mrtergn.exchangerate.model;

import com.mrtergn.exchangerate.model.entity.ExchangeRate;
import com.mrtergn.exchangerate.model.repsonse.RatesApiResponse;
import com.mrtergn.exchangerate.model.request.ExchangeRateRequest;

public class ModelCreator {

    private ModelCreator() {
    }

    public static ExchangeRate createExchangeRate(ExchangeRateRequest exchangeRateRequest, RatesApiResponse ratesApiResponse) {
        ExchangeRate exchangeRate = new ExchangeRate();

        exchangeRate.setBase(exchangeRateRequest.getBase());
        exchangeRate.setSymbol(exchangeRateRequest.getSymbol());
        exchangeRate.setRate(ratesApiResponse.getRates().get(exchangeRateRequest.getSymbol()));
        exchangeRate.setDate(ratesApiResponse.getDate());

        return exchangeRate;
    }
}
