package com.example.miniproject01.service;

import com.example.miniproject01.dto.NegotiationDto;
import com.example.miniproject01.dto.ResponseDto;
import com.example.miniproject01.entity.NegotiationEntity;
import com.example.miniproject01.entity.SalesItemEntity;
import com.example.miniproject01.exception.NegotiationException.NegotiationNotFoundException;
import com.example.miniproject01.exception.NegotiationException.NegotiationNullException;
import com.example.miniproject01.exception.NegotiationException.NegotiationUnauthorizedException;
import com.example.miniproject01.exception.SalesItemException.SalesItemNotFoundException;
import com.example.miniproject01.repository.NegotiationRepository;
import com.example.miniproject01.repository.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NegotiationService {
    private final NegotiationRepository negotiationRepository;
    private final SalesItemRepository salesItemRepository;
    // 응답 메시지 출력 메소드
    private ResponseDto setResponseMsg(String message) {
        ResponseDto dto = new ResponseDto();
        dto.setMessage(message);

        return dto;
    }
    // 중고 물품 확인 메소드
    private SalesItemEntity checkSalesItem(Long itemId) {
        Optional<SalesItemEntity> optionalSalesItem = salesItemRepository.findById(itemId);
        if (optionalSalesItem.isEmpty()) {
            throw new SalesItemNotFoundException();
        }

        return optionalSalesItem.get();
    }
    // 협상 물품 확인 메소드
    private NegotiationEntity checkNegotiation(Long proposalId, NegotiationDto dto) {
        Optional<NegotiationEntity> optionalNegotiation
                = negotiationRepository.findById(proposalId);
        // 협상 유무 확인
        if (optionalNegotiation.isEmpty()) {
            throw new NegotiationNotFoundException();
        }

        // 중고 물품 판매 제안자 조회
        if (!optionalNegotiation.get().getWriter().equals(dto.getWriter())
                || !optionalNegotiation.get().getPassword().equals(dto.getPassword())) {
            throw new NegotiationUnauthorizedException();
        }

        return optionalNegotiation.get();
    }
    // 협상 서비스 생성 메소드
    public ResponseDto createNegotiation(Long itemId, NegotiationDto negotiationDto) {
        // 중고 물품 확인
        SalesItemEntity salesItemEntity = checkSalesItem(itemId);

        if (negotiationDto.getSuggestedPrice() == null ||
                negotiationDto.getWriter().isBlank() || negotiationDto.getWriter() == null ||
                negotiationDto.getPassword().isBlank() || negotiationDto.getPassword() == null
        ) {
            throw new NegotiationNullException();
        }
        // 협상 작성자, 해당 중고 물품, 제안자 비밀번호, 제안한 가격
        NegotiationEntity newNegotiation = new NegotiationEntity();
        newNegotiation.setSalesItem(salesItemEntity);
        newNegotiation.setStatus("제안");
        newNegotiation.setSuggestedPrice(negotiationDto.getSuggestedPrice());
        newNegotiation.setWriter(negotiationDto.getWriter());
        newNegotiation.setPassword(negotiationDto.getPassword());
        negotiationRepository.save(newNegotiation);

        return setResponseMsg("구매 제안이 등록되었습니다.");
    }
    // 페이지에서 협상 내용 읽어오기
    public Page<NegotiationDto> readAllNegotiation(
            Long itemId, String writer, String password, Integer page
    ) {
        SalesItemEntity salesItemEntity = checkSalesItem(itemId); // 중고 물품 확인
        Pageable pageable = PageRequest.of(page - 1, 25);
        // 판매자가 요청시 모든 제안을 볼 수 있음
        if (salesItemEntity.getWriter().equals(writer) && salesItemEntity.getPassword().equals(password)) {
            Page<NegotiationEntity> negotiationEntityPage
                    = negotiationRepository.findAllBySalesItemId(itemId, pageable);

            return negotiationEntityPage.map(NegotiationDto::fromEntity);
        }
        Page<NegotiationEntity> negotiationEntityPage
                = negotiationRepository.findAllBySalesItemIdAndWriterAndPassword(itemId, writer, password, pageable);

        return negotiationEntityPage.map(NegotiationDto::fromEntity);
    }
    // 협상 내용 업데이트 및 수정 메소드
    public ResponseDto updateNegotiation(Long itemId, Long proposalId, NegotiationDto negotiationDto) {
        // 중고 물품 확인
        SalesItemEntity salesItemEntity = checkSalesItem(itemId);
        // "제안", "거절" 상태일 때 확정, false 반환
        // "제안", "수락" 상태일 때 확정
        // 판매자가 수락 or 거절 상태 수정 요청
        if (salesItemEntity.getWriter().equals(negotiationDto.getWriter())
                && salesItemEntity.getPassword().equals(negotiationDto.getPassword())
        ) {
            // 협상 유무 확인
            Optional<NegotiationEntity> optionalNegotiation = negotiationRepository.findById(proposalId);
            if (optionalNegotiation.isEmpty()) {
                throw new NegotiationNotFoundException();
            }
            // 판매자가 "수락"으로 변경 시 다른 모든 제안은 "거절" 상태로 변경
            if (negotiationDto.getStatus().equals("수락")) {
                List<NegotiationEntity> negotiationEntities
                        = negotiationRepository.findAllBySalesItemId(itemId);

                for (NegotiationEntity entity : negotiationEntities) {
                    entity.setStatus("거절");
                }
            }
            NegotiationEntity negotiationEntity = optionalNegotiation.get();
            negotiationEntity.setStatus(negotiationDto.getStatus());
            negotiationRepository.save(negotiationEntity);

            return setResponseMsg("제안의 상태가 변경되었습니다.");
        } else {  // 구매자의 수정 요청
            // 제안자 아이디 및 구매자 정보 인증
            NegotiationEntity negotiationEntity = checkNegotiation(proposalId, negotiationDto);
            // "제안" 또는 "거절"일 때 ("수락" 상태)
            if (!negotiationEntity.getStatus().equals("수락")) {
                if (negotiationDto.getStatus() == null || negotiationDto.getStatus().isBlank()) {
                    // 다른 가격으로 제안
                    negotiationEntity.setSuggestedPrice(negotiationDto.getSuggestedPrice());
                    negotiationEntity.setStatus("제안");  // "거절"일 때 다시 "제안"으로 변경
                    negotiationRepository.save(negotiationEntity);

                    return setResponseMsg("제안이 수정되었습니다.");
                } else if (negotiationDto.getStatus().equals("확정")) {
                    // "수락"이 아닌데 "확정"을 눌렀을 때
                    return setResponseMsg("아직 판매자가 수락하지 않은 거래입니다.");
                }
            }
            // "수락"일 때
            negotiationEntity.setStatus(negotiationDto.getStatus());
            negotiationRepository.save(negotiationEntity);
            // 중고 물품 판매글 상태 변경
            salesItemEntity.setStatus("판매완료");
            salesItemRepository.save(salesItemEntity);

            return setResponseMsg("구매가 확정되었습니다.");
        }
    }
    // 협상 삭제 서비스 메소드
    public ResponseDto deleteNegotiation(Long itemId, Long proposalId, NegotiationDto negotiationDto) {
        checkSalesItem(itemId); // 중고 물품 확인
        checkNegotiation(proposalId, negotiationDto); // 중고 물품 확인, 제안자 조회
        negotiationRepository.deleteById(proposalId);

        return setResponseMsg("제안을 삭제했습니다.");
    }

}
