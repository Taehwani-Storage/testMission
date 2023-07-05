package com.example.miniproject01.controller;

import com.example.miniproject01.dto.ResponseDto;
import com.example.miniproject01.dto.SalesItemDto;
import com.example.miniproject01.service.SalesItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class SalesItemController {
    private final SalesItemService service;
    // 물건 등록 메소드, 성공적이면 메시지 출력
    @PostMapping
    public ResponseEntity<ResponseDto> create(
            @RequestBody SalesItemDto salesItemDto
    ) {
        return ResponseEntity
                .status(200)
                .body(service.createSales(salesItemDto));
    }
    // 물품 조회 페이지(1~25) 메소드, 성공적이면 메시지 출력
    @GetMapping
    public ResponseEntity<Page<SalesItemDto>> readAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "25") Integer size
    ) {
        return ResponseEntity
                .status(200)
                .body(service.readSalesPaged(page, size));
    }
    // 물품 조회 메소드, 성공적이면 메시지 출력
    @GetMapping("/{itemId}")
    public ResponseEntity<SalesItemDto> readOne(
            @PathVariable("itemId") Long itemId
    ) {
        return ResponseEntity
                .status(200)
                .body(service.readSalesItem(itemId));
    }
    // 물품 정보를 통해 판매 물품 업데이트 및 수정, 성공적이면 메시지 출력
    @PutMapping("/{itemId}")
    public ResponseEntity<ResponseDto> update(
            @PathVariable("itemId") Long itemId,
            @RequestBody SalesItemDto salesItemDto
    ) {
        return ResponseEntity
                .status(200)
                .body(service.updateItem(itemId, salesItemDto));
    }
    // 판매 물품 이미지 업데이트 메소드, 성공적이면 메시지 출력
    @PutMapping(value = "/{itemId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> updateImage(
            @PathVariable("itemId") Long itemId,
            @RequestParam("image") MultipartFile itemImage,
            String writer,
            String password
    ) {
        return ResponseEntity
                .status(200)
                .body(service.updateSalesItemImage(itemId, itemImage, writer, password));
    }
    // 판매 물품 삭제 메소드, 성공적이면 메시지 출력
    @DeleteMapping("/{itemId}")
    public ResponseEntity<ResponseDto> delete(
            @PathVariable("itemId") Long itemId,
            @RequestBody SalesItemDto salesItemDto
    ) throws IOException {
        return ResponseEntity
                .status(200)
                .body(service.deleteItem(itemId, salesItemDto));
    }
}
