package com.ry.service;

import com.ry.entity.Article;
import com.ry.entity.Users;
import com.ry.mapper.ArticleMapper;
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
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Integer create() {
        Article article = new Article();
        article.setTitle("文章1");
        article.setSubtitle("标题1");
        article.setUrl("www.baidu.com");
        article.setClickCount(1L);
        article.setIsDelete(1);
        int i = 1/0;
        int result = articleMapper.insert(article);
        return result;
    }


}
