package com.mrtergn.conversion.model;

import com.mrtergn.conversion.model.entity.Conversion;
import com.mrtergn.conversion.model.entity.ExchangeRate;
import com.mrtergn.conversion.model.request.ConversionRequest;

import java.util.UUID;

public class ModelCreator {

    private ModelCreator() {
    }

    public static Conversion createConversion(ConversionRequest conversionRequest, ExchangeRate exchangeRate) {
        Conversion conversion = new Conversion();

        conversion.setTransactionId(UUID.randomUUID().toString());
        conversion.setSource(conversionRequest.getSource());
        conversion.setTarget(conversionRequest.getTarget());
        conversion.setAmount(conversionRequest.getAmount());
        conversion.setConversion(conversionRequest.getAmount() * exchangeRate.getRate());
        conversion.setDate(exchangeRate.getDate());

        return conversion;
    }

}
