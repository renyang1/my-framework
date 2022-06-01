package com.ry.service;

import com.google.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ryang
 * @Description
 * @date 2022年05月20日 6:34 下午
 */
@Service
public  class ServiceFactory implements ApplicationContextAware {

    private static final Map<String, AbstractService> map = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, AbstractService> beans = applicationContext.getBeansOfType(AbstractService.class);
        ArrayList<AbstractService> abstractServices = Lists.newArrayList(beans.values());
        for (int i1 = 0; i1 < abstractServices.size(); i1++) {
            map.put(i1 + "", abstractServices.get(i1));
        }
    }

    public static AbstractService getOccupyInventory(String type) {
        AbstractService abstractOccupyInventory = map.get(type);
        return abstractOccupyInventory;
    }
}
