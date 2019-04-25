package cn.icheny.ideaplugin.translation.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * <pre>
 *     @author : www.icheny.cn
 *     @e-mail : ausboyue@gmail.com
 *     @time   : 2019.04.25
 *     @desc   :流解析
 *     @version: 1.0.0
 * </pre>
 */
public class StreamParser {
    /**
     * 把输入流的内容转换成字符串
     *
     * @param is
     * @return null解析失败， string读取成功
     */
    public static String readStream(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 << 2];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();
            String temptext = new String(baos.toByteArray());
            if (temptext.contains("charset=gb2312")) {//解析meta标签
                return new String(baos.toByteArray(), "gb2312");
            } else {
                return new String(baos.toByteArray(), "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            is = null;
        }
    }
}
