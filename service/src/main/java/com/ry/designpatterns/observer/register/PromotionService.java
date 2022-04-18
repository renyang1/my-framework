package com.ry.designpatterns.observer.register;

import org.springframework.stereotype.Service;

/**
 * @author ryang
 * @Description
 * @date 2022年04月12日 11:55 上午
 */
@Service
public class PromotionService {

    public void issueNewUserExperienceCash(String userNo) {
        System.out.println("PromotionService#issueNewUserExperienceCash()");
    }
}
