package com.tristu.payments.repository;

import com.tristu.payments.repository.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Integer> {

    Optional<WalletEntity> findById(Integer id);
}
