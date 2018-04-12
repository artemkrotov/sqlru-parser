package com.example.demo.services;

import com.example.demo.models.Message;
import org.springframework.data.domain.PageRequest;

public interface ParseService {

    Message startParse(PageRequest pageRequest);

    Message statusParse(PageRequest pageRequest);

    Message startStop(PageRequest pageRequest);
}
