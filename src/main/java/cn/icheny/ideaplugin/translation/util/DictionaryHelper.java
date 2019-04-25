package cn.icheny.ideaplugin.translation.util;

import cn.icheny.ideaplugin.translation.baiduapi.TransApi;
import cn.icheny.ideaplugin.translation.bean.Baidu;
import cn.icheny.ideaplugin.translation.bean.Ciba;
import cn.icheny.ideaplugin.translation.bean.Youdao;
import cn.icheny.ideaplugin.translation.net.UrlUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;


/**
 * <pre>
 *     @author : www.icheny.cn
 *     @e-mail : ausboyue@gmail.com
 *     @time   : 2019.04.25
 *     @desc   :对词典信息进行处理的帮助类
 *     @version: 1.0.0
 * </pre>
 */
public class DictionaryHelper {

    private static final String URL_YOU_DAO = "http://fanyi.youdao.com/openapi.do?keyfrom=neverland&key=969918857&type=data&doctype=json&version=1.1&q=";
    private static final String URL_BAIDU = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    private static final String URL_CI_BA = "http://dict-co.iciba.com/api/dictionary.php?type=json&key=145C75C96D8F319CFBAA5A162CD89309&w=";

    public static String createCibaUrl(@NotNull String word) {
        return URL_CI_BA + UrlUtils.encode(word.toLowerCase());
    }

    public static String createYoudaoUrl(@NotNull String word) {
        return URL_YOU_DAO + UrlUtils.encode(word);
    }

    public static String createBaiduUrl(@NotNull String word) {
        final Map<String, String> params = TransApi.buildParams(word);
        if (params == null) {
            return URL_BAIDU;
        }
        StringBuilder builder = new StringBuilder(URL_BAIDU);
        if (URL_BAIDU.contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }

        int i = 0;
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null) { // 过滤空的key
                continue;
            }

            if (i != 0) {
                builder.append('&');
            }

            builder.append(key);
            builder.append('=');
            builder.append(UrlUtils.encode(value));

            i++;
        }

        return builder.toString();
    }

    public static Youdao fomatByYoudao(@NotNull String text) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        return new Gson().fromJson(text, Youdao.class);
    }

    public static Baidu fomatByBaidu(@NotNull String text) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        return new Gson().fromJson(text, Baidu.class);
    }

    public static Ciba fomatByCiba(@NotNull String text) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(text, Ciba.class);
    }
}
