package com.example.miniproject01.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "sales_item")
public class SalesItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 중고 물품 판매 제목

    @Column(nullable = false)
    private String description; // 중고 물품 설명

    @Column(nullable = false, name = "min_price_wanted")
    private Integer minPriceWanted; // 중고 물품 최소 판매액

    @Column(name = "image_url")
    private String imageUrl; // 중고 물품 이미지 URL

    private String status; // 중고 물품 판매 여부

    @Column(nullable = false)
    private String writer; // 중고 물품 작성자

    @Column(nullable = false)
    private String password; // 중고 물품 작성자 비밀번호

    // 지연 로딩을 위한 리스트
    @OneToMany(mappedBy = "salesItem", fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "salesItem", fetch = FetchType.LAZY)
    private List<NegotiationEntity> negotiationList = new ArrayList<>();

}
