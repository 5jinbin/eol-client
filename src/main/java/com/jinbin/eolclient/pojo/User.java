// com.jinbin.eolclient.pojo.User
package com.jinbin.eolclient.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("eol_user")
@ApiModel(value="User对象", description="用户表")
public class User {
    @ApiModelProperty(value = "用户编号", example = "1")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名", example = "john123")
    private String username;

    @ApiModelProperty(value = "密码", example = "password123")
    private String password;

    @ApiModelProperty(value = "姓名", example = "John Doe")
    private String name;

    @ApiModelProperty(value = "性别", example = "male")
    private String gender;

    @ApiModelProperty(value = "年龄", example = "25")
    private Integer age;

    @ApiModelProperty(value = "出生日期", example = "2000-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;



    @ApiModelProperty(value = "手机号码", example = "12345678901")
    private String phone;

    @ApiModelProperty(value = "邮箱地址", example = "john.doe@example.com")
    private String email;

    @ApiModelProperty(value = "学历", example = "Bachelor")
    private String education;

    @ApiModelProperty(value = "自我介绍", example = "Hello, I am John.")
    private String introduction;

    @ApiModelProperty(value = "注册时间", example = "2022-01-01T12:00:00")
    private Date createTime;
}
