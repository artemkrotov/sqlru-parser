package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {

    @RequestMapping(path = ControllerConstants.USERS, method = RequestMethod.GET)
    Page<User> findAll(PageRequest pageRequest);

    @RequestMapping(path = ControllerConstants.USERS_BY_ID, method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);

    @RequestMapping(path = ControllerConstants.USERS, params = "ids", method = RequestMethod.GET)
    Page<User> findByIds(@RequestParam("ids") List<Long> ids, PageRequest semPageRequest);

}
