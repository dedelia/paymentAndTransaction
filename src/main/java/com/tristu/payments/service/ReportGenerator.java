package com.tristu.payments.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.tristu.payments.api.dto.TransactionDto;
import com.tristu.payments.api.dto.TransactionType;
import com.tristu.payments.api.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
public class ReportGenerator {

    private static final Font font = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
    private static final Paragraph linebreak = new Paragraph("\n ");

    public byte[] generateTransactionReport(UserDto user, List<TransactionDto> userTransactionList) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, stream);
        document.open();

        addUserInfo(user, document);
        addTransactions(userTransactionList, document);

        document.close();
        return stream.toByteArray();
    }

    private void addUserInfo(UserDto user, Document document) throws DocumentException {
        document.add(new Chunk("Name : " + user.getName(), font));
        document.add(linebreak);
        document.add(new Chunk("CNP : " + user.getCnp(), font));
        document.add(linebreak);
        document.add(new Chunk("IBAN : " + user.getIban(), font));
        document.add(linebreak);
    }

    private void addTransactions(List<TransactionDto> transactionList, Document document) throws DocumentException {
        document.add(new Chunk("Transactions : ", font));
        addTransactionForType(TransactionType.IBAN_TO_IBAN, transactionList, document);
        addTransactionForType(TransactionType.IBAN_TO_WALLET, transactionList, document);
        addTransactionForType(TransactionType.WALLET_TO_IBAN, transactionList, document);
        addTransactionForType(TransactionType.WALLET_TO_WALLET, transactionList, document);
        document.add(linebreak);
    }

    private void addTransactionForType(TransactionType type, List<TransactionDto> transactionList, Document document) throws DocumentException {
        document.add(linebreak);
        document.add(new Chunk(type.toString() + " | " +
                // Current type transaction count
                +transactionList.stream().filter(t -> type.equals(t.getTransactionType())).count() + "  transactions | "
                //Current type transactions sum
                + transactionList.stream().filter(t -> type.equals(t.getTransactionType()))
                .filter(transaction -> !transaction.getAmount().equals(BigDecimal.ZERO))
                .map(TransactionDto::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add) + " RON ", font));

        document.add(linebreak);
        //Current type type transactions:
        transactionList.stream().filter(transaction -> transaction.getTransactionType().equals(type))
                .forEach(transaction -> {
                    try {
                        document.add(new Chunk(
                                " Payee IBAN : " + transaction.getPayeeIban() +
                                        " Description : " + transaction.getDescription() +
                                        " Amount : " + transaction.getAmount() + "\n"));
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                });
        document.add(linebreak);

    }

}
