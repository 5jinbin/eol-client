package com.jinbin.eolclient.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinbin.eolclient.pojo.Listening;
import com.jinbin.eolclient.util.Result;
import com.jinbin.eolclient.service.ListeningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listening")
@Api(tags = "听力训练相关接口")
public class ListeningController {

    @Autowired
    private ListeningService listeningService;

    @PostMapping()
    @ApiOperation("新增听力训练")
    public Result addListening(@RequestBody Listening listening) {
        boolean result = listeningService.addListening(listening);
        if (result) {
            return Result.ok("新增听力训练成功！");
        } else {
            return Result.fail("新增听力训练失败！");
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除听力训练")
    public Result deleteListening(@PathVariable("id") Integer id) {
        boolean result = listeningService.deleteListening(id);
        if (result) {
            return Result.ok("删除听力训练成功！");
        } else {
            return Result.fail("删除听力训练失败！");
        }
    }

    @PutMapping()
    @ApiOperation("修改听力训练")
    public Result updateListening(@RequestBody Listening listening) {
        boolean result = listeningService.updateListening(listening);
        if (result) {
            return Result.ok("修改听力训练成功！");
        } else {
            return Result.fail("修改听力训练失败！");
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询听力训练")
    public Result getListeningById(@PathVariable("id") Integer  id) {
        Listening listening = listeningService.getListeningById(id);
        if (listening != null) {
            return Result.ok(listening);
        } else {
            return Result.fail("查询失败，该听力训练不存在！");
        }
    }

    @ApiOperation("根据条件查询的听力训练")
    @PostMapping("/condition")
    public Result getListensByCondition(@RequestBody Listening listening) {
        LambdaQueryWrapper<Listening> lqw = new LambdaQueryWrapper<>();
        if(listening.getDifficulty() != null) lqw.eq(Listening::getDifficulty,listening.getDifficulty());
        if(listening.getTitle() != null) lqw.like(Listening::getTitle,listening.getTitle());

        List<Listening> list = listeningService.list(lqw);
        if(list != null){
            return Result.ok(list);
        } else{
            return Result.fail("查询失败，该听力训练不存在！");
        }
    }


    @GetMapping()
    @ApiOperation("分页查询听力训练")
    public Result listListeningByPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        IPage<Listening> listeningPage = listeningService.findListeningList(pageNum, pageSize);
        if (listeningPage != null) {
            return Result.ok(listeningPage);
        } else {
            return Result.fail("查询失败，无听力训练信息！");
        }
    }

}

