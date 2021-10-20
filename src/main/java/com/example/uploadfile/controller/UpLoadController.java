package com.example.uploadfile.controller;

import com.example.uploadfile.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
public class UpLoadController {

    @Value("${file.localurl}")
    private String uploadPath;

    @PostMapping("/fileUpload")
    public JsonResult filesUpload(@RequestParam("file") MultipartFile file) throws IOException {

        JsonResult result = new JsonResult();
        //如果文件夹不存在，创建
        File fileP = new File(uploadPath);

        if (!fileP.isDirectory()) {
            //递归生成文件夹
            fileP.mkdirs();
        }
        String fileName = "";
        if(file.getOriginalFilename().endsWith(".jpg")){
            fileName =String.format("%s.jpg",System.currentTimeMillis());
        }else if(file.getOriginalFilename().endsWith(".png")){
            fileName =String.format("%s.jpg",System.currentTimeMillis());
        }else if(file.getOriginalFilename().endsWith(".jpeg")){
            fileName =String.format("%s.jpeg",System.currentTimeMillis());
        }else if(file.getOriginalFilename().endsWith(".bmp")){
            fileName =String.format("%s.bmp",System.currentTimeMillis());
        }else{
            result.setSuccess(false);
            result.setCode("0");
            result.setMessage("图片格式不正确！，使用.jpg/.png/.bpm/.jpeg后缀的图片");
            return result;
        }
        file.transferTo(new File(fileP,fileName));
        //数据库存入地址
//        cxShipDetailService.insertShipPic(mmsi,uploadPath+fileName,fileName);
        log.info("{}:",uploadPath+fileName);
        log.info("{}:",fileName);
        result.setSuccess(true);
        result.setCode("1");
        result.setMessage("上传图片成功！");
        return result;
    }
}
