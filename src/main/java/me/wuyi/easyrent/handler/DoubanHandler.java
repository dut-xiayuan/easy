package me.wuyi.easyrent.handler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import me.wuyi.easyrent.bean.HouseInfo;
import me.wuyi.easyrent.constant.SourceFromType;
import me.wuyi.easyrent.parser.HouseInfoParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import static me.wuyi.easyrent.constant.SourceFromType.BYR;
import static me.wuyi.easyrent.constant.SourceFromType.DOUBAN;
import static me.wuyi.easyrent.constant.SourceFromType.SMTH;

@Component("doubanHandler")
public class DoubanHandler implements Handler {

    @Override
    public HouseInfo handle(Page page) {

        return null;
    }

    /**
     *
     * @param page 要解析的页面
     * @param city 为了提高页面解析的准确性，需要提供租房信息的城市，通过crawler的customdata获取，
     *             如果无法获取，直接传空字符串
     * @return
     */
    @Override
    public HouseInfo handle(Page page, String city) {

        HouseInfo info = new HouseInfo();

        HtmlParseData htmlParseData = (HtmlParseData)page.getParseData();
        String html = htmlParseData.getHtml();
        Document doc = Jsoup.parse(html);
        Element content = doc.getElementById("content");
        String contentStr = content.text();

        String domian = page.getWebURL().getDomain();
        SourceFromType sourceFromType = SourceFromType.val(domian);
        switch (sourceFromType) {
            case DOUBAN:
                info.setSourceFrom(DOUBAN.getName());
                break;
            case SMTH:
                info.setSourceFrom(SMTH.getName());
                break;
            case BYR:
                info.setSourceFrom(BYR.getName());
                break;
            default:
                break;
        }

        info.setHouseLink(page.getWebURL().getURL());

        Elements h1 = content.getElementsByTag("h1");
        if (h1.size() != 1) {
            return null;
        }
        String title = h1.text().trim();
        info.setTopicDesc(title);

        Elements postDateEle = content.getElementsByAttributeValue("class", "color-green");
        if (postDateEle.size() != 1) {
            return null;
        }
        info.setPostDate(postDateEle.text().trim());

        info.setGenderLimit(HouseInfoParser.parseGenderLimit(contentStr));
        info.setHouseType(HouseInfoParser.parseHouseType(contentStr));
        info.setRoomType(HouseInfoParser.parseIsMainroom(contentStr));
        info.setOneLevelDistrict(HouseInfoParser.parseOneLevelDistrict(title, city));
        info.setTwoLevelRegion(HouseInfoParser.parseTwoLevelRegion(contentStr));
        info.setRentType(HouseInfoParser.parseRentType(contentStr));
        info.setMetroNearBy(HouseInfoParser.parseMetroNearBy(contentStr));

        return null;
    }
}
