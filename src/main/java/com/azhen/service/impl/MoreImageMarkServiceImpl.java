package com.azhen.service.impl;

import com.azhen.service.MarkService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service("moreImageMarkService")
@Scope("prototype")
public class MoreImageMarkServiceImpl implements MarkService {
    @Override
    public String watermark(CommonsMultipartFile file, String uploadPath, String realUploadPath) {
        String logoFileName = "logo_" + file.getOriginalFilename();
        OutputStream out = null;
        String logoPath = realUploadPath + File.separator + LOGO;
        try {
            //获取原始图片的宽高
            Image image = ImageIO.read(file.getInputStream());
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image,0,0,null);

            File logo = new File(logoPath);
            Image imageLogo = ImageIO.read(logo);
            int logoWidth = imageLogo.getWidth(null);
            int logoHeight = imageLogo.getHeight(null);

            //设置透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
            g.rotate(Math.toRadians(30),bufferedImage.getWidth()/2,bufferedImage.getHeight()/2);

            int x = -width / 2;
            int y;
            while (x < width * 1.5) {
                y = -height / 2;
                while (y < height * 1.5) {
                    g.drawImage(imageLogo,x,y,null);
                    y += logoWidth + 300;
                }
                x += logoHeight + 300;
            }
            g.dispose();

            //写入文件
            out = new FileOutputStream(realUploadPath + File.separator + logoFileName);
            String imageType = file.getContentType().substring(file.getContentType().indexOf("/") + 1);
            ImageIO.write(bufferedImage,imageType,out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return uploadPath + File.separator + logoFileName;
    }

    public int getTextLength(String text) {
        int length = text.length();
        for(int i = 0; i < length; i ++) {
            String s = String.valueOf(text.charAt(i));
            if(s.getBytes().length > 1) {
                length ++;
            }
        }
        length = length % 2 == 0 ? length / 2 : length / 2 + 1;
        return length;
    }
}
