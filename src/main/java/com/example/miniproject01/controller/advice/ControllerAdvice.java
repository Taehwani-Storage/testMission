package com.example.miniproject01.controller.advice;

import com.example.miniproject01.dto.ResponseDto;
import com.example.miniproject01.exception.Status401Exception;
import com.example.miniproject01.exception.Status404Exception;
import com.example.miniproject01.exception.Status500Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(Status401Exception.class)
    // 승인되지 않은 사용자가 있다면 에러 메시지 띄우기
    public ResponseEntity<ResponseDto> UserUnauthorized(Status401Exception e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(e.getMessage());

        return ResponseEntity
                .status(401)
                .body(responseDto);
    }

    @ExceptionHandler(Status404Exception.class)
    // 찾지 못한 사용자가 있으면 메시지 띄우기
    public ResponseEntity<ResponseDto> UserNotFound(Status404Exception e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(e.getMessage());

        return ResponseEntity
                .status(404)
                .body(responseDto);
    }

    @ExceptionHandler(Status500Exception.class)
    // 예외적인 에러 발생하면 메시지 띄우기
    public ResponseEntity<ResponseDto> UserInternalServerError(Status500Exception e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(e.getMessage());

        return ResponseEntity
                .status(500)
                .body(responseDto);
    }
}
