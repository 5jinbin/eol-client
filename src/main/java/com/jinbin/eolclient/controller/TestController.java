package com.jinbin.eolclient.controller;


import io.swagger.annotations.Api;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/test")
@Api(tags = "管理员测试开发相关接口")
public class TestController {

    String url = "C:\\Users\\11508\\Desktop\\test1.mp4";



    @GetMapping(value = "/video")
    public ResponseEntity<Resource> playVideo() throws IOException {
        Resource resource = new PathResource(url);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + resource.getFilename())
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resource);
    }

}
