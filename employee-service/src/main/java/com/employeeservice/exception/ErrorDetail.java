package com.employeeservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private LocalDateTime timeStamp;
    private String message;
    private String path;
    private String errorCode;
}
