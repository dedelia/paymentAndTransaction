package com.tristu.payments.service;

import com.tristu.payments.api.WalletService;
import com.tristu.payments.repository.WalletRepository;
import com.tristu.payments.repository.entity.WalletEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    @Transactional
    public void updateUserWallet(Integer walletId, BigDecimal amount) {
        WalletEntity walletdb = walletRepository.findById(walletId).orElseThrow(EntityNotFoundException::new);
        walletdb.setAmount(walletdb.getAmount().add(amount));
        walletRepository.save(walletdb);
    }
}
