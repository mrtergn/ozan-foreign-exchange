package com.mrtergn.conversion.service;

import com.mrtergn.conversion.model.entity.Conversion;

import java.util.List;

public interface IConversionDbService {

    void save(Conversion conversion);

    List<Conversion> findByTransactionDate(String transactionDate, int page, int size);

    List<Conversion> findByTransactionId(String transactionId);

}
