package me.wuyi.easyrent.dao;

import me.wuyi.easyrent.bean.HouseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

@Component
public interface HouseInfoDao {

    String HOUSE_INFO_TABLE = "";
    String INSERT_KEY = "";
    String INSERT_VALUE = "";

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO " + HOUSE_INFO_TABLE + " (" + INSERT_KEY + ") VALUES " + "(" +  INSERT_VALUE + ")")
    void insert(HouseInfo houseInfo);
}
