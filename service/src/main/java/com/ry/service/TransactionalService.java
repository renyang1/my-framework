package com.ry.service;

import com.ry.mapper.TaMapper;
import com.ry.mapper.TbMapper;
import com.ry.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author ryang
 * @Description
 * @date 2022年05月10日 11:03 上午
 */
@Component
@Slf4j
public class TransactionalService {

    @Autowired
    private TaMapper taMapper;
    @Autowired
    private TbMapper tbMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void testTransactional1(int taId, int tbId) {
        SpringUtil.getBean(TransactionalService.class).updateTaById(taId);
        try {
            SpringUtil.getBean(TransactionalService.class).updateTbById(tbId);
        } catch (Exception e) {
            log.error("操作失败", e);
        }
        // 异常代码
        // int i = 1/0;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateTaById(int id) {
        taMapper.updateById(id);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void updateTbById(int id) {
        tbMapper.updateById(id);
        System.out.println(1 / 0);
    }
}
