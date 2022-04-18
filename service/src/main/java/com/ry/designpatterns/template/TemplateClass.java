package com.ry.designpatterns.template;

/**
 * @author ryang
 * @Description
 * @date 2022年04月15日 9:07 上午
 */
public interface TemplateClass {
    /**
     *  利用Java8新特性，接口方法可以有默认实现，但这样该模版方法可以被子类修改，
     *  模版方法中并不一定要求模版方法不能被重写，这样能解决Java单继承问题
     *
     * @author ryang
     * @date 2022/4/15 9:20 上午
     */
    default void templateMethod() {
        method1();
        method2();
    }
    void method1();
    void method2();
}
