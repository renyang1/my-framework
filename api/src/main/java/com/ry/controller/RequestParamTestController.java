package com.ry.controller;

import com.ry.entity.Response;
import com.ry.enums.SexEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 接口参数测试类
 *
 * @author renyang
 * @date 2020-09-16
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@RestController
@RequestMapping("/test")
public class RequestParamTestController {

    /**
     * 1.接口入参字段类型为枚举时，前端可以传枚举类型名称，这时会进行自动转换，若转换失败抛出异常ConversionFailedException
     * 2.接口返回字段类型为枚举时，前端得到的时枚举类型名称
     * */
    @RequestMapping("/get")
    public Response<SexEnum> getEnumValue(SexEnum sexEnum) {
        return Response.of(SexEnum.UNKONWN);
    }



}
