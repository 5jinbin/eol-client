package com.jinbin.eolclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinbin.eolclient.mapper.ListeningMapper;
import com.jinbin.eolclient.pojo.Listening;
import com.jinbin.eolclient.service.ListeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListeningServiceImpl extends ServiceImpl<ListeningMapper, Listening> implements ListeningService {

    @Autowired
    private ListeningMapper listeningMapper;

    @Override
    public IPage<Listening> findListeningList(Integer pageNum, Integer pageSize) {
        return listeningMapper.selectPage(new Page<>(pageNum, pageSize), new QueryWrapper<>());
    }

    @Override
    public Listening getListeningById(Integer id) {
        return listeningMapper.selectById(id);
    }

    @Override
    public boolean addListening(Listening listening) {
        return listeningMapper.insert(listening) > 0;
    }

    @Override
    public boolean updateListening(Listening listening) {
        return listeningMapper.updateById(listening) > 0;
    }

    @Override
    public boolean deleteListening(Integer id) {
        return listeningMapper.deleteById(id) > 0;
    }
}
