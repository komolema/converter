package com.converter.converter.controller;

public class ApiResponse<B> {
    private ApiHeader header;
    private ApiBody body;
    private ApiError error;

    public ApiResponse(ApiHeader header, ApiBody body, ApiError error){
        this.header = header;
        this.body = body;
        this.error = error;
    }

    public ApiHeader getHeader() {
        return header;
    }

    public void setHeader(ApiHeader header) {
        this.header = header;
    }

    public ApiBody getBody() {
        return body;
    }

    public void setBody(ApiBody body) {
        this.body = body;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }
}
