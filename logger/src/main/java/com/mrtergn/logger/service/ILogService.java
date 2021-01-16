package com.mrtergn.logger.service;

import com.mrtergn.logger.model.entity.ErrorLog;

import java.util.List;

public interface ILogService {

    void insertErrorLog(ErrorLog errorLog);

    List<ErrorLog> getAllErrorLogs();

}
