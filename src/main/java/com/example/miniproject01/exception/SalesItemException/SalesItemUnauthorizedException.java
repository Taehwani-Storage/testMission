package com.example.miniproject01.exception.SalesItemException;

import com.example.miniproject01.exception.Status401Exception;

public class SalesItemUnauthorizedException extends Status401Exception {
    public SalesItemUnauthorizedException() {
        super("상품에 대한 잘못된 접근입니다. 아이디 혹은 비밀번호를 확인해주세요.");
    }
}
