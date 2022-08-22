package com.huangk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author zhanghao
 */
@Data
public class Video {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;
    private Date updatedAt;
    private String category;
    private String title;
    private String coverPath;
    private String videoPath;
    private String summary;
    private Integer playsNum;
    private Integer videoDuration;
    private Integer videoSize;
}
