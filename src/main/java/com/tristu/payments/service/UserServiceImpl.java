package com.tristu.payments.service;

import com.tristu.payments.api.UserService;
import com.tristu.payments.api.dto.UserDto;
import com.tristu.payments.repository.UserRepository;
import com.tristu.payments.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username).map(UserEntity::asUserDto);
    }

    public Optional<UserDto> findByCnp(String cnp) {
        return userRepository.findByCnp(cnp).map(UserEntity::asUserDto);
    }

}
