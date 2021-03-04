package com.lzn.document.documentmanage.controller.file;

import com.lzn.document.documentmanage.common.CommonResult;
import com.lzn.document.documentmanage.common.ResponseCode;
import com.lzn.document.documentmanage.mapper.studentInfo.StudentInfoMapper;
import com.lzn.document.documentmanage.mode.file.Files;
import com.lzn.document.documentmanage.service.file.FileService;
import com.lzn.document.documentmanage.utils.PDFtoJPG;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/28 11:34
 **/
@RestController
@RequestMapping("api/file")
@Api(description = "文件管理模块相关接口")
public class FileController {
    private static final String JPG = "image/jpeg;charset=GB2312";
    public static final float DEFAULT_DPI = 105;
    public static final Log logger = LogFactory.getLog(FileController.class);
    @Autowired
    private FileService fileService;

    @GetMapping("/pdfToImage")
    @ApiOperation("Pdf转图片one")
    public CommonResult pdfToImage(String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // String ext = StringUtils.substringAfterLast(fileName, ".");
        File file = new File(path);
        if (!file.exists()) {
            return new CommonResult(ResponseCode.FILE_NOTEXITS.getCode(), ResponseCode.FILE_NOTEXITS.getMsg(), "");
        }
        response.reset(); // 非常重要
        response.setContentType(JPG);
        OutputStream out = response.getOutputStream();
        // 图像合并使用参数
        int width = 0; // 总宽度
        int[] singleImgRGB; // 保存一张图片中的RGB数据
        int shiftHeight = 0;
        BufferedImage imageResult = null;// 保存每张图片的像素值
        PDDocument doc = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(doc);
        int pageCount = doc.getNumberOfPages();
        // BufferedImage image = null;
        for (int i = 0, len = pageCount; i < pageCount; i++) {
            // image = renderer.renderImageWithDPI(i, 144);
            // ImageIO.write(image, "jpg", file);
            BufferedImage image = renderer.renderImageWithDPI(i, DEFAULT_DPI, ImageType.RGB);
            int imageHeight = image.getHeight();
            int imageWidth = image.getWidth();
            if (i == 0) {// 计算高度和偏移量
                width = imageWidth;// 使用第一张图片宽度;
                // 保存每页图片的像素值
                imageResult = new BufferedImage(width, imageHeight * len, BufferedImage.TYPE_INT_RGB);
            } else {
                shiftHeight += imageHeight; // 计算偏移高度
            }
            singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
            imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width); // 写入流中
        }
        doc.close();
        ImageIO.write(imageResult, "jpg", out);// 写图片
        return new CommonResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), "");
    }

    @GetMapping("/pdfToImage2")
    @ApiOperation("Pdf转图片Two")
    public void pdfToImage2(String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PDFtoJPG.pdf2multiImage(path, "E:\\1_4_李润简历.jpg");
    }

    //文件上传接口
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation("文件上传")
    public CommonResult upLoadFiles(MultipartFile multipartFile) {
        //如果文件为空，直接返回错误信息
        if (multipartFile.isEmpty()) {
            return new CommonResult(ResponseCode.FILE_EMPTY.getCode(), ResponseCode.FILE_EMPTY.getMsg(), null);
        }
        //否则调用service上传文件
        return fileService.upLoadFiles(multipartFile);
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    @ApiOperation("文件下载")
    public void downloadFiles(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        //先根据id查到文件信息
        Files files = fileService.getFileById(id);
        String fileName = files.getFileName();
        //通过文件信息将文件转化为inputStream
        inputStream = fileService.getFileInputStream(files);
        //下载文件需要设置的header
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        // 获取输出流
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭流
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
