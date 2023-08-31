package com.jinbin.eolclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinbin.eolclient.mapper.WordStudyRecordMapper;
import com.jinbin.eolclient.pojo.WordStudyRecord;
import com.jinbin.eolclient.service.WordStudyRecordService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class WordStudyRecordServiceImpl extends ServiceImpl<WordStudyRecordMapper, WordStudyRecord> implements WordStudyRecordService {

    @Override
    public IPage<WordStudyRecord> selectPage(Integer pageNum, Integer pageSize, Integer userId) {
        Page<WordStudyRecord> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, new QueryWrapper<WordStudyRecord>().eq("user_id", userId));
    }

    @Override
    public Map<String, Integer> getProgress(Integer userId, Integer difficulty) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("compCount",baseMapper.selectCompCount(userId,difficulty));
        map.put("total",baseMapper.selectTotal(difficulty));
        return map;
    }

}
