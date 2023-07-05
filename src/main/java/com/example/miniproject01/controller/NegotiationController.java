package com.example.miniproject01.controller;


import com.example.miniproject01.dto.NegotiationDto;
import com.example.miniproject01.dto.ResponseDto;
import com.example.miniproject01.service.NegotiationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/items/{itemId}/proposals")
@RequiredArgsConstructor
public class NegotiationController {
    private final NegotiationService negotiationService;

    // 협상 생성 메소드, 성공적이면 메시지 출력
    @PostMapping
    public ResponseEntity<ResponseDto> createNegotiation(
            @PathVariable("itemId") Long itemId,
            @RequestBody NegotiationDto negotiationDto
    ) {
        return ResponseEntity
                .status(200)
                .body(negotiationService.createNegotiation(itemId, negotiationDto));
    }
    // 물품 정보, 작성자, 비밀번호를 통해 협상 물품을 페이지에서 읽어오는 메소드, 성공적이면 메시지 출력
    @GetMapping
    public ResponseEntity<Page<NegotiationDto>> readNegotiation(
            @PathVariable("itemId") Long itemId,
            @RequestParam("writer") String writer,
            @RequestParam("password") String password,
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        return ResponseEntity
                .status(200)
                .body(negotiationService.readAllNegotiation(itemId, writer, password, page));
    }
    // 물품 정보, 제안자 아이디를 통해 협상 정보 업데이트 및 수정 메소드, 성공적이면 메시지 출력
    @PutMapping("/{proposalId}")
    public ResponseEntity<ResponseDto> updateNegotiation(
            @PathVariable("itemId") Long itemId,
            @PathVariable("proposalId") Long proposalId,
            @RequestBody NegotiationDto negotiationDto
    ) {
        return ResponseEntity
                .status(200)
                .body(negotiationService.updateNegotiation(itemId, proposalId, negotiationDto));
    }
    // 협상 정보 삭제 메소드, 성공적이면 메시지 출력
    @DeleteMapping("/{proposalId}")
    public ResponseEntity<ResponseDto> deleteNegotiation(
            @PathVariable("itemId") Long itemId,
            @PathVariable("proposalId") Long proposalId,
            @RequestBody NegotiationDto negotiationDto
    ) {
        return ResponseEntity
                .status(200)
                .body(negotiationService.deleteNegotiation(itemId, proposalId, negotiationDto));
    }
}
