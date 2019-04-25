package cn.icheny.ideaplugin.translation.baiduapi;

import cn.icheny.ideaplugin.translation.net.UrlUtils;
import cn.icheny.ideaplugin.translation.util.MD5;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TransApi {
    private static final String APP_ID = "20190412000287134";
    private static final String SECURITY_KEY = "YvXblXuqxTPheJLFakjE";

    public static Map<String, String> buildParams(String query) {
        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", "auto");
        String to = "en";
        if (UrlUtils.encode(query).equals(query)) {
            // 传入的是纯英文，需要转中文
            to = "zh";
        }
        params.put("to", to);
        params.put("appid", APP_ID);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = APP_ID + query + salt + SECURITY_KEY; // 加密前的原文

        String sign = "";
        try {
            sign = MD5.md5(src);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("sign", sign);

        return params;
    }
}
