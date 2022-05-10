package com.ry;

import com.ry.service.TransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TransactionalServiceTest {

    @Autowired
    private TransactionalService transactionalService;

    @Test
    public void testTransactional1() {
        transactionalService.testTransactional1(1,1);
    }
}