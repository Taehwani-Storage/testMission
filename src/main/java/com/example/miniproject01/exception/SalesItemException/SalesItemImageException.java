package com.example.miniproject01.exception.SalesItemException;

import com.example.miniproject01.exception.Status500Exception;

public class SalesItemImageException extends Status500Exception {
    // Internal Server 에러
    public SalesItemImageException() {
        super("이미지를 저장할 수 없습니다. 다시 시도하세요");
    }
}
