package com.ry.res;

import lombok.Data;

import java.util.Date;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2020-09-04
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
public interface UserRes {

    @Data
    class Create {
        /**
         * 用户名 用户名
         */
        private String username;

        /**
         * 密码 密码
         */
        private String password;

        /**
         * 昵称 昵称
         */
        private String nickname;

        /**
         * 真实姓名
         */
        private String realname;

        /**
         * 头像
         */
        private String face;

        /**
         * 手机号 手机号
         */
        private String mobile;

        /**
         * 邮箱地址 邮箱地址
         */
        private String email;

        /**
         * 性别 性别 1:男  0:女  2:保密
         */
        private Integer sex;

        /**
         * 生日 生日
         */
        private Date birthday;
    }
}
