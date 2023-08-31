package com.jinbin.eolclient.controller;

import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/audio")
@Api(tags = "视频流接口")
public class AudioController {

    final String rootPath = "C:\\Users\\11508\\Downloads\\2cet420221208_4816512Mdv.mp3";

    @GetMapping(value = "", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> streamVideo()
    {
        // 拼接文件路径
        String filePath = rootPath;


        Resource resource = new PathResource(filePath);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + resource.getFilename())
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resource);
    }

}

