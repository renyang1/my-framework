package com.ry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ryang
 * @Description
 * @date 2022年05月14日 10:54 下午
 */
@Component
public class CircularDependencyB {
    @Autowired
    private CircularDependencyA circularDependencyA;
}
