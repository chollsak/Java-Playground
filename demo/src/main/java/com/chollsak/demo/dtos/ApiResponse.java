package com.chollsak.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String message;
    private boolean success = true;

    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
        this.success = true;
    }

    public ApiResponse(T data) {
        this.data = data;
        this.message = "Success";
        this.success = true;
    }
}