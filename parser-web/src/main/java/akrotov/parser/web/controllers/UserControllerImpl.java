package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;

public class UserControllerImpl implements UserController {

    @Override
    public Page<User> findAll(PageRequest pageRequest) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public Page<User> findByIds(List<Long> ids, PageRequest semPageRequest) {
        return null;
    }
}
