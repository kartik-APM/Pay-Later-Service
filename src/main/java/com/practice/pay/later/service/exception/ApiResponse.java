package com.practice.pay.later.service.exception;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter

public class ApiResponse<T> {

        private String status;
        private T data;
        private String message;

        public ApiResponse() {
                status="success";
        }
}