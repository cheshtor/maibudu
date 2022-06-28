package com.mabushizai.maibudu.config;

import lombok.Data;

@Data
public final class ApiResponse<T> {

    private int code;
    private String message;
    private T data;

    private ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(0, "", data);
    }

    public static <T> ApiResponse<T> error(String errorMsg) {
        return new ApiResponse<>(0, errorMsg, null);
    }
}
