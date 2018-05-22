package me.wuyi.easyrent.dao;

import me.wuyi.easyrent.bean.HouseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HouseInfoDao {

    String HOUSE_INFO_TABLE = "";
    String INSERT_KEY = "";
    String INSERT_VALUE = "";

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO " + HOUSE_INFO_TABLE + " (" + INSERT_KEY + ") VALUES " + "(" +  INSERT_VALUE + ")")
    void insert(HouseInfo houseInfo);

    @Select("SELECT a.region FROM area a")
    List<String> getAllregion();

    @Select("SELECT l.line_name FROM lines l")
    List<String> getAllLine();

}
