package com.tristu.payments.rest;

import com.tristu.payments.api.UserService;
import com.tristu.payments.api.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username).orElseThrow(EntityExistsException::new);
    }

    @GetMapping("/by-cnp/{cnp}")
    public UserDto findByCnp(@PathVariable String cnp) {
        return userService.findByCnp(cnp).orElseThrow(EntityExistsException::new);
    }

}
