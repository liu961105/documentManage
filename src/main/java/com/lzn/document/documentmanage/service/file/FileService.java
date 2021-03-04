package com.lzn.document.documentmanage.service.file;

import com.lzn.document.documentmanage.common.CommonResult;
import org.springframework.web.multipart.MultipartFile;
import com.lzn.document.documentmanage.mode.file.Files;
import java.io.InputStream;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/2911:12
 **/
public interface FileService {
    /**
     * 文件上传接口
     * @param file
     * @return
     */
    CommonResult upLoadFiles(MultipartFile file);

    /**
     * 根据id获取文件
     * @param id
     * @return
     */
    Files getFileById(String id);


    /**
     * 根据id获取数据流
     * @param files
     * @return
     */
    InputStream getFileInputStream(Files files);



}
