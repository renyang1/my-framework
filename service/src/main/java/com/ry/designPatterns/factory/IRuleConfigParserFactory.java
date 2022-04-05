package com.ry.designPatterns.factory;

/**
 * 工厂接口
 * @author ryang
 * @Description
 * @date 2022年04月04日 5:18 下午
 */
public interface IRuleConfigParserFactory {
    IRuleConfigParser createParser();
}
