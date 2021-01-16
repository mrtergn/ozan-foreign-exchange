package com.mrtergn.logger.restcontroller;

import com.mrtergn.logger.model.entity.ErrorLog;
import com.mrtergn.logger.model.response.BaseResponse;
import com.mrtergn.logger.service.ILogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LoggerController {

    @Autowired
    private ILogService logService;

    @PostMapping(
            value = "/error",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(value = "Insert error log")
    public ResponseEntity<Void> handleLogError(@RequestBody ErrorLog errorLog) {
        logService.insertErrorLog(errorLog);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping(
            value = "/error/all",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(value = "Get all error logs")
    public ResponseEntity<BaseResponse<List<ErrorLog>>> handleGetAllErrorLogs() {
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(logService.getAllErrorLogs()));
    }

}
