package com.practice.pay.later.service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDetails {

    private Date date;
    private String message;
    private String details;

}
