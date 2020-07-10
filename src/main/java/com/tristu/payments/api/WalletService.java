package com.tristu.payments.api;

import java.math.BigDecimal;

public interface WalletService {

    public void updateUserWallet(Integer walletId, BigDecimal amount);
}
