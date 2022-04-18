package com.ry.designpatterns.template.callback;

/**
 * @author ryang
 * @Description
 * @date 2022年04月17日 3:24 下午
 */
public class AClass {
    public static void main(String[] args) {
        BClass b = new BClass();
        b.process(new ICallback() {
            @Override
            public void methodToCallback() {
                System.out.println("ICallback#methodToCallback()...");
            }
        });
    }
}
