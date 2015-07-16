package com.baidu.dpop.ctp.industrytype.constants;

public enum IndustryDataType {

    LEVEL_TWO(Byte.valueOf("0"), "二级行业"), LEVEL_THREE(Byte.valueOf("1"), "三级行业");

    private Byte id;
    private String desc;

    private IndustryDataType(Byte id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static IndustryDataType get(Number id) {
        if (null == id) {
            return null;
        }
        for (IndustryDataType temp : IndustryDataType.values()) {
            if (temp.getId().equals(id)) {
                return temp;
            }
        }
        return null;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
