package com.example.miniproject01.exception.SalesItemException;

import com.example.miniproject01.exception.Status500Exception;

public class SalesItemNullException extends Status500Exception {
    // @NotNull Exception
    public SalesItemNullException() {
        super("빈칸이 있습니다. 다시 확인해 주세요.");
    }
}
