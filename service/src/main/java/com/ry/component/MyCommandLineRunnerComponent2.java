package com.ry.component;

import com.ry.service.IocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author renyang
 */
@Slf4j
@Component
@Order(2)
public class MyCommandLineRunnerComponent2 implements CommandLineRunner {

    @Autowired
    private IocService iocService;

    @Override
    public void run(String... args) throws Exception {
        log.info("MyCommandLineRunnerComponent2: run()");
        iocService.test();
    }
}
