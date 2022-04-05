package com.ry.designPatterns.factory;

/**
 * 抽象工厂接口
 * @author ryang
 * @Description
 * @date 2022年04月04日 5:18 下午
 */
public interface IRuleConfigParserFactory1 {
    /**
     * 规则类型工厂
     * @author ryang
     * @date 2022/4/5 12:47 上午
     * @return com.ry.designPatterns.factory.IRuleConfigParser
     */
    IRuleConfigParser createParser();

    /**
     * 系统类型工厂
     * @author ryang
     * @date 2022/4/5 12:47 上午
     * @return com.ry.designPatterns.factory.ISystemConfigParser
     */
    ISystemConfigParser createSystemParser();
}
