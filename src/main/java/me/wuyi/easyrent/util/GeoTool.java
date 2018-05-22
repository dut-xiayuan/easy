package me.wuyi.easyrent.util;

import me.wuyi.easyrent.constant.GaodeProperties;

import java.util.Properties;

/**
 * 地址解析工具--高德地图开放API
 */

public class GeoTool {

    /**
     *
     * @param address
     * @return 高德返回的json串
     */

    private static String GAODE_GEO_BASE_URL;
    private static String GAODE_GEO_APPKEY;

    static {
        try {
            Properties properties = new Properties();
            properties.load(GeoTool.class.getResourceAsStream("/gaodegeo.properties"));
            GAODE_GEO_BASE_URL = properties.getProperty(GaodeProperties.GAODE_GEO_BASE_URL_PRO);
            GAODE_GEO_APPKEY = properties.getProperty(GaodeProperties.GAODE_GEO_APPKEY_PRO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String decodeAddress(String address, String city) {

        String getStr = GAODE_GEO_BASE_URL + address + "&key=" + GAODE_GEO_APPKEY + "&city=" + city;
        String addressRes = HttpUtil.getResult(getStr);

        return addressRes;
    }

}
