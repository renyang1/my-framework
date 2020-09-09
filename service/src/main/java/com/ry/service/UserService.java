package com.ry.service;

import com.ry.entity.Users;
import com.ry.mapper.UsersMapper;
import com.ry.res.UserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2020-09-01
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Service
public class UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer create(UserRes.Create res) {
        Users users = new Users();
        users.setUsername(res.getUsername());
        users.setPassword(res.getPassword());
        users.setNickname(res.getNickname());
        users.setRealname(res.getRealname());
        users.setFace(res.getFace());
        users.setMobile(res.getMobile());
        users.setEmail(res.getEmail());
        users.setSex(res.getSex());
        users.setBirthday(res.getBirthday());
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());
        return usersMapper.insert(users);
    }

    public Users queryUser(String userId) {
        Users users = usersMapper.getUserById(userId);
        Users users1 = usersMapper.selectById(userId);
        System.out.println(users1);
        return users;
    }
}
