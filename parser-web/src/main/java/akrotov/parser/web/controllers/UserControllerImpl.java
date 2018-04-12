package akrotov.parser.web.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.akrotov.parser.common.models.User;

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
