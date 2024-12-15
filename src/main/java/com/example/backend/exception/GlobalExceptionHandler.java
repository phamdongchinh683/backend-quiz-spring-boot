package com.example.backend.exception;

import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.backend.dto.response.ApiResponse;

import jakarta.validation.ConstraintViolation;

@ControllerAdvice
public class GlobalExceptionHandler {

        private static final String MIN_ATTRIBUTE = "min";

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
                Map<String, Object> attributes = null;
                try {
                        errorCode = ErrorCode.valueOf(enumKey);

                        var constraintViolation = exception.getBindingResult().getAllErrors().get(0)
                                        .unwrap(ConstraintViolation.class);
                        attributes = constraintViolation.getConstraintDescriptor().getAttributes();

                } catch (IllegalArgumentException e) {
                        System.out.println("Invalid key");
                }

                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setCode(errorCode.getCode());
                apiResponse.setMessage(
                                Objects.nonNull(attributes)
                                                ? mapAttribute(errorCode.getMessage(), attributes)
                                                : errorCode.getMessage());

                return ResponseEntity.badRequest().body(apiResponse);
        }

        private String mapAttribute(String message, Map<String, Object> attributes) {
                String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));
                return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
        }
}
