package com.vbcodes.schooltransport.exception;

import org.modelmapper.internal.bytebuddy.asm.Advice.OffsetMapping.Factory.Illegal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vbcodes.schooltransport.exception.customexceptions.IllegalVehicleAccessException;
import com.vbcodes.schooltransport.exception.customexceptions.VehicleNotFoundException;
import com.vbcodes.schooltransport.responses.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVehicleNotFoundException(VehicleNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalVehicleAccessException.class)
    public ResponseEntity<ErrorResponse> handleIllegalVehicleAccessException(IllegalVehicleAccessException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
