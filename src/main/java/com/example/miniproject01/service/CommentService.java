package com.example.miniproject01.service;

import com.example.miniproject01.dto.CommentDto;
import com.example.miniproject01.dto.ResponseDto;
import com.example.miniproject01.entity.CommentEntity;
import com.example.miniproject01.entity.SalesItemEntity;
import com.example.miniproject01.exception.CommentException.CommentNotFoundException;
import com.example.miniproject01.exception.CommentException.CommentNullException;
import com.example.miniproject01.exception.CommentException.CommentUnauthorizedException;
import com.example.miniproject01.exception.SalesItemException.SalesItemNotFoundException;
import com.example.miniproject01.repository.CommentRepository;
import com.example.miniproject01.repository.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final SalesItemRepository salesItemRepository;
    // 응답 메시지 출력 메소드
    private ResponseDto setResponseMsg(String message) {
        ResponseDto dto = new ResponseDto();
        dto.setMessage(message);

        return dto;
    }
    // 중고 물품 아이디로 판매 물건인지 확인하는 메소드
    private SalesItemEntity checkSalesItem(Long itemId) {
        Optional<SalesItemEntity> optionalSalesItem = salesItemRepository.findById(itemId);
        if (optionalSalesItem.isEmpty()) {
            throw new SalesItemNotFoundException();
        }

        return optionalSalesItem.get();
    }
    // 댓글 아이디로 댓글 확인 메소드
    private CommentEntity checkComment(Long commentId) {
        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            throw new CommentNotFoundException();
        }

        return optionalComment.get();
    }
    // 댓글 확인 및 구매자 확인 메소드
    private CommentEntity checkCommentAndBuyer(Long commentId, CommentDto commentDto) {
        CommentEntity commentEntity = checkComment(commentId); // 댓글 확인
        // 구매자 정보 조회
        if (!commentEntity.getPassword().equals(commentDto.getPassword())
                || !commentEntity.getWriter().equals(commentDto.getWriter())) {
            throw new CommentUnauthorizedException();
        }

        return commentEntity;
    }
    // 댓글 생성 메소드
    public ResponseDto createComment(Long itemId, CommentDto commentDto) {
        SalesItemEntity salesItemEntity = checkSalesItem(itemId); // 중고 물품 확인
        // 댓글 작성자 정보 조회
        if (commentDto.getWriter().isBlank() || commentDto.getWriter() == null ||
                commentDto.getPassword().isBlank() || commentDto.getPassword() == null
        ) {
            throw new CommentNullException();
        }
        // 댓글 작성자, 해당 중고 물품, 댓글 비밀번호, 댓글 내용
        CommentEntity newComment = new CommentEntity();
        newComment.setWriter(commentDto.getWriter());
        newComment.setSalesItem(salesItemEntity);
        newComment.setPassword(commentDto.getPassword());
        newComment.setContent(commentDto.getContent());
        commentRepository.save(newComment);

        return setResponseMsg("댓글이 등록되었습니다.");
    }
    // 페이지에서 댓글 읽는 메소드
    public Page<CommentDto> readPage(Long itemId) {
        checkSalesItem(itemId); // 중고 물품 확인
        Pageable pageable = PageRequest.of(0, 25); // 페이지 최대 25쪽
        Page<CommentEntity> commentEntityPage
                = commentRepository.findAllBySalesItemId(itemId, pageable);

        return commentEntityPage.map(CommentDto::readComments);
    }
    // 댓글 업데이트 및 수정 메소드
    public ResponseDto updateComment(Long itemId, Long commentId, CommentDto commentDto) {
        checkSalesItem(itemId); // 중고 판매 물품 확인
        // 댓글 확인 및 구매자 정보 조회
        CommentEntity commentEntity = checkCommentAndBuyer(commentId, commentDto);
        commentEntity.setContent(commentDto.getContent());
        commentRepository.save(commentEntity);

        return setResponseMsg("댓글이 수정되었습니다.");
    }
    // 댓글에 대한 답글 생성 메소드
    public ResponseDto updateCommentReply(Long itemId, Long commentId, CommentDto commentDto) {
        // 판매 물품 확인
        SalesItemEntity salesItemEntity = checkSalesItem(itemId);
        // 판매자 정보 조회
        if (!salesItemEntity.getPassword().equals(commentDto.getPassword())
                || !salesItemEntity.getWriter().equals(commentDto.getWriter())) {
            return setResponseMsg("잘못된 판매자 정보입니다. 아이디 혹은 비밀번호를 확인해주세요.");
        }
        // 댓글 확인 및 답글 추가
        CommentEntity commentEntity = checkComment(commentId);
        commentEntity.setReply(commentDto.getReply());
        commentRepository.save(commentEntity);

        return setResponseMsg("댓글에 답변이 추가되었습니다.");
    }
    // 댓글 삭제 서비스 메소드
    public ResponseDto deleteComment(Long itemId, Long commentId, CommentDto commentDto) {
        checkSalesItem(itemId); // 중고 물품 확인
        checkCommentAndBuyer(commentId, commentDto); // 댓글 확인 및 구매자 정보 조회
        commentRepository.deleteById(commentId);

        return setResponseMsg("댓글을 삭제했습니다.");
    }
}
