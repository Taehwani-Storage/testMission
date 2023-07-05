package com.example.miniproject01.dto;

import com.example.miniproject01.entity.NegotiationEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NegotiationDto {
    private Long id;
    private Long item_id; // 협상 물품 아이디
    private Integer suggestedPrice; // 구매를 위해 제안한 금액
    private String status; // 판매 여부
    private String writer; // 작성자
    private String password; // 협상 비밀번호

    public static NegotiationDto fromEntity(NegotiationEntity entity) {
        NegotiationDto dto = new NegotiationDto();
        dto.setId(entity.getId());
        dto.setSuggestedPrice(entity.getSuggestedPrice());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}
