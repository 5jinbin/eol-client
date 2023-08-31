package com.jinbin.eolclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinbin.eolclient.pojo.Word;

import java.util.List;

public interface WordService extends IService<Word> {
    public Integer getTaskCount(Integer userId);
    List<Word> getTask(Integer userId);

    List<Word> getWordsByCondition(Integer userId, Integer difficultyLevel, Integer filterCondition);
}
