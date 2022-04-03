package com.ry.designPatterns.factory;

/**
 * @author ryang
 * @Description
 * @date 2022年04月01日 11:42 下午
 */
public class XmlConfigParser implements IRuleConfigParser{

    @Override
    public RuleConfig parse(String configText) {
        System.out.println("XmlConfigParser:parse()");
        return new RuleConfig();
    }
}
