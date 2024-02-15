package com.spring.exception.error;


import com.spring.exception.base.BaseException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class globalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleRecordNotFountException(BaseException exception) {
        ErrorResponse error = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
        return ResponseEntity.status(exception.getStatus()).body(error);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NotNull HttpHeaders headers,@NotNull HttpStatusCode status, WebRequest request) {
        ValidationError validationError = new ValidationError();
        validationError.setUrl(request.getDescription(false));
        List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
        for (FieldError f : fieldError) {
            validationError.addError(f.getDefaultMessage());

        }
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }


}