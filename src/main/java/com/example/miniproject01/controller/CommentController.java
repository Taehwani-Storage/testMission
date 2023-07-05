package com.example.miniproject01.controller;

import com.example.miniproject01.dto.CommentDto;
import com.example.miniproject01.dto.ResponseDto;
import com.example.miniproject01.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/items/{itemId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성 메소드, 성공적이면 메시지 출력
    @PostMapping
    public ResponseEntity<ResponseDto> create (
            @PathVariable("itemId") Long itemId,
            @RequestBody CommentDto commentDto
    ) {
        return ResponseEntity
                .status(200)
                .body(commentService.createComment(itemId, commentDto));
    }
    // 페이지에서 댓글 읽는 메소드, 성공적이면 메시지 출력
    @GetMapping
    public ResponseEntity<Page<CommentDto>> readCommentPage (
            @PathVariable("itemId") Long itemId
    ) {
        return ResponseEntity
                .status(200)
                .body(commentService.readPage(itemId));
    }
    // 댓글 업데이트 및 수정 메소드, 성공적이면 메시지 출력
    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseDto> updateComment (
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentDto commentDto
    ) {
        return ResponseEntity
                .status(200)
                .body(commentService.updateComment(itemId, commentId, commentDto));
    }
    // 댓글에 대한 답변 업데이트 메소드, 성공적이면 메시지 출력
    @PutMapping("/{commentId}/reply")
    public ResponseEntity<ResponseDto> updateCommentReply (
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentDto commentDto
    ) {
        return ResponseEntity
                .status(200)
                .body(commentService.updateCommentReply(itemId, commentId, commentDto));
    }
    // 댓글 삭제 메소드, 성공적이면 메시지 출력
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseDto> delete (
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentDto commentDto
    ) {
        return ResponseEntity
                .status(200)
                .body(commentService.deleteComment(itemId, commentId, commentDto));
    }

}
