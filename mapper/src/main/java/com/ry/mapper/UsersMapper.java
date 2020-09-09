package com.ry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.entity.Users;

/**
 * @author renyang
 */
public interface UsersMapper extends BaseMapper<Users> {

    Users getUserById(String id);
}