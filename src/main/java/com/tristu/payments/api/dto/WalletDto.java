package com.tristu.payments.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletDto {

    @NonNull
    private Integer id;
    @NonNull
    private BigDecimal amount;
}
