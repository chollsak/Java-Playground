package com.chollsak.demo.dtos;

public class ApiResponse<T> {
    private T data;
    private String message;
    private boolean success;

    public ApiResponse() {}

    public ApiResponse(T data) {
        this.data = data;
        this.success = true;
    }


    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
        this.success = true;
    }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}