package com.example.miniproject01.service;

import com.example.miniproject01.dto.ResponseDto;
import com.example.miniproject01.dto.SalesItemDto;
import com.example.miniproject01.entity.SalesItemEntity;
import com.example.miniproject01.exception.SalesItemException.SalesItemImageException;
import com.example.miniproject01.exception.SalesItemException.SalesItemNotFoundException;
import com.example.miniproject01.exception.SalesItemException.SalesItemNullException;
import com.example.miniproject01.exception.SalesItemException.SalesItemUnauthorizedException;
import com.example.miniproject01.repository.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesItemService {
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
        if(optionalSalesItem.isEmpty()){
            throw new SalesItemNotFoundException();
        }

        return optionalSalesItem.get();
    }
    // 중고 물품 판매자 확인 메소드
    private SalesItemEntity checkSeller(Long id, String writer, String password) {
        SalesItemEntity entity = checkSalesItem(id);
        // 판매자 조회
        if (entity.getWriter().equals(writer) && entity.getPassword().equals(password)) {
            return entity;
        } else {
            throw new SalesItemUnauthorizedException();
        }
    }
    // 중고 물품 등록 메소드
    public ResponseDto createSales(SalesItemDto dto) {
        // Null 아닌 경우, 예외처리
        if (dto.getTitle().isBlank() || dto.getTitle() == null ||
                dto.getDescription().isBlank() || dto.getDescription() == null ||
                dto.getMinPriceWanted() == null || dto.getWriter().isBlank() ||
                dto.getWriter() == null || dto.getPassword().isBlank() ||
                dto.getPassword() == null
        ) {
            throw new SalesItemNullException();
        }
        SalesItemEntity newSales = new SalesItemEntity();
        newSales.setTitle(dto.getTitle());
        newSales.setDescription(dto.getDescription());
        newSales.setMinPriceWanted(dto.getMinPriceWanted());
        newSales.setStatus("판매중");
        newSales.setWriter(dto.getWriter());
        newSales.setPassword(dto.getPassword());
        salesItemRepository.save(newSales);

        return setResponseMsg("등록이 완료되었습니다.");
    }
    // 중고 물품 조회 페이지 메소드
    public Page<SalesItemDto> readSalesPaged(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(
                pageNum - 1 , pageSize
        );
        Page<SalesItemEntity> salesItemEntityPage
                = salesItemRepository.findAll(pageable);

        return salesItemEntityPage.map(SalesItemDto::readSalesItemPage);
    }
    // 중고 물품 확인 및 읽어오기
    public SalesItemDto readSalesItem(Long id) {
        return SalesItemDto.readSalesItem(checkSalesItem(id));
    }
    // 중고 물품 정보 업데이트 및 수정
    public ResponseDto updateItem(Long id, SalesItemDto dto) {
        // 중고 물품 판매 확인 및 판매자 조회
        SalesItemEntity salesItemEntity = checkSeller(id, dto.getWriter(), dto.getPassword());
        salesItemEntity.setTitle(dto.getTitle());
        salesItemEntity.setDescription(dto.getDescription());
        salesItemEntity.setMinPriceWanted(dto.getMinPriceWanted());
        salesItemRepository.save(salesItemEntity);

        return setResponseMsg("물품이 수정되었습니다.");
    }
    // 중고 물품 이미지 등록 업데이트
    public ResponseDto updateSalesItemImage(Long id, MultipartFile itemImage, String writer, String password) {
        SalesItemEntity salesItemEntity = checkSeller(id, writer, password);  // 판매자 확인
        if (itemImage.isEmpty()) {  // 중고 물품 이미지가 첨부되지 않았을 때, 예외처리
            throw new SalesItemImageException();
        }

        String itemDir = String.format("media/%d/", id);
        try {
            Files.createDirectories(Path.of(itemDir));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new SalesItemImageException();
        }

        String originalFilename = itemImage.getOriginalFilename();
        String[] fileNameSplit = originalFilename.split("\\.");
        String extension = fileNameSplit[fileNameSplit.length - 1];
        String itemFilename = "salesItem." + extension;
        String itemPath = itemDir + itemFilename;

        try {
            itemImage.transferTo(Path.of(itemPath));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new SalesItemImageException();
        }
        salesItemEntity.setImageUrl(String.format("/static/%d/%s", id, itemFilename));
        salesItemRepository.save(salesItemEntity);

        return setResponseMsg("이미지가 등록되었습니다");
    }
    // 중고 물품 삭제 메소드
    public ResponseDto deleteItem(Long id, SalesItemDto dto) throws IOException {
        // 중고 물품 판매 조회 및 판매자 조회
        SalesItemEntity salesItemEntity = checkSeller(id, dto.getWriter(), dto.getPassword());
        // 등록된 중고 물품 이미지 폴더 삭제
        FileUtils.deleteDirectory(new File(String.format("media/%d",salesItemEntity.getId())));
        // 해당 중고 물품 Entity 삭제
        salesItemRepository.deleteById(salesItemEntity.getId());

        return setResponseMsg("물품을 삭제했습니다.");
    }

}
