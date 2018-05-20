package me.wuyi.easyrent.constant;

public enum HouseType {

    ANY("不限",1),
    ONE_ROOM("一室", 2),
    TWO_ROOM("两室", 3),
    THREE_ROOM("三室", 4),
    FOUR_ROOM("四室", 5);

    private String desc;
    private int index;

    HouseType(String desc, int index) {
        this.desc = desc;
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
