package com.jinbin.eolclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinbin.eolclient.mapper.AnnouncementMapper;
import com.jinbin.eolclient.pojo.Announcement;
import com.jinbin.eolclient.service.AnnouncementService;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {
}
