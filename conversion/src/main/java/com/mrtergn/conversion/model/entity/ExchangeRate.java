package com.mrtergn.conversion.model.entity;

import lombok.Data;


@Data
public class ExchangeRate {

    private String base;
    private String symbol;
    private double rate;
    private String date;

}
