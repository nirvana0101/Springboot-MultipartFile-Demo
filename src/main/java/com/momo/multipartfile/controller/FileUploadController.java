package com.momo.multipartfile.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
    @RequestMapping("/")
    public String index(){
        return ("index");
    }

    /**
     * 上传单个：方式一
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload01")
    @ResponseBody
    public String upload01(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return "Please select a file to upload";
        }
        File file=new File("C:/Users/Administrator/Desktop/file/"+multipartFile.getOriginalFilename());
        IOUtils.copy(multipartFile.getInputStream(),new FileOutputStream(file));
        return "You successfully uploaded '" + multipartFile.getOriginalFilename();
    }

    /**
     * 上传单个：方式二
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload02")
    @ResponseBody
    public String upload02(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return "Please select a file to upload";
        }
        Path path = Paths.get("C:/Users/Administrator/Desktop/file/"+ multipartFile.getOriginalFilename());
        Files.write(path, multipartFile.getBytes());
        return "You successfully uploaded '" + multipartFile.getOriginalFilename();
    }

    /**
     * 上传多个文件
     * @param files
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String uploadFiles(@RequestParam("file") MultipartFile[] files) throws IOException {
        if (files.length==0) {
            return "Please select a file to upload";
        }
        for(MultipartFile file:files) {
            Path path = Paths.get("C:/Users/Administrator/Desktop/file/" + file.getOriginalFilename());
            Files.write(path, file.getBytes());
        }
        return "You successfully uploaded all" ;
    }

}
