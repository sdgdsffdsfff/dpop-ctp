package com.baidu.dpop.ctp.group.constant;

/**
 * 数据类型，即广告库类型。同一group下的所有ad必属同一类型
 * 
 * @author mading01
 * 
 */
public enum GroupDataType {

    BEIDOU(Byte.valueOf("0"), "Beidou"), QIUSHI(Byte.valueOf("1"), "Qiushi"), DSP(
            Byte.valueOf("2"), "DSP"), NEWDSP(Byte.valueOf("3"), "NewDSP"), ALL(
            Byte.valueOf("99"), "all");

    private Byte id;
    private String desc;

    private GroupDataType(Byte id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static boolean isBeidou(Number id) {
        if (id == null) {
            return false;
        }
        return id.intValue() == BEIDOU.id.intValue();
    }

    public static boolean isQiushi(Number id) {
        if (id == null) {
            return false;
        }
        return id.intValue() == QIUSHI.id.intValue();
    }

    public static boolean isDSP(Number id) {
        if (id == null) {
            return false;
        }
        return id.intValue() == DSP.id.intValue();
    }

    public static boolean isNewDsp(Number id) {
        if (id == null) {
            return false;
        }
        return id.intValue() == NEWDSP.id.intValue();
    }

    public static GroupDataType get(Number id) {
        if (null == id) {
            return null;
        }
        for (GroupDataType temp : GroupDataType.values()) {
            if (temp.getId().equals(id.byteValue())) {
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
