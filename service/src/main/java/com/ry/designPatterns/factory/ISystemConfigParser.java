package com.ry.designPatterns.factory;

/**
 * @author ryang
 * @Description
 * @date 2022年04月01日 11:50 下午
 */
public interface ISystemConfigParser {

    /**
     *
     * @author ryang
     * @date 2022/4/1 11:51 下午
     * @param configText
     * @return com.ry.designPatterns.factory.RuleConfig
     */
    RuleConfig parse(String configText);

}
