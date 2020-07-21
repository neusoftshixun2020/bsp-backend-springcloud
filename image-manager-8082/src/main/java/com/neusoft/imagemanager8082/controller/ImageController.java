package com.neusoft.imagemanager8082.controller;

import com.neusoft.imagemanager8082.common.base.BaseController;
import com.neusoft.imagemanager8082.common.base.BaseModelJson;
import com.neusoft.imagemanager8082.common.exception.BusinessException;
import com.neusoft.imagemanager8082.file.service.FilesStorageService;
import com.neusoft.imagemanager8082.image.entity.Image;
import com.neusoft.imagemanager8082.image.entity.ImageVerificationCode;
import com.neusoft.imagemanager8082.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {

    @Autowired
    FilesStorageService storageService;

    @Autowired
    ImageService imageService;

    @Autowired
    Environment environment;

    @PostMapping("/uploadImage")
    public BaseModelJson<Image> uploadImage(@RequestParam("file")MultipartFile file) {

        String extension = "";
        String fileName =  file.getOriginalFilename();
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }
//        System.out.println(extension);
        if(!("jpg".equals(extension) || "png".equals(extension) || "gif".equals(extension) || "tiff".equals(extension))) {
            throwException("filetype_not_picture");
        }
        try {
            String filename = storageService.save(file);
            BaseModelJson<Image> result = new BaseModelJson<>();
            Image image = new Image();
            System.out.println(environment.getProperty("local.server.port"));
            String url = "http://localhost:" + environment.getProperty("local.server.port") + "/File/files/" + filename;
//            System.out.println(url);
            image.setUri(url);
            int j = imageService.insert(image);
            if (j == 1) {
                result.code = 200;
                result.message = "upload file success";
                result.data = image;
                return result;
            } else {
                throwException("insert_fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throwException("upload_file_fail");
        }
        return null;
    }

    @PostMapping("getById")
    public BaseModelJson<Image> getImgById(int img_id) {
       Image image = imageService.getById(img_id);
        if (image == null) {
            throwException("not_exists");
            return null;
        } else {
            BaseModelJson<Image> result = new BaseModelJson<>();
            result.code = 200;
            result.data = image;
            return result;
        }
    }


    @GetMapping("getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageVerificationCode ivc = new ImageVerificationCode();     //用我们的验证码类，生成验证码类对象
        BufferedImage image = ivc.getImage();  //获取验证码
        request.getSession().setAttribute("verifyCode", ivc.getText()); //将验证码的文本存在session中
        ivc.output(image, response.getOutputStream());//将验证码图片响应给客户端
    }

    @PostMapping("getVerifyCodeNumber")
    public BaseModelJson<String> getVerifyCodeNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println(request.getSession().getAttributeNames());
        String session_vcode=(String) request.getSession().getAttribute("verifyCode");    //从session中获取真正的验证码
        System.out.println(session_vcode);
        BaseModelJson<String> result = new BaseModelJson<>();
        result.code = 200;
        result.data = session_vcode;
        return result;
    }
}
