package com.example.backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.backend.dto.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(value = Exception.class)
        public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
                apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
                return ResponseEntity.badRequest().body(exception.getMessage());
        }

        @ExceptionHandler(value = AppException.class)
        ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
                ErrorCode errorCode = exception.getErrorCode();
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setCode(errorCode.getCode());
                apiResponse.setMessage(errorCode.getMessage());
                return ResponseEntity.badRequest().body(apiResponse);
        }

        @ExceptionHandler(value = MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse> handleValidation(MethodArgumentNotValidException exception) {
                String enumKey = exception.getFieldError().getDefaultMessage();
                ErrorCode errorCode = ErrorCode.INVALID_KEY;
                try {
                        errorCode = ErrorCode.valueOf(enumKey);

                } catch (IllegalArgumentException e) {
                        System.out.println("2");
                }
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setCode(errorCode.getCode());
                apiResponse.setMessage(errorCode.getMessage());

                return ResponseEntity.badRequest().body(apiResponse);
        }
}
