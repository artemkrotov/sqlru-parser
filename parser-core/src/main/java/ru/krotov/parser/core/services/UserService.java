package com.example.demo.services;

import com.example.demo.controllers.ControllerConstants;
import com.example.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserService {

    List<Long> add(List<User> users);

    Integer deleteByIds(List<Long> ids);

    List<Long> update(List<User> users);

    Page<User> findAll(PageRequest pageRequest);

    User findById(Long id);

    Page<User> findByIds(List<Long> ids, PageRequest semPageRequest);
    
}
