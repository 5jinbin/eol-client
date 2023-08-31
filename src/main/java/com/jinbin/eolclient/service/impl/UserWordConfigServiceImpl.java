package com.jinbin.eolclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinbin.eolclient.mapper.UserWordConfigMapper;
import com.jinbin.eolclient.pojo.UserWordConfig;
import com.jinbin.eolclient.service.UserWordConfigService;
import org.springframework.stereotype.Service;

@Service
public class UserWordConfigServiceImpl extends ServiceImpl<UserWordConfigMapper, UserWordConfig> implements UserWordConfigService {
}
