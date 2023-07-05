package com.example.miniproject01.exception;

public abstract class Status500Exception extends RuntimeException {
    // 인터널 서버 에러 발생하면 메시지
    public Status500Exception(String msg) {
        super(msg);
    }
}
