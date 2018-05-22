package me.wuyi.easyrent.handler;

import edu.uci.ics.crawler4j.crawler.Page;
import me.wuyi.easyrent.bean.HouseInfo;

public interface Handler {
    HouseInfo handle(Page page);
    HouseInfo handle(Page page, String city);
}
