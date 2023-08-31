package com.jinbin.eolclient.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinbin.eolclient.pojo.Course;
import com.jinbin.eolclient.service.CourseService;
import com.jinbin.eolclient.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@Api(tags = "课程管理")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    @ApiOperation("根据ID获取课程信息")
    public Result getById(@PathVariable Integer id) {
        Course course = courseService.getById(id);
        if (course == null) {
            return Result.fail("课程不存在");
        }
        return Result.ok(course);
    }

    @GetMapping("/title/{title}")
    @ApiOperation("根据条件获取课程信息")
    public Result getByTitle(@PathVariable(name = "title", required = false) String title) {
        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper<>();
        if (title != null) lqw.like(Course::getTitle, title);
        List<Course> list = courseService.list(lqw);
        if (list == null) {
            return Result.fail("课程不存在");
        }
        return Result.ok(list);
    }

    @GetMapping("/title")
    @ApiOperation("根据条件获取课程信息")
    public Result getByTitle10086() {
        List<Course> list = courseService.list();
        if (list == null) {
            return Result.fail("课程不存在");
        }
        return Result.ok(list);
    }

    @PostMapping
    @ApiOperation("添加课程")
    public Result add(@RequestBody Course course) {
        boolean result = courseService.save(course);
        if (!result) {
            return Result.fail("添加失败");
        }
        return Result.ok();
    }

    @PutMapping
    @ApiOperation("更新课程")
    public Result update(@RequestBody Course course) {
        boolean result = courseService.updateById(course);
        if (!result) {
            return Result.fail("更新失败");
        }
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除课程")
    public Result delete(@PathVariable Integer id) {
        boolean result = courseService.removeById(id);
        if (!result) {
            return Result.fail("删除失败");
        }
        return Result.ok();
    }

    @GetMapping("/list")
    @ApiOperation("获取课程列表")
    public Result<IPage<Course>> list(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Course> pageParam = new Page<>(page, pageSize);
        IPage<Course> courseList = courseService.page(pageParam);
        return Result.ok(courseList);
    }

}
