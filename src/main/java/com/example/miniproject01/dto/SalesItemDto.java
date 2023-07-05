package com.example.miniproject01.dto;

import com.example.miniproject01.entity.SalesItemEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null인 데이터는 JSON결과값이 나오지 않는 옵션
public class SalesItemDto {
    private Long id;
    private String title; // 중고 물품 페이지 제목
    private String description; // 중고 물품 설명
    private Integer minPriceWanted; // 중고 물품 최소 판매액
    private String imageUrl; // 중고 물품 이미지 URL
    private String status; // 판매 여부
    private String writer; // 작성자
    private String password; // 중고 물품 판매글 비밀번호
    // 중고 물품 판매 페이지 읽어오는 메소드
    public static SalesItemDto readSalesItemPage(SalesItemEntity entity) {
        SalesItemDto dto = new SalesItemDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setImageUrl(entity.getImageUrl());
        dto.setStatus(entity.getStatus());

        return dto;
    }
    // 중고 물품 읽어오는 메소드
    public static SalesItemDto readSalesItem(SalesItemEntity entity) {
        SalesItemDto dto = new SalesItemDto();
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setImageUrl(entity.getImageUrl());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}
