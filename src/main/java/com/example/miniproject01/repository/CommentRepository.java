package com.example.miniproject01.repository;

import com.example.miniproject01.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository
        extends JpaRepository<CommentEntity, Long> {
    Page<CommentEntity> findAllBySalesItemId(Long itemId, Pageable pageable);

}
