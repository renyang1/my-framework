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
@Order(1) // 指定执行顺序
public class MyCommandLineRunnerComponent implements CommandLineRunner {

    @Autowired
    private IocService iocService;

    @Override
    public void run(String... args) throws Exception {
        log.info("MyCommandLineRunnerComponent: run()");
        iocService.test();
    }
}
