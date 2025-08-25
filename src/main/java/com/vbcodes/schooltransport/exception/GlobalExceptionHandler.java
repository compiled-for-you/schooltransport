package com.vbcodes.schooltransport.exception;

import org.modelmapper.internal.bytebuddy.asm.Advice.OffsetMapping.Factory.Illegal;
import org.modelmapper.internal.bytebuddy.implementation.bind.MethodDelegationBinder.ParameterBinding.Anonymous;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vbcodes.schooltransport.exception.customexceptions.AnonymousUserException;
import com.vbcodes.schooltransport.exception.customexceptions.IllegalResourceAccessException;
import com.vbcodes.schooltransport.exception.customexceptions.ResourceNotFoundException;
import com.vbcodes.schooltransport.exception.customexceptions.UnsupportedMappingOperationException;
import com.vbcodes.schooltransport.responses.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVehicleNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleIllegalVehicleAccessException(IllegalResourceAccessException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnsupportedMappingOperationException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedMappingOperationException(UnsupportedMappingOperationException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AnonymousUserException.class)
    public ResponseEntity<ErrorResponse> handleAnonymousUserException(AnonymousUserException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
