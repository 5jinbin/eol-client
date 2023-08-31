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
@TableName("eol_article")
@ApiModel(value="Article对象", description="文章表")
public class Article {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "文章编号")
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "简述")
    private String summary;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "收藏次数")
    private Integer favoriteCount;

    @ApiModelProperty(value = "学习次数")
    private Integer studyCount;

}
