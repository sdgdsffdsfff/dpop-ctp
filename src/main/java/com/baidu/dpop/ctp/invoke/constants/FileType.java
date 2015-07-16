package com.baidu.dpop.ctp.invoke.constants;

public enum FileType {

    TAGED_BEIDOU(0x00010000, "北斗下载数据"), TAGED_QIUSHI(0x00010001, "秋实下载数据"), TAGED_DSP(0x00010002, "DSP下载数据"),
    TAGED_NEWDSP(0x00010003, "百度DSP下载数据"), FULL_BEIDOU(0x00020000, "北斗全集下载数据"), FULL_QIUSHI(0x00020001, "秋实全集下载数据"),
    FULL_DSP(0x00020002, "DSP全集下载数据"), FULL_NEWDSP(0x00020003, "百度DSP全集下载数据"), GROUP_INFO(0x00030000, "GROUP信息下载数据");

    private Integer id;
    private String desc;

    private FileType(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static FileType get(Number id) {
        if (null == id) {
            return null;
        }
        for (FileType temp : FileType.values()) {
            if (temp.getId().longValue() == id.longValue()) {
                return temp;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
