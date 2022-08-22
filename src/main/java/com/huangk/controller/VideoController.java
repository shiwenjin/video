package com.huangk.controller;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huangk.base.R;
import com.huangk.entity.Video;
import com.huangk.service.IVideoService;
import lombok.var;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhanghao
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @Resource
    private IVideoService videoService;

    @GetMapping("/page")
    public R<Page<Video>> page(HttpServletRequest req) {
        var pageParams = ServletUtil.fillBean(req, new Page<Video>(), false);
        var params = ServletUtil.fillBean(req, new Video(), false);

        Page<Video> result = videoService.page(pageParams, videoService.lambdaQuery().eq(CharSequenceUtil.isNotBlank(params.getCategory()), Video::getCategory, params.getCategory()).getWrapper());
        return R.ok(result);
    }

    @PostMapping()
    public R<Void> saveOrUpdate(@RequestBody Video video) {
        videoService.saveOrUpdate(video);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R<Void> del(@PathVariable("id") Integer id) {
        videoService.removeById(id);
        return R.ok();
    }
}
