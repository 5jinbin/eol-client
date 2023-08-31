package com.jinbin.eolclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinbin.eolclient.pojo.Article;

public interface ArticleService extends IService<Article> {

    IPage<Article> selectPage(int pageNum, int pageSize);

    Article selectById(int id);

    boolean insert(Article article);

    boolean update(Article article);

    boolean deleteById(int id);

}

