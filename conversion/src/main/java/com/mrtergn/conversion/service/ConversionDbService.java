package com.mrtergn.conversion.service;

import com.mrtergn.conversion.model.entity.Conversion;
import com.mrtergn.conversion.repository.IConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversionDbService implements IConversionDbService{

    @Autowired
    private IConversionRepository conversionRepository;

    public void save(Conversion conversion) {
        conversionRepository.save(conversion);
    }

    public List<Conversion> findByTransactionDate(String transactionDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Conversion> conversionPage = conversionRepository.findByTransactionDate(transactionDate, pageable);
        return conversionPage.getContent();
    }

    public List<Conversion> findByTransactionId(String transactionId) {
        return conversionRepository.findByTransactionId(transactionId);
    }
}
