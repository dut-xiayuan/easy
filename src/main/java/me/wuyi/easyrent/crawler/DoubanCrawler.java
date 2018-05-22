package me.wuyi.easyrent.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import me.wuyi.easyrent.bean.HouseInfo;
import me.wuyi.easyrent.dao.HouseInfoDao;
import me.wuyi.easyrent.handler.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.regex.Pattern;

public class DoubanCrawler extends WebCrawler {
    private static final Logger logger = LoggerFactory.getLogger(DoubanCrawler.class);
    private static final String DOUBAN_DOMAIN = "https://www.douban.com/";
    private static final Pattern DOUBAN_TOPIC_URL_PATTERN = Pattern.compile(DOUBAN_DOMAIN + "group/topic/[0-9]+/");
    private static final Pattern DOUBAN_NEXT_PAGE_URL_PATTERN = Pattern.compile(DOUBAN_DOMAIN + "group/(shanghaizufang|beijingzufang)/discussion\\?start=[0-9]+");
    private static final String DOUBAN_TOPIC_KEY_STORE_IN_REDIS = "douban_topic_visited";

    @Autowired
    @Qualifier("doubanHandler")
    private Handler handler;

    @Autowired
    private HouseInfoDao houseInfoDao;

    //TODO:每次都从redis拉取数据是不是会耗时，要不要考虑将set缓存
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        if (DOUBAN_NEXT_PAGE_URL_PATTERN.matcher(href).matches()) {
            return true;
        } else if (DOUBAN_TOPIC_URL_PATTERN.matcher(href).matches()) {
            String  topicID = href.replaceAll("\\D", "").trim();
            if (!redisTemplate.opsForSet().members(DOUBAN_TOPIC_KEY_STORE_IN_REDIS).contains(topicID)) {
                redisTemplate.opsForSet().add(DOUBAN_TOPIC_KEY_STORE_IN_REDIS, topicID);
                return true;
            }
        }
        return false;
    }

    public void visit(Page page) {
        HouseInfo houseInfo = handler.handle(page, this.getMyController().getCustomData().toString());
        houseInfoDao.insert(houseInfo);
    }





}
