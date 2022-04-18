package com.ry.designpatterns.adapter;

/**
 * @author ryang
 * @Description
 * @date 2022年04月11日 5:31 下午
 */
public class BAdaptor implements IA {

    private B b;

    public BAdaptor(B b) {
        this.b = b;
    }

    @Override
    public void fa() {
        b.fb();
    }
}
