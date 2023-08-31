// @/api/word.js

// 获取每天的单词任务
import request from '@/utils/request'

export function getTask(id) {
    return request({
        url: '/word/task/' + id,
        method: 'get'
    })
}

// 通过拼写查询单词
export function getWordByName(wordName) {
    return request({
        url: '/word/byName/' + wordName,
        method: 'get'
    })
}

// 通过id查询单词
export function getWordById(id) {
    return request({
        url: '/word/' + id,
        method: 'get'
    })
}

// 查询单词学习进度(word-study-record接口的方法)
export function getProgress(id, difficulty) {
    return request({
        url: '/word-study-record/progress/' + id + '/' + difficulty,
        method: 'get'
    })
}


// 新增单词学习记录
export function addWordStudyRecord(wordStudyRecord) {
    return request({
        url: '/word-study-record/add',
        method: 'post',
        data: wordStudyRecord
    })
}

// 获取条件查询的单词表
export function getWordsByCondition(id, difficultyLevel, filterCondition) {
    return request({
        url: '/word/condition/' + id + '/' + difficultyLevel + '/' + filterCondition,
        // url: '/word/condition/1/-1/1',
        method: 'get'
    })
}

