package com.example.miniproject01.exception.NegotiationException;

import com.example.miniproject01.exception.Status500Exception;

public class NegotiationNullException extends Status500Exception {
    // @NotNull Exception, Internal Server 에러
    public NegotiationNullException() {
        super("빈 칸이 있습니다. 다시 확인해주세요.");
    }
}
