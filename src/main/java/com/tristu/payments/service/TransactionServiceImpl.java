package com.tristu.payments.service;

import com.itextpdf.text.DocumentException;
import com.tristu.payments.api.TransactionService;
import com.tristu.payments.api.UserService;
import com.tristu.payments.api.WalletService;
import com.tristu.payments.api.dto.TransactionDto;
import com.tristu.payments.api.dto.UserDto;
import com.tristu.payments.repository.TransactionRepository;
import com.tristu.payments.repository.entity.TransactionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final UserService userService;
    private final TransactionRepository transactionRepository;
    private final ReportGenerator reportGenerator;
    private final WalletService walletService;

    @Override
    public void saveTransaction(TransactionDto transaction) {
        switch (transaction.getTransactionType()) {
            case IBAN_TO_WALLET:
                persistWalletTransactionToPayee(transaction);
                break;
            case WALLET_TO_IBAN:
                persistWalletTransactionFromPayer(transaction);
                break;
            case WALLET_TO_WALLET:
                persistWalletTransactionToPayee(transaction);
                persistWalletTransactionFromPayer(transaction);
                break;
            default:
                break;
        }

        TransactionEntity transactionEntityDb = TransactionEntity.builder()
                .transactionType(transaction.getTransactionType().toString())
                .payerCnp(transaction.getPayerCnp())
                .payerIban(transaction.getPayerIban())
                .payeeCnp(transaction.getPayeeCnp())
                .payeeIban(transaction.getPayeeIban())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .build();
        transactionRepository.save(transactionEntityDb);
    }

    private void persistWalletTransactionToPayee(TransactionDto transaction) {
        UserDto userForWallet = userService.findByCnp(transaction.getPayeeCnp()).orElseThrow(EntityNotFoundException::new);
        walletService.updateUserWallet(userForWallet.getWalletId(), transaction.getAmount());

    }

    private void persistWalletTransactionFromPayer(TransactionDto transaction) {
        UserDto userForWallet = userService.findByCnp(transaction.getPayerCnp()).orElseThrow(EntityNotFoundException::new);
        walletService.updateUserWallet(userForWallet.getWalletId(), transaction.getAmount().negate());

    }

    @Override
    public byte[] getTransactionsForUser(String username) throws FileNotFoundException, DocumentException {
        UserDto user = userService.findByUsername(username).orElseThrow(EntityNotFoundException::new);

        List<TransactionDto> userTransactionEntities = transactionRepository.findAllByPayerCnpOrderByTransactionType(user.getCnp())
                .stream().map(TransactionEntity::asTransactionDto).collect(Collectors.toList());
        return reportGenerator.generateTransactionReport(user, userTransactionEntities);

    }
}
