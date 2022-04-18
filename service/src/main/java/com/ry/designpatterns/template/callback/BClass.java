package com.ry.designpatterns.template.callback;

/**
 * @author ryang
 * @Description
 * @date 2022年04月17日 3:19 下午
 */
public class BClass {
    public void process(ICallback callback) {
        System.out.println("BClass#process()...");
        // 执行回调逻辑
        callback.methodToCallback();
    }
}
