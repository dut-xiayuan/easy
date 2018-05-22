package me.cwuyi.easyrent.util;

import me.wuyi.easyrent.util.GeoTool;
import org.junit.Test;

public class GeoToolTest {

    @Test
    public void testDecodeAddress () {
        GeoTool tool = new GeoTool();
        System.out.println(tool.decodeAddress("11号线三林地铁站金谊河畔朝南大主卧转租", "上海"));
    }

}
