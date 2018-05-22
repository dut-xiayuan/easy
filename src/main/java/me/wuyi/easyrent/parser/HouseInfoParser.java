package me.wuyi.easyrent.parser;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.wuyi.easyrent.constant.GenderLimitType;
import me.wuyi.easyrent.constant.HouseType;
import me.wuyi.easyrent.constant.RentType;
import me.wuyi.easyrent.constant.RoomType;
import me.wuyi.easyrent.dao.HouseInfoDao;
import me.wuyi.easyrent.util.GeoTool;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static me.wuyi.easyrent.constant.GenderLimitType.FEMALE_ONLY;
import static me.wuyi.easyrent.constant.GenderLimitType.MALE_ONLY;
import static me.wuyi.easyrent.constant.HouseType.*;
import static me.wuyi.easyrent.constant.RentType.FULL_RENT;
import static me.wuyi.easyrent.constant.RentType.PART_RENT;
import static me.wuyi.easyrent.constant.RoomType.MAIN_ROOM;
import static me.wuyi.easyrent.constant.RoomType.SUB_ROOM;

/**
 * a parse tool, parse house info{@code HouseInfo} from content string
 */

public class HouseInfoParser {

    @Autowired
    private static HouseInfoDao houseInfoDao;

    private static List<String> regionList;

    private static List<String> lineList;

    static {
        regionList = houseInfoDao.getAllregion();
        lineList = houseInfoDao.getAllLine();
    }

    public static String parseOneLevelDistrict (String content, String city) {

        GeoTool geoTool = new GeoTool();
        String resJsonStr = geoTool.decodeAddress(content, city);
        JSONObject resJson = JSON.parseObject(resJsonStr);
        JSONArray geoCodes = resJson.getJSONArray("geocodes");
        if(geoCodes == null || geoCodes.size() != 1) {
            return "";
        }

        return geoCodes.getJSONObject(0).getString("district");
    }

    public static String parseTwoLevelRegion (String content) {
        for (String region : regionList) {
            if (content.contains(region)) {
                return region;
            }
        }
        return "";
    }

    public static String parseHouseType (String content) {
        if (content.contains(ONE_ROOM.getDesc())) {
            return ONE_ROOM.getDesc();
        } else if (content.contains(TWO_ROOM.getDesc())) {
            return TWO_ROOM.getDesc();
        } else if (content.contains(THREE_ROOM.getDesc())) {
            return THREE_ROOM.getDesc();
        } else if (content.contains(FOUR_ROOM.getDesc())) {
            return FOUR_ROOM.getDesc();
        }

        return HouseType.ANY.getDesc();
    }

    public static String parseRentType (String content) {
        if (content.contains(FULL_RENT.getDesc())) {
            return FULL_RENT.getDesc();
        } else if (content.contains(PART_RENT.getDesc())){
            return PART_RENT.getDesc();
        }

        return RentType.ANY.getDesc();
    }

    public static String parseIsMainroom (String content) {
        if (content.contains(MAIN_ROOM.getDesc())) {
            return MAIN_ROOM.getDesc();
        } else if (content.contains(SUB_ROOM.getDesc())) {
            return SUB_ROOM.getDesc();
        }

        return RoomType.ANY.getDesc();
    }

    public static String parseGenderLimit (String content) {
        if (content.contains(MALE_ONLY.getDesc())) {
            return MALE_ONLY.getDesc();
        } else if (content.contains(FEMALE_ONLY.getDesc())) {
            return FEMALE_ONLY.getDesc();
        }

        return GenderLimitType.ANY.getDesc();
    }

    public static String parseMetroNearBy (String content) {
        for (String line : lineList) {
            if (content.contains(line)) {
                return line;
            }
        }
        return "";
    }


}
