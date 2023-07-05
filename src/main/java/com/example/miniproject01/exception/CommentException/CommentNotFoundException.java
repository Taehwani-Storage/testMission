package com.example.miniproject01.exception.CommentException;

import com.example.miniproject01.exception.Status404Exception;

public class CommentNotFoundException extends Status404Exception {
    public CommentNotFoundException() {
        super("없는 댓글입니다.");
    }
}
