package com.lzn.document.documentmanage.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/6 13:51
 * 图片转 PDF
 **/
public class JPGtoPDF {
    public static void convert(String source, String target) {
        Document document = new Document();
        //设置文档页边距
        document.setMargins(0,0,0,0);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(target);
            PdfWriter.getInstance(document, fos);
            //打开文档
            document.open();
            //获取图片的宽高
            Image image = Image.getInstance(source);
            float imageHeight=image.getScaledHeight();
            float imageWidth=image.getScaledWidth();
            //设置页面宽高与图片一致
            Rectangle rectangle = new Rectangle(imageWidth, imageHeight);
            document.setPageSize(rectangle);
            //图片居中
            image.setAlignment(Image.ALIGN_CENTER);
            //新建一页添加图片
            document.newPage();
            document.add(image);
        } catch (Exception ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            //关闭文档
            document.close();
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        String source = "E:\\2016-12-29.jpg";
        String target = "E:\\2016-12-29.pdf";
        convert(source, target);
    }

}
