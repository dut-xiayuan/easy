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
        info.setTopicDesc(h1.text().trim());

        Elements postDateEle = content.getElementsByAttributeValue("class", "color-green");
        if (postDateEle.size() != 1) {
            return null;
        }
        info.setPostDate(postDateEle.text().trim());


        info.setGenderLimit(HouseInfoParser.parseGenderLimit(contentStr));
        info.setHouseType(HouseInfoParser.parseHouseType(contentStr));
        info.setMainroom(HouseInfoParser.parseIsMainroom(contentStr));
        info.setOneLevelDistrict(HouseInfoParser.parseOneLevelDistrict(contentStr));
        info.setTwoLevelRegion(HouseInfoParser.parseTwoLevelRegion(contentStr));
        info.setRentType(HouseInfoParser.parseRentType(contentStr));
        info.setMetroNearBy(HouseInfoParser.parseMetroNearBy(contentStr));





        return null;
    }
}
