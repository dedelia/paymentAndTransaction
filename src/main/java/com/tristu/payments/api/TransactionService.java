package com.tristu.payments.api;

import com.itextpdf.text.DocumentException;
import com.tristu.payments.api.dto.TransactionDto;

import java.io.FileNotFoundException;

public interface TransactionService {

    void saveTransaction(TransactionDto transaction);

    byte[] getTransactionsForUser(String username) throws FileNotFoundException, DocumentException;

}
