package com.azhen.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.awt.*;

public interface MarkService {
    String MARK_TEXT = "慕课网";
    String FONT_NAME = "微软雅黑";
    Integer FONT_STYLE = Font.BOLD;
    Integer FONT_SIZE = 120;
    Color FONT_COLOR = Color.BLACK;
    Integer X = 10;
    Integer Y = 10;
    Float ALPHA = 0.3F;

    String LOGO = "logo.png";   //图片水印名称
    String watermark(CommonsMultipartFile file,String uploadPath,String realUploadPath);
}
