// @/api/course.js

import request from '@/utils/request'


// 课程管理接口

// 根据id获取课程信息
export function getById(id) {
    return request({
        url: `/course/${id}`,
        method: 'get'
    })
}
// 获取课程列表
export function list(page, pageSize) {
    return request({
        url: '/course/list',
        method: 'get',
        params: {
            page,
            pageSize
        }
    })
}
/* 
    @GetMapping("/title/{tilte}")
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
*/

// 根据条件获取课程信息
export function getByTitle(title) {
    return request({
        url: `/course/title${title === null ? '' : '/'+ title}`,
        method: 'get'
    })
}