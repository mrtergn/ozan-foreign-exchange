package com.mrtergn.logger.repository;

import com.mrtergn.logger.model.entity.ErrorLog;
import org.springframework.data.repository.CrudRepository;

public interface IErrorLogRepository extends CrudRepository<ErrorLog, Long> {
}

