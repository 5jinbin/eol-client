/* 
package com.jinbin.eolclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinbin.eolclient.pojo.UserWordConfig;
import com.jinbin.eolclient.service.UserWordConfigService;
import com.jinbin.eolclient.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_word_config")
@Api(tags = "用户单词配置管理接口")
public class UserWordConfigController {
    @Autowired
    private UserWordConfigService userWordConfigService;

    @ApiOperation("新增用户单词配置")
    @PostMapping("/add")
    public Result addUserWordConfig(@RequestBody UserWordConfig userWordConfig) {
        boolean result = userWordConfigService.save(userWordConfig);
        return result ? Result.ok("新增用户单词配置成功") : Result.fail("新增用户单词配置失败");
    }

    @ApiOperation("删除用户单词配置")
    @DeleteMapping("/delete/{userId}")
    public Result deleteUserWordConfig(@PathVariable Integer userId) {
        boolean result = userWordConfigService.removeById(userId);
        return result ? Result.ok("删除用户单词配置成功") : Result.fail("删除用户单词配置失败");
    }

    @ApiOperation("修改用户单词配置")
    @PutMapping("/update")
    public Result updateUserWordConfig(@RequestBody UserWordConfig userWordConfig) {
        boolean result = userWordConfigService.updateById(userWordConfig);
        return result ? Result.ok("修改用户单词配置成功") : Result.fail("修改用户单词配置失败");
    }

    @ApiOperation("根据用户ID查询用户单词配置")
    @GetMapping("/{userId}")
    public Result getUserWordConfigByUserId(@PathVariable Integer userId) {
        UserWordConfig userWordConfig = userWordConfigService.getById(userId);
        return userWordConfig != null ? Result.ok(userWordConfig) : Result.fail("查询用户单词配置失败");
    }

    @ApiOperation("分页查询用户单词配置")
    @GetMapping("/list")
    public Result getUserWordConfigListByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        Page<UserWordConfig> page = new Page<>(pageNum, pageSize);
        Page<UserWordConfig> userWordConfigPage = userWordConfigService.page(page);
        return Result.ok(userWordConfigPage);
    }

}


*/

// Path: src\api\word.js
import request from '@/utils/request'
// 获取用户单词配置
export function getUserWordConfig(userId) {
    return request({
        url: '/user_word_config/' + userId,
        method: 'get'
    })
}

// 修改用户单词配置
export function updateUserWordConfig(userWordConfig) {
    return request({
        url: '/user_word_config/update',
        method: 'put',
        data: userWordConfig
    })
}