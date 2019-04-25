package cn.icheny.ideaplugin.translation.net;

/**
 * <pre>
 *     @author : www.icheny.cn
 *     @e-mail : ausboyue@gmail.com
 *     @time   : 2019.04.25
 *     @desc   :请求工具类
 *     @version: 1.0.0
 * </pre>
 */
public final class HttpUtils {

    private HttpUtils() {

    }

    public static void doAsyncRequest(String url, HttpCallback callback) {
        new HttpTask(url, callback).start();
    }

    public static final X509Manager sX509Manager = new X509Manager();

}
