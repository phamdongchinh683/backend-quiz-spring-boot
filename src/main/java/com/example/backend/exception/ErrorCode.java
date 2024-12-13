package com.example.backend.exception;

public enum ErrorCode {
        INVALID_KEY(400, "Invalid key"),
        USER_EXISTED(400, "User existed"),
        UNCATEGORIZED_EXCEPTION(500, "Uncategorized exception"),
        USERNAME_INVALID(400, "username must be at least 6 characters"),
        INVALID_PASSWORD(400, "username must be at least 10 characters\""),
        USER_NOT_EXISTED(400, "User not existed"),
        UNAUTHENTICATED(401, "Unauthenticated"),
        VALID_TOKEN(403, "Invalid token"),
        UNAUTHORIZED(401, "You do not have permission"),
        ;
        private final int code;
        private final String message;

        private ErrorCode(int code, String message) {
                this.code = code;
                this.message = message;
        }

        public int getCode() {
                return code;
        }

        public String getMessage() {
                return message;
        }
}
