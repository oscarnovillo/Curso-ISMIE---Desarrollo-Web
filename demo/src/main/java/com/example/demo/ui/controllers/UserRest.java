package com.example.demo.ui.controllers;

import com.example.demo.data.modelo.UserEntity;
import com.example.demo.domain.modelo.User;
import com.example.demo.domain.modelo.UserVisitasDTO;
import com.example.demo.domain.servicios.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRest {


    private final UserService userService;

    public UserRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getAll()
    {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserVisitasDTO getUser(@PathVariable Long id)
    {
        return userService.getById(id);
    }

}
