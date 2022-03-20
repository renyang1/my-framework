package com.ry.designPatterns.builder;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ryang
 */
@Getter
@ToString
public class PoolConfig {
    private String name;
    private int minTotal;
    private int maxTotal;
    private boolean isDelete;

    /**
     * 私有有参构造函数，本类中可以被调用
     */
    private PoolConfig(Builder builder) {
        this.name = builder.name;
        this.minTotal = builder.minTotal;
        this.maxTotal = builder.maxTotal;
        this.isDelete = builder.isDelete;
    }

    protected PoolConfig(PoolConfigBuilder poolConfigBuilder) {
        poolConfigBuilder.check();
        this.name = poolConfigBuilder.getName();
        this.minTotal = poolConfigBuilder.getMinTotal();
        this.maxTotal = poolConfigBuilder.getMaxTotal();
        this.isDelete = poolConfigBuilder.isDelete();
    }

    /**
     * 建造者类（静态内部类）
     */
    public static class Builder {
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
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("...");
            }

            if (minTotal > maxTotal) {
                throw new IllegalArgumentException("...");
            }
            return new PoolConfig(this);
        }

        public Builder setName(String name) {
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("name不能为空");
            }
            this.name = name;
            return this;
        }

        public Builder setMinTotal(int minTotal) {
            if (minTotal < 0) {
                throw new IllegalArgumentException("minTotal 不能小于0");
            }
            this.minTotal = minTotal;
            return this;
        }

        public Builder setMaxTotal(int maxTotal) {
            if (maxTotal < 0) {
                throw new IllegalArgumentException("minTotal 不能小于0");
            }
            this.maxTotal = maxTotal;
            return this;
        }

        public Builder setIsDelete(boolean isDelete) {
            this.isDelete = isDelete;
            return this;
        }
    }
}
