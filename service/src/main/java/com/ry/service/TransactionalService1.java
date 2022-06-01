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

public interface TransactionalService1 {





     void updateTaById(int id);


}
