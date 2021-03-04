package com.lzn.document.documentmanage.mode.file;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/29 10:42
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("file")
public class Files implements Serializable {
    private static final long serialVersionUID=1L;

    @TableId(type = IdType.UUID)
    private String id;
    /**
     * 文件存储路径
     */
    private String filePath;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件后缀名
     */
    private String fileSuffix;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    /**
     * 修改人
     */
    private String updateUser;
    @Version
    private Integer version;

}
