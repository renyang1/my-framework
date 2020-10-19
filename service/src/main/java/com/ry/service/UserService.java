package com.ry.service;

import com.alibaba.fastjson.JSONObject;
import com.ry.entity.Users;
import com.ry.mapper.UsersMapper;
import com.ry.res.UserRes;
import com.ry.cache.MyRedisTemplate;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
@Slf4j
public class UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private ArticleService articleService;

    @Resource
    private MyRedisTemplate myRedisTemplate;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer create(UserRes.Create res) {
        Users users = new Users();
        users.setUserName(res.getUsername());
        users.setPassword(res.getPassword());
        users.setNickName(res.getNickname());
        users.setRealName(res.getRealname());
        users.setFace(res.getFace());
        users.setMobile(res.getMobile());
        users.setEmail(res.getEmail());
        users.setSex(res.getSex());
        users.setBirthday(res.getBirthday());
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());
        int result = usersMapper.insert(users);

        try {
            articleService.create();
        }catch (Exception e) {
            log.error("出错了!!!");
        }
        return result;
    }

    public Users queryUser(String userId) {
        Users users = usersMapper.selectById(userId);
        myRedisTemplate.set("userId_" + userId, JSONObject.toJSONString(users));
        return users;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteUserById(Integer userId) {
        usersMapper.deleteById(userId);
    }
}
