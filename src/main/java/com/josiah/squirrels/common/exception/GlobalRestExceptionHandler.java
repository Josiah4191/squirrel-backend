package com.josiah.squirrels.common.exception;

import com.josiah.squirrels.common.dto.ErrorResponseDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleAll(Exception ex) throws Exception {

        String message = ex.getMessage();
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String error = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        LocalDateTime timeStamp = LocalDateTime.now();
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(code, error, message, timeStamp);

        return ResponseEntity.status(code).body(errorResponseDto);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "Data integrity violation occurred.";
        int code = HttpStatus.BAD_REQUEST.value();
        String error = HttpStatus.BAD_REQUEST.getReasonPhrase();
        LocalDateTime timeStamp = LocalDateTime.now();
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(code, error, message, timeStamp);

        return ResponseEntity.status(code).body(errorResponseDto);
    }



}
