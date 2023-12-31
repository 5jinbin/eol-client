package com.jinbin.eolclient.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("eol_user_word_config")
public class UserWordConfig {
    @TableId
    private Integer userId;
    private Integer defaultDifficulty;
    private Integer dailyTaskCount;
}
