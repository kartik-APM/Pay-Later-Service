package com.practice.pay.later.service.exception;

import com.practice.pay.later.service.enums.Status;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@Builder

public class ApiResponse<T> {

        private Status status;
        private T data;
        private String message;

        public ApiResponse() {
                status = Status.SUCCESSFUL;
        }
}