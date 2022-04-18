package com.ry.designpatterns.factory;

/**
 * @author ryang
 * @Description
 * @date 2022年04月01日 11:42 下午
 */
public class YamlConfigParser implements IRuleConfigParser{

    @Override
    public RuleConfig parse(String configText) {
        System.out.println("YamlConfigParser:parse()");
        return new RuleConfig();
    }
}
