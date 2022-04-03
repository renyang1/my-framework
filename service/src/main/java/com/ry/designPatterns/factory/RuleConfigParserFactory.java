package com.ry.designPatterns.factory;

import java.util.Objects;

/**
 * 简单工厂，通过if...else...分支来控制实例化出通类型的不同对象，且每次是返回一个新的对象
 * @author ryang
 * @Description
 * @date 2022年04月01日 11:49 下午
 */
public class RuleConfigParserFactory {

    public static IRuleConfigParser createParser(String configType) {
        IRuleConfigParser configParser;
        if (Objects.equals(configType, "json")) {
            configParser = new JsonConfigParser();
        }else if (Objects.equals(configType, "xml")) {
            configParser = new XmlConfigParser();
        } else if (Objects.equals(configType, "yaml")) {
            configParser = new YamlConfigParser();
        }else {
            throw new RuntimeException("类型错误。。。");
        }
        return configParser;
    }

}
