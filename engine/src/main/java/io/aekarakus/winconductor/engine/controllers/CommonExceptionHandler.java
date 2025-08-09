package io.aekarakus.winconductor.engine.controllers;

import io.aekarakus.winconductor.engine.exceptions.DeviceNotFoundException;
import io.aekarakus.winconductor.engine.exceptions.DeviceNotReachableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({DeviceNotReachableException.class})
    protected ResponseEntity<Object> handleDeviceNotReachableException(DeviceNotReachableException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler({DeviceNotFoundException.class})
    protected ResponseEntity<Object> handleDeviceNotFoundException(DeviceNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
