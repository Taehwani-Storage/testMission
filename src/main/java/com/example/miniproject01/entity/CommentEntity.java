package com.example.miniproject01.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private SalesItemEntity salesItem; // 중고 물품에 대한 댓글

    @NotNull
    private String writer; // 중고 물품 댓글 작성자

    @NotNull
    private String password; // 중고 물품 댓글 비밀번호
    private String content; // 중고 물품 댓글 내용
    private String reply; // 중고 물품 댓글에 대한 답글

}
