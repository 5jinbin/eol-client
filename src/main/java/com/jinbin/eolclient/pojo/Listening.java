package com.jinbin.eolclient.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("eol_listening")
@ApiModel(value="Listening对象", description="听力训练表")
public class Listening  {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "听力编号")
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "音频路径")
    private String audioPath;

    @ApiModelProperty(value = "听力内容")
    private String content;

    @ApiModelProperty(value = "难度等级")
    private Integer difficulty;

    @ApiModelProperty(value = "收藏次数")
    private Integer favoriteCount;

    @ApiModelProperty(value = "学习次数")
    private Integer studyCount;

}

