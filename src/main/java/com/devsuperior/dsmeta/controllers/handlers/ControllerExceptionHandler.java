package com.devsuperior.dsmeta.controllers.handlers;

import java.time.Instant;

import com.devsuperior.dsmeta.dto.CustomErrorDTO;
import com.devsuperior.dsmeta.exceptions.InvalidParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<CustomErrorDTO> invalidParameter(InvalidParameterException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}