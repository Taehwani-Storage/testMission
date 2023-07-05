package com.example.miniproject01.exception.SalesItemException;

import com.example.miniproject01.exception.Status404Exception;

public class SalesItemNotFoundException extends Status404Exception {
    public SalesItemNotFoundException() {
        super("없는 상품입니다.");
    }
}
