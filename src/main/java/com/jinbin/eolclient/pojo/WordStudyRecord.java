package com.jinbin.eolclient.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("eol_word_study_record")
@ApiModel(value = "WordStudyRecord对象", description = "单词学习记录表")
public class WordStudyRecord {

    @ApiModelProperty(value = "用户ID", required = true)
    private Integer userId;

    @ApiModelProperty(value = "单词ID", required = true)
    private Integer wordId;

    @ApiModelProperty(value = "记录日期", required = true)
    @TableField(fill = FieldFill.INSERT)
    private LocalDate studyDate;
}
