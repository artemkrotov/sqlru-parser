package ru.krotov.parser.core.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.akrotov.parser.common.models.User;

import java.util.List;

public interface UserService {

    List<Long> add(List<User> users);

    Integer deleteByIds(List<Long> ids);

    List<Long> update(List<User> users);

    Page<User> findAll(PageRequest pageRequest);

    User findById(Long id);

    Page<User> findByIds(List<Long> ids, PageRequest semPageRequest);
    
}
