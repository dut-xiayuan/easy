package me.wuyi.easyrent.constant;

public enum RoomType {
    ANY("不限", 1),
    MAIN_ROOM("主卧", 2),
    SUB_ROOM("次卧", 3);

    private String desc;
    private int index;

    RoomType(String desc, int index) {
        this.desc = desc;
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public int getIndex() {
        return index;
    }
}
