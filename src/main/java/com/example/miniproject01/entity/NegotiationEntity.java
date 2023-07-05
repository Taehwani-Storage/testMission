package com.example.miniproject01.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "negotiation")
public class NegotiationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private SalesItemEntity salesItem; // 중고 물품 협상에 대한 아이디

    @NotNull
    @Column(name = "suggested_price")
    private Integer suggestedPrice; // 제안한 금액
    private String status; // 판매 여부

    @NotNull
    private String writer; // 작성자
    @NotNull
    private String password; // 비밀번호
}
