package com.ry;

import com.ry.service.AbstractService;
import com.ry.service.ServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@SpringBootTest
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class AbstractServiceTest {

    @Test
    public void test() {
        AbstractService occupyInventory = ServiceFactory.getOccupyInventory("0");
        occupyInventory.m11();
    }
}