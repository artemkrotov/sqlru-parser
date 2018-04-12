package com.example.demo.controllers;

import com.example.demo.models.Message;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface ParseController {

    @RequestMapping(path = "/api/parse/start", method = RequestMethod.GET)
    Message startParse(PageRequest pageRequest);

    @RequestMapping(path = "/api/parse/status", method = RequestMethod.GET)
    Message statusParse(PageRequest pageRequest);

    @RequestMapping(path = "/api/parse/stop", method = RequestMethod.GET)
    Message startStop(PageRequest pageRequest);

}
