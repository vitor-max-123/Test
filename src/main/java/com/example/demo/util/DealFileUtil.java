package com.example.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author Administrator
 * @create 2021/10/20 0020 13:46
 */
public class DealFileUtil {
    /**
     * summary:将流转化为字节数组
     */
    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        byte[] result;
        try {
            int n;
            while ((n = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            result = out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
        return result;
    }
    /**
     * 将流转换成字符串 使用Base64加密
     */
    public static String streamToString(InputStream inputStream) throws IOException {
        byte[] bt = toByteArray(inputStream);
        inputStream.close();
        return Base64.getEncoder().encodeToString(bt);
    }
}
