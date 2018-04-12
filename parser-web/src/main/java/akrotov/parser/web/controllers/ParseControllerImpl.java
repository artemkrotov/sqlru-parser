package akrotov.parser.web.controllers;

import org.springframework.data.domain.PageRequest;
import ru.akrotov.parser.common.models.Message;

public class ParseControllerImpl implements ParseController {

    @Override
    public Message startParse(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Message statusParse(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Message startStop(PageRequest pageRequest) {
        return null;
    }
}
