package com.ry.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author 10023991
 */
@Slf4j
@Service
@Order(1) // 多个实现类时，指定执行顺序
public class MyApplicationRunnerComponent implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("MyApplicationRunnerComponent: run()");
    }
}
