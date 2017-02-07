package com.azhen.service;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

@Service
public class UploadService {

    public String uploadImage(CommonsMultipartFile file,String uploadPath, String readUploadPath) {
       try(InputStream is = file.getInputStream();
       OutputStream out = new FileOutputStream(new File(readUploadPath + File.separator + file.getOriginalFilename()));) {
           IOUtils.copy(is,out);
       }catch (IOException e) {

       }
        return uploadPath + File.separator + file.getOriginalFilename();
    }
}
