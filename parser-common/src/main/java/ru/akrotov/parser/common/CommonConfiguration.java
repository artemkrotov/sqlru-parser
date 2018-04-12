package ru.akrotov.parser.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(value = {"ru.akrotov.parser.common.*"})
public class CommonConfiguration {
}
