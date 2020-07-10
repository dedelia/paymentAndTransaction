package com.tristu.payments.repository.entity;

import com.sun.istack.NotNull;
import com.tristu.payments.api.dto.TransactionDto;
import com.tristu.payments.api.dto.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_TRANSACTION")
public class TransactionEntity {

    @Id
    @Column(name = "ID", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TRANSACTION_TYPE", nullable = false)
    @NotNull
    private String transactionType;

    @Column(name = "PAYERIBAN")
    private String payerIban;

    @Column(name = "PAYERCNP")
    private String payerCnp;

    @Column(name = "PAYEEIBAN")
    private String payeeIban;

    @Column(name = "PAYEECNP")
    private String payeeCnp;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    public TransactionDto asTransactionDto() {
        return TransactionDto.builder()
                .transactionType(TransactionType.valueOf(getTransactionType()))
                .payerCnp(getPayerCnp())
                .payerIban(getPayerIban())
                .payeeCnp(getPayeeCnp())
                .payeeIban(getPayeeIban())
                .description(getDescription())
                .amount(getAmount())
                .build();
    }
}
