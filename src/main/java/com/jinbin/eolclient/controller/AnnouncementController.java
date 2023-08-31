package com.jinbin.eolclient.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinbin.eolclient.pojo.Announcement;
import com.jinbin.eolclient.service.AnnouncementService;
import com.jinbin.eolclient.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcement")
@Api(tags = "公告管理")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @ApiOperation("添加公告")
    @PostMapping("/add")
    public Result<Boolean> create(@RequestBody Announcement announcement) {
        boolean result = announcementService.save(announcement);
        return result ? Result.ok() : Result.fail();
    }

    @ApiOperation("删除公告")
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestParam(value = "id") Integer id) {
        boolean result = announcementService.removeById(id);
        return result ? Result.ok() : Result.fail();
    }

    @ApiOperation("修改公告")
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Announcement announcement) {
        boolean result = announcementService.updateById(announcement);
        return result ? Result.ok() : Result.fail();
    }

    @ApiOperation("分页查询公告")
    @GetMapping("/list")
    public Result<IPage<Announcement>> list(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<Announcement> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Announcement> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        IPage<Announcement> result = announcementService.page(page, wrapper);
        return Result.ok(result);
    }

    @ApiOperation("获取最新公告")
    @GetMapping("/latest")
    public Result getLatestAnnouncement() {
        QueryWrapper<Announcement> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.last("limit 5");
        List<Announcement> list = announcementService.list(wrapper);
        return list != null ? Result.ok(list) : Result.fail();
    }

}
