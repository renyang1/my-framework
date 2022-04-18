package com.ry.designpatterns.template;

/**
 * @author ryang
 * @Description
 * @date 2022年04月15日 9:10 上午
 */
public class ConcreteClass implements TemplateClass{
    @Override
    public void method1() {
        System.out.println("ConcreteClass#method1()...");
    }

    @Override
    public void method2() {
        System.out.println("ConcreteClass#method2()...");
    }

    public static void main(String[] args) {
        TemplateClass templateClass = new ConcreteClass();
        templateClass.templateMethod();
    }
}
