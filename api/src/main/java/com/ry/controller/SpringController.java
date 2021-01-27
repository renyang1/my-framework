package com.ry.controller;

import com.ry.service.IocService;
import com.ry.utils.SpringUtil;
import com.ry.vo.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2021-01-27
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@RestController
@RequestMapping("/spring")
public class SpringController  {

    @GetMapping("/getBean")
    public Response<String> getBean() {
        IocService iocService = SpringUtil.getBean(IocService.class);
        return Response.of(iocService.toString());
    }

}
