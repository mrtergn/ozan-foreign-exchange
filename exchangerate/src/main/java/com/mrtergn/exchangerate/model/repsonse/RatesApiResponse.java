package com.mrtergn.exchangerate.model.repsonse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class RatesApiResponse {

    private String base;
    private Map<String, Double> rates;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

}
