package com.lzn.document.documentmanage.controller;
import java.awt.image.BufferedImage;
import	java.io.IOException;

import com.lzn.document.documentmanage.utils.PDFtoJPG;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/6 16:16
 **/
@RestController
@RequestMapping("pdfChange")
@Api(description ="PDF转图片/图片转PDF")
public class PdfChangeImageController {
    // 设定输出的类型
    private static final String JPG = "image/jpeg;charset=GB2312";
    public static final float DEFAULT_DPI = 105;

    @GetMapping("/pdfToImage")
    @ApiOperation("Pdf转图片")
    public void pdfToImage( String path,HttpServletRequest request, HttpServletResponse response)throws Exception {
        // String ext = StringUtils.substringAfterLast(fileName, ".");
        File file = new File(path);
        if (!file.exists()) {
            return;
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
    }
    @GetMapping("/pdfToImage2")
    @ApiOperation("Pdf转图片")
    public void pdfToImage2( String path,HttpServletRequest request, HttpServletResponse response)throws Exception {
        PDFtoJPG.pdf2multiImage(path,"E:\\1_4_李润简历.jpg");
    }
}
