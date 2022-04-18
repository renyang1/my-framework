package com.ry.designpatterns.adapter;

public class Demo {

    public void f1(ITarget target) {
        target.a();
    }

    public static void main(String[] args) {
        new Demo().f1(new Adaptor());

        new Demo().f1(new AdaptorA(new Adaptee()));
    }
}
