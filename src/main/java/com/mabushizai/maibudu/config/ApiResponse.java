package com.mabushizai.maibudu.config;

import lombok.Data;

@Data
public final class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;

    private ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "", data);
    }

    public static <T> ApiResponse<T> error(String errorMsg) {
        return new ApiResponse<>(false, errorMsg, null);
    }
}
