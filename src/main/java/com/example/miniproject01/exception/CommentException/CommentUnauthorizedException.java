package com.example.miniproject01.exception.CommentException;

import com.example.miniproject01.exception.Status401Exception;

public class CommentUnauthorizedException extends Status401Exception {
    public CommentUnauthorizedException() {
        super("해당 댓글에 대한 잘못된 접근입니다. 아이디 혹은 비밀번호를 확인해주세요.");
    }
}
