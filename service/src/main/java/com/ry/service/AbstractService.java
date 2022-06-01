package com.ry.service;

import com.ry.mapper.TaMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ryang
 * @Description
 * @date 2022年05月20日 6:34 下午
 */
public abstract class AbstractService {

    @Autowired
    private TransactionalService1 transactionalService1;

    @Autowired
    private TaMapper taMapper;

    public abstract void m1();

    public final void m11() {
        m1();
        m3();
    }

    private void m2() {
        int i = taMapper.updateById(1);
        System.out.println(i);
    }

    private void m3() {
        transactionalService1.updateTaById(1);
    }
}
