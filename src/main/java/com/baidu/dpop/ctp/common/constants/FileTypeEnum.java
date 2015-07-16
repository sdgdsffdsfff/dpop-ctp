package com.baidu.dpop.ctp.common.constants;

import java.util.HashMap;
import java.util.Map;


/**
 * ImageTypeEnum <br/>
 * date: 2014-8-1 上午8:22:00 <br/>
 * <p/>
 * 文件类型枚举
 * 其中ID码的组成规则是：1+文件类型（1位，0-文本，1-图片，2-flash，3-视频，4-音频，...）+文件格式（2位，比如“jpg”、“gif”、“avi”等等）
 *
 * @author wujin.zzq
 *         TODO
 * @author huhailiang
 * @since JDK 1.6
 */
public enum FileTypeEnum {

    
    TYPE_XML(1, 0, "xml",   "3C3F786D"),
    TYPE_TXT(2, 0, "txt",   "31323232"),
    TYPE_CSV(3, 0, "csv", "FFFFFFFF")

    ;

    /**
     * 文件签名最大长度（单位：字节）。目前所有的文件签名长度均一致。
     */
    public static final int SIGNATURE_MAX_LENGTH_IN_BYTES = 4;

    
    private int id;
    private int type;
    private String format;
    private String headInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(String headInfo) {
        this.headInfo = headInfo;
    }

    private FileTypeEnum(int id, int type, String format, String headInfo) {
        this.id = id;
        this.type = type;
        this.format = format;
        this.headInfo = headInfo;
    }

    public static FileTypeEnum get(int id) {
        for (FileTypeEnum temp : FileTypeEnum.values()) {
            if (temp.getId()==id) {
                return temp;
            }
        }
        return null;
    }
    
    public Map<String, String> getFormatMap() {
        Map<String, String> typeMap = new HashMap<String, String>();
        typeMap.put(format, headInfo);
        return typeMap;
    }

    public static FileTypeEnum getByHeadInfo(String headInfo) {
        for (FileTypeEnum fe : FileTypeEnum.values()) {
            if (fe.headInfo.substring(0, 4).equals(headInfo.substring(0, 4))) {
                return fe;
            }
        }
        return null;
    }


    public static FileTypeEnum getFileType(byte[] fileData) {
        if (null == fileData || fileData.length < 4) {
            return null;
        }

        byte[] headInfo = new byte[4];
        System.arraycopy(fileData, 0, headInfo, 0, 4);

        // 根据文件头判断文件类型
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < headInfo.length; i++) {
            int value = headInfo[i] & 0xFF;
            String hexValue = Integer.toHexString(value);
            if (hexValue.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hexValue);
        }
        FileTypeEnum fileTypeEnum = FileTypeEnum.getByHeadInfo(stringBuilder.toString().toUpperCase());

        if (fileTypeEnum != null) {
            return fileTypeEnum;
        }

        // txt文件不能使用文件头来判单，如果文件byte中都是非零则是文本
        boolean fileByteConZero = false;
        for (byte fileDataByte : fileData) {
            if (0 == fileDataByte) {
                fileByteConZero = true;
                break;
            }
        }

        return fileByteConZero ? null : FileTypeEnum.TYPE_TXT;
    }
}
