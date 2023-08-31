package com.jinbin.eolclient.controller;

import io.swagger.annotations.Api;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/video")
@Api(tags = "视频流接口")
public class VideoController {

//    final String rootPath = "D:\\ruoyi\\uploadPath\\upload\\";
//
//    @GetMapping("/profile/upload/{year}/{mon}/{day}/{fileName}")
//    public ResponseEntity<Resource> streamVideo(
//            @PathVariable("year") String year,
//            @PathVariable("mon") String mon,
//            @PathVariable("day") String day,
//            @PathVariable("fileName") String fileName
//            )
//    {
//        // 拼接文件路径
//        String filePath = rootPath + year
//                + "\\" + mon
//                + "\\" + day
//                + "\\" + fileName;
//
//
//        Resource resource = new PathResource(filePath);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + resource.getFilename())
//                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
//                .body(resource);
//    }

}

