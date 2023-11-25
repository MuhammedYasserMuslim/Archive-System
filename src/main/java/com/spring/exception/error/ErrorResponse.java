package com.spring.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ErrorResponse {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime timestamp;
    private String message;
    private String status;
    private boolean success;

    public ErrorResponse() {

    }

    public ErrorResponse(String message ,String status) {
        this.status = status;
        this.message = message;
        this.success = Boolean.FALSE;
        this.timestamp = LocalDateTime.now();
    }
}
