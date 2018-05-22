package me.cwuyi.easyrent.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.wuyi.easyrent.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DistrictsTool {
    public static void main(String[] args) {
        Document doc = Jsoup.parse(HttpUtil.getResult("http://bj.yurixu.com/manage/beijing.php"));
        Element areaList = doc.getElementById("area-list");
        Elements areas = areaList.getElementsByTag("a");
        for (int i = 1; i < areas.size(); ++i) {
            System.out.println(areas.get(i).text().trim());
            JSONObject jsonObject = JSON.parseObject(HttpUtil.getResult("http://bj.yurixu.com/uz/index/getArea?id=" + i));
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int j = 0; j < jsonArray.size(); ++j) {
                System.out.println("--" + jsonArray.getJSONObject(j).getString("name"));
            }
        }

        Element lineList = doc.getElementById("line-list");
        Elements lines = lineList.getElementsByTag("a");
        for (int i = 1; i < areas.size(); ++i) {
            System.out.println(lines.get(i).text().trim());
        }

    }
}
