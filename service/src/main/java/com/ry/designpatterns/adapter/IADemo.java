package com.ry.designpatterns.adapter;

/**
 * 项目中使用外部系统A示例
 * @author ryang
 * @Description
 * @date 2022年04月11日 3:40 下午
 */
public class IADemo {

    private IA a;

    public IADemo(IA a) {
        this.a = a;
    }

    public static void main(String[] args) {
        // 使用A系统对象
        IADemo demo = new IADemo(new A());

        // 使用B系统对象,无需修改别的逻辑，只需用BAdaptor替换调A即可
        IADemo demo1 = new IADemo(new BAdaptor(new B()));
    }
}
