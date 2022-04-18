package com.ry.designpatterns.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂的第二种实现方式：事先缓存对象，每次返回同样的对象
 * @author ryang
 * @Description
 * @date 2022年04月03日 11:37 下午
 */
public class RuleConfigParserFactoty1 {

    private static final Map<String, IRuleConfigParser> ruleConfigParserMap = new HashMap<>(3);

    static {
        ruleConfigParserMap.put("json", new JsonConfigParser());
        ruleConfigParserMap.put("xml", new XmlConfigParser());
        ruleConfigParserMap.put("yaml", new YamlConfigParser());
    }

    public static IRuleConfigParser createParser(String configType) {
        return ruleConfigParserMap.get(configType);
    }
}
