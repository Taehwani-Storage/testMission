package com.example.miniproject01.repository;

import com.example.miniproject01.entity.NegotiationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NegotiationRepository
        extends JpaRepository<NegotiationEntity, Long> {
    List<NegotiationEntity> findAllBySalesItemId(Long itemId);
    Page<NegotiationEntity> findAllBySalesItemId(
            Long itemId, Pageable pageable);
    Page<NegotiationEntity> findAllBySalesItemIdAndWriterAndPassword(
            Long itemId, String writer, String password, Pageable pageable);

}
