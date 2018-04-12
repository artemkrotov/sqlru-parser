package akrotov.parser.web.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.akrotov.parser.common.models.Message;

public interface ParseController {

    @RequestMapping(path = "/api/parse/start", method = RequestMethod.GET)
    Message startParse(PageRequest pageRequest);

    @RequestMapping(path = "/api/parse/status", method = RequestMethod.GET)
    Message statusParse(PageRequest pageRequest);

    @RequestMapping(path = "/api/parse/stop", method = RequestMethod.GET)
    Message startStop(PageRequest pageRequest);

}
