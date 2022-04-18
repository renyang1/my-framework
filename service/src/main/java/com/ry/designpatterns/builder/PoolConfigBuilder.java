package com.ry.designpatterns.builder;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * Builder类设计为非内部类的实现方式：
 *  1.需要将Builder类做为参数传入目标类的构造函数，所以目标类需要提供一个protected或者public的有参构造函数
 *  2.由于目标类暴露了有参构造函数，此时目标类参数合法性校验需要放到有参构造函数中，即需要Builder类提供一个校验方法
 *
 * @author ryang
 */
@Getter
public class PoolConfigBuilder {
    private String name;
    private int minTotal;
    private int maxTotal;
    private boolean isDelete;

    /**
     * 通过该方法创建建造者模式需要创建的对象，保证类属性的合法性
     *
     * @return
     */
    public PoolConfig builder() {
//        check();
        return new PoolConfig(this);
    }

    /**
     * 校验参数是否合法
     */
    public void check() {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("...");
        }

        if (minTotal > maxTotal) {
            throw new IllegalArgumentException("...");
        }
    }

    public PoolConfigBuilder setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name不能为空");
        }
        this.name = name;
        return this;
    }

    public PoolConfigBuilder setMinTotal(int minTotal) {
        if (minTotal < 0) {
            throw new IllegalArgumentException("minTotal 不能小于0");
        }
        this.minTotal = minTotal;
        return this;
    }

    public PoolConfigBuilder setMaxTotal(int maxTotal) {
        if (maxTotal < 0) {
            throw new IllegalArgumentException("minTotal 不能小于0");
        }
        this.maxTotal = maxTotal;
        return this;
    }

    public PoolConfigBuilder setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }
}
