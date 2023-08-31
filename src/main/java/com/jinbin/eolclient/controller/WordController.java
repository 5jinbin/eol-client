package com.jinbin.eolclient.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinbin.eolclient.mapper.WordMapper;
import com.jinbin.eolclient.util.Result;
import com.jinbin.eolclient.pojo.Word;
import com.jinbin.eolclient.service.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/word")
@Api(tags = "单词管理接口")
public class WordController {

    @Autowired
    private WordService wordService;

    @ApiOperation("获取条件查询的单词表")
    @GetMapping("/condition/{id}/{difficultyLevel}/{filterCondition}")
    public Result getWordsByCondition(@PathVariable Integer id, @PathVariable Integer difficultyLevel, @PathVariable Integer filterCondition) {
        return Result.ok(wordService.getWordsByCondition(id, difficultyLevel, filterCondition));
    }

    @ApiOperation("获取某分类单词任务")
    @GetMapping("/task/{id}")
    public Result getTask(@PathVariable Integer id) {
        return Result.ok(wordService.getTask(id));
    }


    @ApiOperation("新增单词")
    @PostMapping("/add")
    public Result addWord(@RequestBody Word word) {
        boolean result = wordService.save(word);
        if (result) {
            return Result.ok("新增单词成功！");
        } else {
            return Result.fail("新增单词失败！");
        }
    }

    @ApiOperation("根据ID删除单词")
    @DeleteMapping("/{id}")
    public Result deleteWord(@PathVariable Long id) {
        boolean result = wordService.removeById(id);
        if (result) {
            return Result.ok("删除单词成功！");
        } else {
            return Result.fail("删除单词失败！");
        }
    }

    @ApiOperation("更新单词")
    @PutMapping("/update")
    public Result updateWord(@RequestBody Word word) {
        boolean result = wordService.updateById(word);
        if (result) {
            return Result.ok("更新单词成功！");
        } else {
            return Result.fail("更新单词失败！");
        }
    }

    @ApiOperation("根据ID查询单词")
    @GetMapping("/{id}")
    public Result getWordById(@PathVariable Long id) {
        Word word = wordService.getById(id);
        if (word != null) {
            return Result.ok(word);
        } else {
            return Result.fail("查询单词失败！");
        }
    }

    @ApiOperation("根据单词拼写查询单词详细信息")
    @GetMapping("/byName/{wordName}")
    public Result getWordByName(@PathVariable String wordName) {
        LambdaQueryWrapper<Word> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Word::getWord,wordName);
        Word word = wordService.getOne(lqw);
        if (word != null) {
            return Result.ok(word);
        } else {
            return Result.fail("查询单词失败！");
        }
    }



    @ApiOperation("分页查询单词")
    @GetMapping("/list")
    public Result getWordByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Word> page = new Page<>(pageNum, pageSize);
        wordService.page(page, null);
        return Result.ok(page);
    }

}
