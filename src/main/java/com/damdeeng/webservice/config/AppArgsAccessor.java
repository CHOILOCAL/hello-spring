package com.damdeeng.webservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AppArgsAccessor {

    @Autowired
    public AppArgsAccessor(ApplicationArguments args) {
        boolean debug = args.containsOption("debug");
        log.debug("debug={}", debug);
        List<String> files = args.getNonOptionArgs();
        log.debug("files={}", files);

    }

}
