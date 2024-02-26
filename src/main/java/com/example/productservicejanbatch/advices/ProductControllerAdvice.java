package com.example.productservicejanbatch.advices;

import com.example.productservicejanbatch.controllers.ProductController;
import com.example.productservicejanbatch.dtos.ExceptionDto;
import com.example.productservicejanbatch.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// @ControllerAdvice(assignableTypes = {ProductController.class}) - In this case only mentioned class
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("Failure");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

}
