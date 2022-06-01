package com.ry.service;

import com.ry.mapper.TbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ryang
 * @Description
 * @date 2022年05月10日 11:03 上午
 */
@Service
public class TransactionalService1Impl implements TransactionalService1{

     @Autowired
     private TbMapper tbMapper;

   @Override
    public void updateTaById(int id){

   }


}
