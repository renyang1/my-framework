package com.ry.designpatterns.factory;

/**
 * @author ryang
 * @Description
 * @date 2022年04月05日 12:17 上午
 */
public class JsonRuleConfigParserFactory1 implements IRuleConfigParserFactory1{
    @Override
    public IRuleConfigParser createParser() {
        return new JsonConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return null;
    }
}
