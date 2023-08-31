package com.jinbin.eolclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinbin.eolclient.pojo.Course;

public interface CourseService extends IService<Course> {

    IPage<Course> selectPage(Page<Course> page);

}
