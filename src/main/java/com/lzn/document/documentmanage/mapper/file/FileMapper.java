package com.lzn.document.documentmanage.mapper.file;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzn.document.documentmanage.mode.file.Files;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/2911:18
 **/
@Repository
public interface FileMapper  extends BaseMapper<Files> {
    /**
     * 根据id获取文件
     * @param id
     * @return
     */
    Files selectFileById(String id);
}
