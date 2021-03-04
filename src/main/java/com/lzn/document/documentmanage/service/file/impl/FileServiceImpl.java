package com.lzn.document.documentmanage.service.file.impl;

import com.lzn.document.documentmanage.common.CommonResult;
import com.lzn.document.documentmanage.common.ResponseCode;
import com.lzn.document.documentmanage.mapper.file.FileMapper;
import com.lzn.document.documentmanage.mode.file.Files;
import com.lzn.document.documentmanage.service.file.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/29 11:14
 **/
@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Value("${file.save-path}")
    private String savePath;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public CommonResult upLoadFiles(MultipartFile file) {
        //设置支持最大上传的文件，这里是1024*1024*2=2M
        long MAX_SIZE = 2097152L;
        //获取要上传文件的名称
        String fileName = file.getOriginalFilename();
        //如果名称为空，返回一个文件名为空的错误
        if (StringUtils.isEmpty(fileName)) {
            return new CommonResult(ResponseCode.FILE_NAME_EMPTY.getCode(), ResponseCode.FILE_NAME_EMPTY.getMsg(), null);
        }
        //如果文件超过最大值，返回超出可上传最大值的错误
        if (file.getSize() > MAX_SIZE) {
            return new CommonResult(ResponseCode.FILE_MAX_SIZE.getCode(), ResponseCode.FILE_MAX_SIZE.getMsg(), null);
        }
        //获取到后缀名
        String suffixName = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : null;
        //文件的保存重新按照时间戳命名
        String newName = System.currentTimeMillis() + suffixName;
        File newFile = new File(savePath, newName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        try {
            //文件写入
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将这些文件的信息写入到数据库中
        Files files = new Files();
        files.setFileName(fileName);
        files.setFilePath(newFile.getPath());
        files.setFileSuffix(suffixName);
        fileMapper.insert(files);
        return new CommonResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), "数据上传成功");
    }

    @Override
    public Files getFileById(String id) {
        return fileMapper.selectFileById(id);
    }

    @Override
    public InputStream getFileInputStream(Files files) {
        File file = new File(files.getFilePath());
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
