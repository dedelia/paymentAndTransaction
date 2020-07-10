package com.tristu.payments.api.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @NonNull
    private long id;
    private String name;
    @NonNull
    private String username;
    @NonNull
    private String iban;
    @NonNull
    private String cnp;
    @NonNull
    private int walletId;

}
