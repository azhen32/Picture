package com.azhen.service;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class PictureService {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    public static final String PREFIX = "/thum_";

    public String thunbnailByTool(CommonsMultipartFile file,String uploadPath,String realUploadPath) {
        try {
            String des = realUploadPath + PREFIX + file.getOriginalFilename();
            Thumbnails.of(file.getInputStream()).size(WIDTH,HEIGHT).toFile(des);    //等比缩略
            Thumbnails.of(file.getInputStream()).keepAspectRatio(false).size(WIDTH,HEIGHT).toFile(des); //强制缩略
        }catch (Exception e) {
            e.printStackTrace();
        }

        return uploadPath + PREFIX + file.getOriginalFilename();
    }

    public String thunbnailByJDK(CommonsMultipartFile file,String uploadPath,String realUploadPath) {
        OutputStream os = null;
        try {
            String des = realUploadPath + PREFIX + file.getOriginalFilename();
            os = new FileOutputStream(des);

            Image image = ImageIO.read(file.getInputStream());
            int width = image.getWidth(null);   //原图宽度
            int height = image.getHeight(null); //原图高度

            int rate1 = width /  WIDTH; //宽度缩略比例
            int rate2 = height / HEIGHT;    //高度缩略比例

            int rate = rate1 > rate2 ? rate1 : rate2;

            //计算缩略图的最终高度和宽度
            int newWidth = width / rate;
            int newHeight = height / rate;

            BufferedImage bufferedImage = new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_BGR);
            bufferedImage.getGraphics().drawImage(
                    image.getScaledInstance(newWidth,newHeight,
                            image.SCALE_SMOOTH),0,0,null);

            //"image/jpeg"
            String imageType = file.getContentType().substring(file.getContentType().indexOf("/") + 1);
            ImageIO.write(bufferedImage,imageType,os);
        }catch (Exception e) {
            e.printStackTrace();
            if(os != null) {
                try {
                    os.close();
                }catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return uploadPath + PREFIX + file.getOriginalFilename();
    }
}
