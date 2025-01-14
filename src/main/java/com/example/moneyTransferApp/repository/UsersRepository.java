package com.example.moneyTransferApp.repository;

import com.example.moneyTransferApp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByNumber(String number);
    Boolean existsByNumber(String number);

}
