package com.example.moneyTransferApp.repository;

import com.example.moneyTransferApp.entity.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServicesRepository extends JpaRepository<Services, UUID> {
    Page<Services> findByProviderId(UUID id, Pageable pageable);
    List<Services> findByProviderId(UUID id);
}
