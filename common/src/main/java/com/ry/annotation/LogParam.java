package com.ry.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2020-09-18
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogParam {
}
