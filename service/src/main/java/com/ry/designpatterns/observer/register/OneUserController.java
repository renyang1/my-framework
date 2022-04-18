package com.ry.designpatterns.observer.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ryang
 * @Description
 * @date 2022年04月12日 11:54 上午
 */
@RestController
@RequestMapping("/oneUserController")
public class OneUserController {

    @Autowired
    private OneUserService oneUserService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private List<RegisterObserver> registerObservers;

    @GetMapping("/register")
    public String register(@RequestParam("name") String phone, @RequestParam("password") String password) {
        // 注册
        String userNo = oneUserService.register(phone, password);

//        // 新人优惠
//        promotionService.issueNewUserExperienceCash(userNo);
        // 遍历所有观察者，执行观察者处理逻辑
        for (RegisterObserver registerObserver : registerObservers) {
            registerObserver.handleRegisterSuccess(userNo);
        }
        return userNo;
    }
}
