package com.example.miniproject01.exception;

public abstract class Status404Exception extends RuntimeException {
    // 검색이 되지 않았을 때 에러 메시지
    public Status404Exception(String msg) {
        super(msg);
    }
}
