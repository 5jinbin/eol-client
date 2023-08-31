package com.jinbin.eolclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinbin.eolclient.pojo.WordStudyRecord;
import com.jinbin.eolclient.util.Result;
import com.jinbin.eolclient.service.WordStudyRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/word-study-record")
@Api(tags = "单词学习记录管理")
public class WordStudyRecordController {

    @Autowired
    private WordStudyRecordService wordStudyRecordService;

    @ApiOperation("查询单词学习进度")
    @GetMapping("/progress/{id}/{difficulty}")
    public Result progress(
            @PathVariable Integer id,
            @PathVariable Integer difficulty
    ) {
        return Result.ok(wordStudyRecordService.getProgress(id,difficulty));
    }

    @ApiOperation("分页查询单词学习记录")
    @GetMapping("/list")
    public Result<IPage<WordStudyRecord>> list(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return Result.ok(wordStudyRecordService.selectPage(userId, pageNo, pageSize));
    }

    @ApiOperation("新增单词学习记录")
    @PostMapping("/add")
    public Result<Boolean> create(@RequestBody WordStudyRecord wordStudyRecord) {
        wordStudyRecord.setStudyDate(LocalDate.now());
        boolean result = wordStudyRecordService.save(wordStudyRecord);
        return result ? Result.ok() : Result.fail();
    }




}
