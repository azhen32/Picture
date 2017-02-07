package com.azhen.controller;

import com.azhen.service.MarkService;
import com.azhen.service.PictureService;
import com.azhen.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class PictureController {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private PictureService pictureService;

    //@Autowired("textMarkService")
    private MarkService textMarkService;

    //@Autowired("imageMarkService")
    private MarkService imageMarkService;

    //@Autowired("moreTextMarkService")
    private MarkService moreTextMarkService;

    //@Autowired("moreImageMarkService")
    private MarkService moreImageMarkService;

    @RequestMapping(value = "/thumbnail",method = RequestMethod.POST)
    public ModelAndView thumbnail(@RequestParam("image") CommonsMultipartFile file, HttpSession session) throws Exception {
        String uploadPath = "/images";
        String realUploadPath = session.getServletContext().getRealPath(uploadPath);

        String imageUrl = uploadService.uploadImage(file,uploadPath,realUploadPath);
        String thumImageUrl = pictureService.thunbnailByTool(file,uploadPath,realUploadPath);
        ModelAndView mv = new ModelAndView();
        mv.addObject("imageURL",imageUrl);
        mv.addObject("thumbImageURL",thumImageUrl);
        mv.setViewName("thumbnail");
        return mv;
    }
}
