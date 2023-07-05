package com.example.miniproject01.exception.NegotiationException;

import com.example.miniproject01.exception.Status404Exception;

public class NegotiationNotFoundException extends Status404Exception {
    public NegotiationNotFoundException() {
        super("존재하지 않는 제안입니다.");
    }
}
