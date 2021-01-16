package com.mrtergn.conversion.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Conversion {

    @Id
    @GeneratedValue
    private int id;
    private String transactionId;
    private String source;
    private String target;
    private double amount;
    private double conversion;
    private String date;

}
