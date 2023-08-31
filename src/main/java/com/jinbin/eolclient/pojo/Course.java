package com.jinbin.eolclient.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("eol_course")
@ApiModel(value="Course对象", description="课程表")
public class Course {

    @ApiModelProperty("课程编号")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("封面图片路径")
    private String coverImage;

    @ApiModelProperty("课程视频路径")
    private String videoUrl;

    @ApiModelProperty("收藏次数")
    private Integer favoriteCount;

    @ApiModelProperty("学习次数")
    private Integer studyCount;

}

