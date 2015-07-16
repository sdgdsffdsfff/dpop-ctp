package com.baidu.dpop.ctp.invoke.vo;

/**
 * DataLoadStatus
 * 
 * @author cgd
 * @date 2015年4月17日 下午2:27:13
 */
public enum DataLoadStatus {
    ING(Byte.valueOf("0"), "进行中"), SUCCESS(Byte.valueOf("1"), "已完成"), FAIL(Byte.valueOf("2"), "失败");

    private Byte id;
    private String desc;

    private DataLoadStatus(Byte id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static DataLoadStatus get(Number id) {
        if (null == id) {
            return null;
        }
        for (DataLoadStatus temp : DataLoadStatus.values()) {
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
