package com.ry.designPatterns.proxy.dynamicproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2020-12-22
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
public class MapperDynamicProxy {

    public Object creatProxy(Object proxiedObject) {
        return Proxy.newProxyInstance(
                proxiedObject.getClass().getClassLoader(),
                proxiedObject.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理逻辑，方法执行前执行");
                        Object result = method.invoke(proxiedObject, args);
                        System.out.println("代理逻辑，方法执行后执行");
                        return result;
                    }
                });
    }

    public static void main(String[] args) {
        MapperDynamicProxy proxy = new MapperDynamicProxy();
        Mapper mapper = (Mapper) proxy.creatProxy(new UserMapper());
        mapper.select();
    }
}
