package com.huangk.controller;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.PathUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.huangk.base.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static cn.hutool.core.img.ImgUtil.*;

/**
 * @author zhanghao
 */
@RestController
@Slf4j
public class UploadController {
    @Value("${file.upload.path}")
    private String path;

    @PostMapping("/upload")
    public R<String> create(@RequestPart(name = "file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String suffix = FileUtil.getSuffix(fileName);

        Path dir;
        if (ListUtil.of("mp4").contains(suffix)) {
            dir = Paths.get(path, "video");
        } else if (ListUtil.of(IMAGE_TYPE_JPG, IMAGE_TYPE_PNG, IMAGE_TYPE_JPEG).contains(suffix)) {
            dir = Paths.get(path, "image");
        } else {
            dir = Paths.get(path);
        }

        dir = Paths.get(dir.toString(), DateUtil.date().toDateStr());
        if (!FileUtil.exist(dir.toString())) {
            PathUtil.mkdir(dir);
        }

        Path filePath = Paths.get(dir.toString(), fileName);

        IoUtil.copy(file.getInputStream(), Files.newOutputStream(filePath));

        return R.ok(CharSequenceUtil.removePrefix(filePath.toString(), path));
    }
}
