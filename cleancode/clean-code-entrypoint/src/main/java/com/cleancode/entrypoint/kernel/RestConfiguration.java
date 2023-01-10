package com.cleancode.entrypoint.kernel;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.cleancode.entrypoint.restcontrollers")
public class RestConfiguration {
}
