package cn.icheny.ideaplugin.translation.net;


import cn.icheny.ideaplugin.translation.util.StreamParser;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import static cn.icheny.ideaplugin.translation.net.HttpUtils.sX509Manager;

/**
 * <pre>
 *     @author : www.icheny.cn
 *     @e-mail : ausboyue@gmail.com
 *     @time   : 2019.04.25
 *     @desc   : http 请求操作任务
 *     @version: 1.0.0
 * </pre>
 */
public final class HttpTask extends Thread {
    private final String mUrl;
    private final HttpCallback mCallback;

    public HttpTask(String mUrl, HttpCallback mCallback) {
        this.mUrl = mUrl;
        this.mCallback = mCallback;
    }


    @Override
    public void run() {
        try {
            // 设置SSLContext
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{sX509Manager}, null);
            URL uri = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            if (conn instanceof HttpsURLConnection) {
                ((HttpsURLConnection) conn).setSSLSocketFactory(sslcontext.getSocketFactory());
            }
            conn.setRequestMethod("GET");//声明请求方式 默认get
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                conn.disconnect();
                return;
            }
            InputStream is = conn.getInputStream();
            String text = StreamParser.readStream(is);
            if (mCallback != null) {
                mCallback.callback(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
