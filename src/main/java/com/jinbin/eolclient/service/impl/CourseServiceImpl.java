package com.jinbin.eolclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinbin.eolclient.mapper.CourseMapper;
import com.jinbin.eolclient.pojo.Course;
import com.jinbin.eolclient.service.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public IPage<Course> selectPage(Page<Course> page) {
        return baseMapper.selectPage(page, null);
    }

}
