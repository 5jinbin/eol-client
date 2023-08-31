package com.jinbin.eolclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinbin.eolclient.pojo.Listening;

public interface ListeningService extends IService<Listening> {

    IPage<Listening> findListeningList(Integer pageNum, Integer pageSize);

    Listening getListeningById(Integer id);

    boolean addListening(Listening listening);

    boolean updateListening(Listening listening);

    boolean deleteListening(Integer id);
}
