package com.example.moneyTransferApp.repository;

import com.example.moneyTransferApp.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, UUID> {
}
