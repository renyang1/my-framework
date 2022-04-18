package com.ry.designpatterns.observer.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ryang
 * @Description
 * @date 2022年04月12日 11:55 上午
 */
@Component
public class PromotionObserver implements RegisterObserver{
    @Autowired
    private PromotionService promotionService;

    @Override
    public void handleRegisterSuccess(String userNo) {
        System.out.println("PromotionObserver#handleRegisterSuccess()...");
        promotionService.issueNewUserExperienceCash(userNo);
    }
}
