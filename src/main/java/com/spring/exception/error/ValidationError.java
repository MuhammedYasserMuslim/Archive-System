package com.spring.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ValidationError {

    private List<String> errors;
    private String url;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime timestamp;

    public void addError(String error){
        this.errors.add(error);
    }

    public ValidationError() {
        this.timestamp = LocalDateTime.now();
        this.errors = new ArrayList<>();
    }


}