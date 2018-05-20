package me.wuyi.easyrent.constant;

public enum SourceFromType {

    DOUBAN("豆瓣", "douban.com", 1),
    BYR("北邮人", "icybee.cn", 2),
    SMTH("水木社区", "newsmth.net", 3);

    private String value;
    private String sourceDomain;
    private int index;

    SourceFromType(String name, String sourceDomain, int index) {
        this.value = name;
        this.sourceDomain = sourceDomain;
        this.index = index;
    }

    public String getName() {
        return value;
    }

    public String getSourceDomain() {
        return sourceDomain;
    }

    public int getIndex() {
        return index;
    }

    public static SourceFromType val(String sourceDomain) {
        for(SourceFromType s : values()) {    //values()方法返回enum实例的数组
            if(sourceDomain.equals(s.getSourceDomain()))
                return s;
        }
        return null;
    }

}
