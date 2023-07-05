package com.example.miniproject01.exception.NegotiationException;

import com.example.miniproject01.exception.Status401Exception;

public class NegotiationUnauthorizedException extends Status401Exception {
    public NegotiationUnauthorizedException() {
        super("해당 제안은 잘못된 접근입니다. 아이디 혹은 비밀번호를 확인해주세요.");
    }
}
