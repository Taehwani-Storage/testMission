package com.example.miniproject01.repository;

import com.example.miniproject01.entity.SalesItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesItemRepository
        extends JpaRepository<SalesItemEntity, Long> {

}
