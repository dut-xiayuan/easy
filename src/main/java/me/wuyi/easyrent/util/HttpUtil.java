package me.wuyi.easyrent.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String getResult(String url) {
        HttpGet get = new HttpGet(url);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String ret = "";
        try {
            httpClient = HttpClients.createDefault();
            response = httpClient.execute(get);
            ret = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            logger.error("getResult# get string result fail, surl:{}", url, e);
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                logger.error("getResult# close response or httpClient error", e);
            }
        }
        return ret;

    }

}
