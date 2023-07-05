package com.example.miniproject01.exception;

public abstract class Status401Exception extends RuntimeException {
    // 승인되지 않으면 에러 메시지
    public Status401Exception(String msg) {
        super(msg);
    }
}
