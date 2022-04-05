package com.ry.designPatterns.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂方法的简单工厂
 * @author ryang
 * @Description
 * @date 2022年04月05日 12:31 上午
 */
public class RuleConfigParseFactoryMap {
    private static final Map<String, IRuleConfigParserFactory> configParserFactoryMap = new HashMap<>();

    static {
       configParserFactoryMap.put("json", new JsonRuleConfigParserFactory());
       configParserFactoryMap.put("xml", new XmlRuleConfigParserFactory());
       configParserFactoryMap.put("yaml", new YamlRuleConfigParserFactory());
    }

    public static IRuleConfigParserFactory getParserFactory(String configType) {
        return configParserFactoryMap.get(configType);
    }
}
