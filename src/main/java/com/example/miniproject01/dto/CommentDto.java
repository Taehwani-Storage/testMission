package com.example.miniproject01.dto;

import com.example.miniproject01.entity.CommentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto {
    private Long id;
    private Long item_id;  // 중고 물품 아이디
    private String writer; // 댓글 작성자
    private String password; // 댓글 등록 비밀번호
    private String content; // 댓글 내용
    private String reply; // 댓글에 대한 답글

    public static CommentDto readComments(CommentEntity entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setReply(entity.getReply());

        return dto;
    }

}
