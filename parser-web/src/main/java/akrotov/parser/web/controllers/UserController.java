package akrotov.parser.web.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.akrotov.parser.common.models.User;

import java.util.List;

public interface UserController {

    @RequestMapping(path = ControllerConstants.USERS, method = RequestMethod.GET)
    Page<User> findAll(PageRequest pageRequest);

    @RequestMapping(path = ControllerConstants.USERS_BY_ID, method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);

    @RequestMapping(path = ControllerConstants.USERS, params = "ids", method = RequestMethod.GET)
    Page<User> findByIds(@RequestParam("ids") List<Long> ids, PageRequest semPageRequest);

}
