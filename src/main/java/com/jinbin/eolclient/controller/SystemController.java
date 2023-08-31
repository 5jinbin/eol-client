package com.jinbin.eolclient.controller;

import com.jinbin.eolclient.pojo.User;
import com.jinbin.eolclient.service.UserService;
import com.jinbin.eolclient.util.JwtHelper;
import com.jinbin.eolclient.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/sys")
@Api(tags = "系统功能接口")
public class SystemController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册接口
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        User existUser = userService.getUserByUsername(user.getUsername());

        if (existUser != null) {
            return Result.fail("该用户名已经被注册,请换一个再试试!");
        } else {
            if(userService.save(user)){

                return Result.ok("注册成功");
            }
            else
                return Result.fail("注册失败！");
        }
    }

    /**
     * 用户登录接口
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletResponse response) {
        // 检查用户名和密码是否正确
        User existUser = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (existUser == null ) {
            return Result.fail("用户名或密码错误！");
        }


        // 登录成功，生成token
        String token = JwtHelper.createToken(existUser.getId());
        // 将token放入响应头中
//        response.setHeader("Authorization", token);
        Map<String, Object> map = new LinkedHashMap();
        map.put("userInfo", existUser);
        map.put("token", token);

        return Result.ok(map);
    }


//    @GetMapping(value = "/audio", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    public ResponseEntity<byte[]> getAudio() throws IOException {
//        // 读取本地的MP3文件
//        File file = new File("C:\\Users\\11508\\Downloads\\2cet420221208_4816512Mdv.mp3");
//        byte[] audioBytes = Files.readAllBytes(file.toPath());
//
//        // 设置ResponseHeaders
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDisposition(ContentDisposition.builder("inline").filename(file.getName()).build());
//        headers.setContentLength(audioBytes.length);
//
//        // 返回音频流
//        return new ResponseEntity<>(audioBytes, headers, HttpStatus.OK);
//    }
}
