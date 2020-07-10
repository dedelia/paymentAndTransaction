package com.tristu.payments.repository.entity;

import com.sun.istack.NotNull;
import com.tristu.payments.api.dto.WalletDto;
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
@Table(name = "T_WALLET")
public class WalletEntity {

    @Id
    @Column(name = "ID", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "AMOUNT", nullable = false)
    @NotNull
    private BigDecimal amount;

    public WalletDto asWalletDto() {
        return WalletDto.builder()
                .id(getId())
                .amount(getAmount())
                .build();
    }
}
