// com.jinbin.eolclient.controller.UserController
package com.jinbin.eolclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinbin.eolclient.util.Result;
import com.jinbin.eolclient.pojo.User;
import com.jinbin.eolclient.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理接口")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("新增用户")
    @PostMapping("/add")
    public Result addUser(@RequestBody User user) {
        boolean result = userService.save(user);
        return result ? Result.ok("新增用户成功") : Result.fail("新增用户失败");
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        boolean result = userService.removeById(id);
        return result ? Result.ok("删除用户成功") : Result.fail("删除用户失败");
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    public Result updateUser(@RequestBody User user) {
        boolean result = userService.updateById(user);
        return result ? Result.ok("修改用户成功") : Result.fail("修改用户失败");
    }

    @ApiOperation("根据id查询用户")
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return user != null ? Result.ok(user) : Result.fail("查询用户失败");
    }

    @ApiOperation("分页查询用户")
    @GetMapping("/list")
    public Result getUserListByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        Page<User> userPage = userService.page(page);
        return Result.ok(userPage);
    }

}