package com.example.miniproject01.exception.CommentException;

import com.example.miniproject01.exception.Status500Exception;

public class CommentNullException extends Status500Exception {
    // @NotNull Exception, Internal Server 에러
    public CommentNullException() {
        super("빈칸이 있습니다. 다시 확인해주세요.");
    }
}
