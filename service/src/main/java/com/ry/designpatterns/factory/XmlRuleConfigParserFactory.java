package com.ry.designpatterns.factory;

/**
 * @author ryang
 * @Description
 * @date 2022年04月05日 12:17 上午
 */
public class XmlRuleConfigParserFactory implements IRuleConfigParserFactory{
    @Override
    public IRuleConfigParser createParser() {
        return new XmlConfigParser();
    }
}
