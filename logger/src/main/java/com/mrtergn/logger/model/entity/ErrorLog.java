package com.mrtergn.logger.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@ApiModel(value = "ErrorLog model", description = "ErrorLog model")
public class ErrorLog {

    @Id
    @GeneratedValue
    @ApiModelProperty(hidden = true)
    private int id;
    @JsonProperty
    @ApiModelProperty(value = "Service name", example = "logger")
    private String service;
    @JsonProperty
    @Column(columnDefinition = "TEXT")
    @ApiModelProperty(value = "Error message", example = "Test logger error message")
    private String message;

}
