package com.javabase.http;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

public class TestHttp {
    public static void main(String[] args) {

        final String imageUrl = "网上图片地址";

        try {
            final String base643 = downloadImageAndEncodeToBase64(imageUrl);
            System.out.println(base643);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 获取图片的输入流
    private static String downloadImageAndEncodeToBase64(String imageUrl) throws IOException{
        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url);

        // 将图片转换为Base64字符串
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", bos);
        byte[] imageBytes = bos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    // 获取图片的输入流
    private static void saveImageFromBase64(String base64Image, String outputPath) throws IOException {
        // Base64解码
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        // 将解码后的字节写入文件
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            fos.write(imageBytes);
        }
    }
}
