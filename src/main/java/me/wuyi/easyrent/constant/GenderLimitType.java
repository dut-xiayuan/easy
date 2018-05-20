package me.wuyi.easyrent.constant;

public enum GenderLimitType {

    MALE_ONLY("限男", 1), FEMALE_ONLY("限女", 2), ANY("不限", 3);

    private String desc;
    private int index;

    GenderLimitType(String desc, int index) {
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
