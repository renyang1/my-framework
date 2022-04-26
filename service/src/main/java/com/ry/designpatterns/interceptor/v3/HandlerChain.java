package com.ry.designpatterns.interceptor.v3;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author ryang
 * @Description
 * @date 2022年04月21日 11:36 下午
 */
public class HandlerChain {
    private List<IHandler> handlers = Lists.newArrayList();

    public void addHandler(IHandler handler) {
        handlers.add(handler);
    }

    public void handle() {
        handlers.forEach(handler -> {
            boolean handled = handler.handle();
            if (handled) {
                return;
            }
        });
    }

    public static void main(String[] args) {
        HandlerChain handlerChain = new HandlerChain();
        handlerChain.addHandler(new HandleA());
        handlerChain.addHandler(new HandleB());

        handlerChain.handle();
    }
}
