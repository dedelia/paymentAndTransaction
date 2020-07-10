package com.tristu.payments.api;

import com.tristu.payments.api.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> findByUsername(String username);

    Optional<UserDto> findByCnp(String cnp);


}
