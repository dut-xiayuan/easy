package me.wuyi.easyrent.constant;

public enum RentType {

    ANY("不限", 1),
    FULL_RENT("整租", 2),
    PART_RENT("合租", 3);

    private String desc;
    private int index;

    RentType(String desc, int index) {
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
