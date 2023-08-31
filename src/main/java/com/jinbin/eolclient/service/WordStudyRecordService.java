package com.jinbin.eolclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinbin.eolclient.pojo.WordStudyRecord;

import java.util.Map;

public interface WordStudyRecordService extends IService<WordStudyRecord> {
    IPage<WordStudyRecord> selectPage(Integer pageNum, Integer pageSize, Integer userId);

    Map<String,Integer> getProgress(Integer userId, Integer difficulty);

}
