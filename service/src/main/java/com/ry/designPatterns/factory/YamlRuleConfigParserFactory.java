package com.ry.designPatterns.factory;

/**
 * @author ryang
 * @Description
 * @date 2022年04月05日 12:17 上午
 */
public class YamlRuleConfigParserFactory implements IRuleConfigParserFactory{
    @Override
    public IRuleConfigParser createParser() {
        return new YamlConfigParser();
    }
}
