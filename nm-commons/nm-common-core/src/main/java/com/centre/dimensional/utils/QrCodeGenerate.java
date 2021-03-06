package com.centre.dimensional.utils;

import com.centre.dimensional.constant.CommonConstant;
import com.centre.dimensional.handle.MatrixToImageWriterAlter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * @version : 1.0.0
 * @description: 二维码生成类
 * @author: GL
 * @program: mind-center-platform
 * @create: 2020年 05月 18日 22:35
 **/
public class QrCodeGenerate {

    /***
     * 二维码生成类
     * 虽然这个方法并没有返回值，因为二维码使用流的方式传递给前端了。
     * @param response
     * @param logoImages logo图片
     * @param contents 二维码里的数据
     * @throws IOException
     * @throws WriterException
     */
    public void EncodeQrCode(HttpServletResponse response, String logoImages, String contents) throws IOException, WriterException {
        // 二维码图片宽度 300
        int width = CommonConstant.QRCODE_WIDTH;
        // 二维码图片高度300
        int height = CommonConstant.GRCODE_HEIGHT;

        // 二维码的图片格式 gif
        String format = CommonConstant.GRCODE_FORMAT;

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//		hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值
//	    hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值
        //设置二维码边的空度，非负数
        hints.put(EncodeHintType.MARGIN, CommonConstant.GRCODE_BORDER_DISTANCE);

        //要编码的内容
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION
                BarcodeFormat.QR_CODE,
                //条形码的宽度
                width,
                //条形码的高度
                height,
                //生成条形码时的一些配置,此项可选
                hints);

        //获取输出流
        OutputStream fos = response.getOutputStream();
        BufferedImage bimage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        MatrixToImageWriterAlter.writeToStream(bitMatrix, format, fos, logoImages);
        if (!ImageIO.write(bimage, CommonConstant.GRCODE_OUTPUT_IMAGES, fos)) {
            throw new IOException("Could not write an image ");
        }
        fos.close();
    }
}
