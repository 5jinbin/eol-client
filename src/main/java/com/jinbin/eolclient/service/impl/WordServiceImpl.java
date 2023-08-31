package com.jinbin.eolclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinbin.eolclient.mapper.UserWordConfigMapper;
import com.jinbin.eolclient.mapper.WordMapper;
import com.jinbin.eolclient.mapper.WordStudyRecordMapper;
import com.jinbin.eolclient.pojo.UserWordConfig;
import com.jinbin.eolclient.pojo.Word;
import com.jinbin.eolclient.pojo.WordStudyRecord;
import com.jinbin.eolclient.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService {

    @Autowired
    private WordStudyRecordMapper wordStudyRecordMapper;

    @Autowired
    private UserWordConfigMapper userWordConfigMapper;

    public Page<Word> getPage(Integer pageNum, Integer pageSize) {
        Page<Word> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page);
    }

    /**
     * 获取User的今天剩余的任务量
     *
     * @param userId
     * @return 默认单词书今天剩余的任务量
     */
    @Override
    public Integer getTaskCount(Integer userId){
        // 获取用户信息
        UserWordConfig userWordConfig = userWordConfigMapper.selectById(userId);
        // 如果获取用户配置信息失败，则用默认配置:CET-4 每天背10个单词
        if(userWordConfig == null)userWordConfig = new UserWordConfig(userId,0,10);


        Integer dailyTaskCount = userWordConfig.getDailyTaskCount();

        // 获取当天已完成的背单词记录
        List<WordStudyRecord> wordStudyRecords = wordStudyRecordMapper.selectList(
                (new LambdaQueryWrapper<WordStudyRecord>())
                        .eq(WordStudyRecord::getUserId, userId)
                        .eq(WordStudyRecord::getStudyDate, LocalDate.now())
        );
        // 获取默认单词书种类里的记录WordId字段集
        List<Integer> wordStudyRecordWordIds = new ArrayList<>();
        for (WordStudyRecord wordStudyRecord : wordStudyRecords) {
            wordStudyRecordWordIds.add(wordStudyRecord.getWordId());
        }
        Integer doneCount = 0;

        // 记录表为空时不处理
        if(wordStudyRecordWordIds.size() > 0){
            // 在单词表中获取对应的单词信息
            List<Word> wordList = baseMapper.selectList(
                    (new LambdaQueryWrapper<Word>())
                            .in(Word::getId, wordStudyRecordWordIds)
            );

            for (Word word : wordList) {
                if(word.getDifficulty().equals(userWordConfig.getDefaultDifficulty()))
                    doneCount++;
            }
        }

        return Math.max(dailyTaskCount - doneCount, 0);
    }

    @Override
    public List<Word> getTask(Integer userId) {
//        // 获取用户每日学习配置信息
//        UserWordConfig userWordConfig = userWordConfigMapper.selectById(userId);
//        // 如果获取失败则使用默认信息，并把默认信息写入数据库
//        if(userWordConfig == null){
//            userWordConfig = new UserWordConfig(userId,1,30);
//        }
//
//
//        Integer count = getTaskCount(userId);
//
//        List<WordStudyRecord> wordStudyRecords = wordStudyRecordMapper.selectList(
//                new LambdaQueryWrapper<WordStudyRecord>()
//                        .eq(WordStudyRecord::getUserId, userId)
//        );
//
//        List<Integer> wordIds = new ArrayList<>();
//        for (WordStudyRecord wordStudyRecord : wordStudyRecords) {
//            wordIds.add(wordStudyRecord.getWordId());
//        }
//
//
//        Page<Word> wordPage = baseMapper.selectPage(
//                new Page<>(1, count),
//                new LambdaQueryWrapper<Word>()
//                        .eq(Word::getDifficulty, userWordConfig.getDefaultDifficulty())
//                        .notIn(Word::getId,wordIds)
//        );
//
//        return wordPage.getRecords();


        // 获取用户每日学习配置信息
        UserWordConfig userWordConfig = userWordConfigMapper.selectById(userId);
        // 如果获取失败则使用默认信息，并把默认信息(英语四级，每日30条)写入数据库
        if(userWordConfig == null){
            userWordConfig = new UserWordConfig(userId,1,30);
            userWordConfigMapper.insert(userWordConfig);
        }

        // 剩余单词量 = 每日单词量 - 已背单词数量统计
        Integer count = userWordConfig.getDailyTaskCount() - wordStudyRecordMapper.selectCompDailyCount(userId);

        // 每日任务已完成
        if(count <= 0){
            return null;
        }

        return baseMapper.selectTask(userId,count);
    }

    @Override
    public List<Word> getWordsByCondition(Integer userId, Integer difficultyLevel, Integer filterCondition) {
        return baseMapper.selectWordsByCondition(userId, difficultyLevel, filterCondition);
    }
}
