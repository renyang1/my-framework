package com.ry.designpatterns.factory;

import org.junit.Test;

public class RuleConfigParserFactoryTest {

    @Test
    public void createParser() {
        IRuleConfigParser json = RuleConfigParserFactory.createParser("json");
        System.out.println(json.parse("ryang"));
    }

}