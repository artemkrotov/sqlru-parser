package ru.krotov.parser.core.services;

import org.springframework.data.domain.PageRequest;
import ru.akrotov.parser.common.models.Message;

public interface ParseService {

    Message startParse(PageRequest pageRequest);

    Message statusParse(PageRequest pageRequest);

    Message startStop(PageRequest pageRequest);
}
