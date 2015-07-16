package com.baidu.dpop.ctp.mainTask.constant;

import com.baidu.dpop.ctp.group.constant.GroupDataType;

public enum GeneralMcType {

    INVALID(0, "非法类型"), TXT(1, "文字"), IMG(2, "图片"), FLASH(3, "flash"), RICHMEDIA(4, "富媒体"), IMGTXT(5, "图文"), VIDEO(6,
            "视频"), HTML(9, "网页");

    private Integer id;
    private String desc;

    public static boolean isTXT(Number id) {
        if (id.intValue() == TXT.id.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isIMG(Number id) {
        if (id.intValue() == IMG.id.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isFLASH(Number id) {
        if (id.intValue() == FLASH.id.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isRICHMEDIA(Number id) {
        if (id.intValue() == RICHMEDIA.id.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isIMGTXT(Number id) {
        if (id.intValue() == IMGTXT.id.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isVIDEO(Number id) {
        if (id.intValue() == VIDEO.id.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isHTML(Number id) {
        if (id.intValue() == HTML.id.intValue()) {
            return true;
        }
        return false;
    }

    public static Integer getGeneralMcType(Number id, Number dataType) {
        if (GroupDataType.isBeidou(dataType)) {
            return getBeidouMcType(id.intValue());
        } else if (GroupDataType.isQiushi(dataType)) {
            return getQiushiMcType(id.intValue());
        } else if (GroupDataType.isDSP(dataType)) {
            return getDSPMcType(id.intValue());
        } else if (GroupDataType.isNewDsp(dataType)) {
            return getNewDSPMcType(id.intValue());
        }
        return -1;
    }

    public static Integer getBeidouMcTypeFromGeneral(int type) {
        switch (type) {
            case 1:
                return BeidouMcType.TXT.getId();
            case 2:
                return BeidouMcType.IMG.getId();
            case 3:
                return BeidouMcType.FLASH.getId();
            case 5:
                return BeidouMcType.IMGTXT.getId();
            case 9:
                return BeidouMcType.HTML.getId();
            default:
                return -1;
        }
    }

    public static Integer getQiushiMcTypeFromGeneral(int type) {
        switch (type) {
            case 0:
                return QiushiMcType.INVALID.getId();
            case 1:
                return QiushiMcType.TXT.getId();
            case 2:
                return QiushiMcType.IMG.getId();
            case 4:
                return QiushiMcType.RICHMEDIA.getId();
            case 5:
                return QiushiMcType.GENIMGTEXT.getId();
            case 6:
                return QiushiMcType.VIDEO.getId();
            default:
                return -1;
        }
    }

    public static Integer getDSPMcTypeFromGeneral(int type) {
        switch (type) {
            case 2:
                return DSPMcType.IMG.getId();
            case 3:
                return DSPMcType.FLASH.getId();
            case 6:
                return DSPMcType.VIDEO.getId();
            default:
                return -1;
        }
    }

    private static Integer getBeidouMcType(int id) {
        switch (id) {
            case 1:
                return TXT.id;
            case 2:
                return IMG.id;
            case 3:
                return FLASH.id;
            case 5:
                return IMGTXT.id;
            case 9:
                return HTML.id;
            default:
                return TXT.id;
        }
    }

    private static Integer getQiushiMcType(int id) {
        switch (id) {
            case 0:
                return INVALID.id;
            case 1:
                return TXT.id;
            case 2:
                return IMG.id;
            case 3:
                return VIDEO.id;
            case 4:
                return RICHMEDIA.id;
            case 5:
                return IMGTXT.id;
            case 6:
                return IMGTXT.id;
            default:
                return TXT.id;
        }
    }

    private static Integer getDSPMcType(int id) {
        switch (id) {
            case 1:
                return IMG.id;
            case 2:
                return FLASH.id;
            case 3:
                return VIDEO.id;
            default:
                return IMG.id;
        }
    }

    private static Integer getNewDSPMcType(int id) {
        switch (id) {
            case 1:
                return TXT.id;
            case 2:
                return IMG.id;
            case 3:
                return FLASH.id;
            case 5:
                return IMGTXT.id;
            case 7:
                return HTML.id;
            case 8:
                return VIDEO.id;
            default:
                return TXT.id;
        }
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

    private GeneralMcType(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static GeneralMcType get(Number id) {
        if (null == id) {
            return null;
        }
        for (GeneralMcType temp : GeneralMcType.values()) {
            if (temp.getId().equals(id.intValue())) {
                return temp;
            }
        }
        return null;
    }

    public static Boolean needUrl(Number type) {
        if (type.intValue() == IMG.id.intValue()) {
            return true;
        } else if (type.intValue() == FLASH.id.intValue()) {
            return true;
        } else if (type.intValue() == RICHMEDIA.id.intValue()) {
            return true;
        } else if (type.intValue() == IMGTXT.id.intValue()) {
            return true;
        } else if (type.intValue() == VIDEO.id.intValue()) {
            return true;
        }
        return false;
    }
}
