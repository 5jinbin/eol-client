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
@TableName("eol_word")
@ApiModel(value="Word对象", description="单词表")
public class Word {

    @ApiModelProperty("单词编号")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("单词")
    private String word;

    @ApiModelProperty("英式发音")
    private String ukPron;

    @ApiModelProperty("英式发音音频路径")
    private String ukPronPath;

    @ApiModelProperty("美式发音")
    private String usPron;

    @ApiModelProperty("美式发音音频路径")
    private String usPronPath;

    @ApiModelProperty("释义")
    private String definition;

    @ApiModelProperty("例句")
    private String exampleSentence;

    @ApiModelProperty("例句翻译")
    private String exampleTranslation;

    @ApiModelProperty("难度等级")
    private Integer difficulty;

    @ApiModelProperty("收藏次数")
    private Integer favoriteCount;

    @ApiModelProperty("学习次数")
    private Integer studyCount;

}
