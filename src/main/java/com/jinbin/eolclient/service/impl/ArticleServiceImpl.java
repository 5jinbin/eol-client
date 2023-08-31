package com.jinbin.eolclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinbin.eolclient.mapper.ArticleMapper;
import com.jinbin.eolclient.pojo.Article;
import com.jinbin.eolclient.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public IPage<Article> selectPage(int pageNum, int pageSize) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPageVo(page, null);
    }

    @Override
    public Article selectById(int id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean insert(Article article) {
        return baseMapper.insert(article) > 0;
    }

    @Override
    public boolean update(Article article) {
        return baseMapper.updateById(article) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        return baseMapper.deleteById(id) > 0;
    }

}
