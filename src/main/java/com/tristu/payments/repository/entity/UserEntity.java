package com.tristu.payments.repository.entity;


import com.sun.istack.NotNull;
import com.tristu.payments.api.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_USER")
public class UserEntity {

    @Id
    @Column(name = "ID", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "USERNAME", nullable = false)
    @NotNull
    private String username;

    @Column(name = "IBAN")
    private String iban;

    @Column(name = "CNP")
    private String cnp;

    @Column(name = "walletid")
    private Integer walletId;

    public UserDto asUserDto(){
        return UserDto.builder()
                .id(getId())
                .name(getName())
                .username(getUsername())
                .iban(getIban())
                .cnp(getCnp())
                .walletId(getWalletId())
                .build();
    }
}
