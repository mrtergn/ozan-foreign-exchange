package com.mrtergn.logger.service;

import com.mrtergn.logger.model.entity.ErrorLog;
import com.mrtergn.logger.repository.IErrorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LogService implements ILogService {

    @Autowired
    private IErrorLogRepository errorLogRepository;

    public void insertErrorLog(ErrorLog errorLog) {
        errorLogRepository.save(errorLog);
    }

    public List<ErrorLog> getAllErrorLogs() {
        return StreamSupport
                .stream(errorLogRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

}
