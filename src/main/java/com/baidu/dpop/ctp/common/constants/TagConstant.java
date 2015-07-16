package com.baidu.dpop.ctp.common.constants;

/**   
 * 标签相关常量定义
 * @author cgd  
 * @date 2015年4月1日 上午10:51:10 
 */
public class TagConstant {
    
    /** 美观度描述 **/
    public static final String BEAUTY_DESC = "美观度";
    /** 欺诈度描述 **/
    public static final String CHEAT_DESC = "欺诈度";
    /** 低俗度描述 **/
    public static final String VULGAR_DESC = "低俗度";
    /** 高危度描述 **/
    public static final String HIGH_DANGER_DESC = "高危度";
    
    /** 低 **/
    public static final Integer LOW = 10;
    /** 中 **/
    public static final Integer NORMAL = 11;
    /** 高 **/
    public static final Integer HIGH = 12;
    /** 黑 **/
    public static final Integer BLACK = 100;
    /** 灰 **/
    public static final Integer GRAY = 101;
    /** 白 **/
    public static final Integer WHITE = 102;
    /** 是 **/
    public static final Integer YES = 1;
    /** 否 **/
    public static final Integer NO = 0;
    
    /** 美观度 **/
    public static final String BEAUTY = "BEAUTY";
    /** 欺诈度 **/
    public static final String CHEAT = "CHEAT";
    /** 低俗度 **/
    public static final String VULGAR = "VULGAR";
    /** 高危度 **/
    public static final String HIGH_DANGER = "HIGH_DANGER";

    /** 北斗-美观度 **/
    public static final Integer BEIDOU_BEAUTY_TAG_BIT = 0x00000038;
    public static final Integer BEIDOU_LOW_BEAUTY = Integer.parseInt("001000",
            2);
    public static final Integer BEIDOU_NORMAL_BEAUTY = Integer.parseInt(
            "010000", 2);
    public static final Integer BEIDOU_HIGH_BEAUTY = Integer.parseInt(
            "011000", 2);

    /** 北斗-欺诈度 **/
    public static final Integer BEIDOU_CHEAT_TAG_BIT = 0x000001C0;
    public static final Integer BEIDOU_IS_CHEAT = Integer.parseInt(
            "001000000", 2);
    public static final Integer BEIDOU_NOT_CHEAT = Integer.parseInt(
            "010000000", 2);

    /** 北斗-低俗度 **/
    public static final Integer BEIDOU_VULGAR_TAG_BIT = 0x00000E00;
    public static final Integer BEIDOU_BLACK_VULGAR = Integer.parseInt(
            "001000000000", 2);
    public static final Integer BEIDOU_GRAY_VULGAR = Integer.parseInt(
            "010000000000", 2);
    public static final Integer BEIDOU_WHITE_VULGAR = Integer.parseInt(
            "011000000000", 2);

    /** 北斗-高危度 **/
    public static final Integer BEIDOU_HIGH_DANGER_TAG_BIT = 0x00007000;
    public static final Integer BEIDOU_IS_HIGH_DANGER = Integer.parseInt(
            "001000000000000", 2);
    public static final Integer BEIDOU_NOT_HIGH_DANGER = Integer.parseInt(
            "010000000000000", 2);

    // ---------------------------------------------------
    /** 秋实&DSP-美观度 **/
    public static final Integer QIUSHI_DSP_BEAUTY_TAG_BIT = 0x0000000f;
    public static final Integer QIUSHI_DSP_LOW_BEAUTY = Integer.parseInt(
            "0001", 2);
    public static final Integer QIUSHI_DSP_NORMAL_BEAUTY = Integer.parseInt(
            "0010", 2);
    public static final Integer QIUSHI_DSP_HIGH_BEAUTY = Integer.parseInt(
            "0011", 2);

    /** 秋实&DSP-欺诈度 **/
    public static final Integer QIUSHI_DSP_CHEAT_TAG_BIT = 0x000000f0;
    public static final Integer QIUSHI_DSP_IS_CHEAT = Integer.parseInt(
            "00010000", 2);
    public static final Integer QIUSHI_DSP_NOT_CHEAT = Integer.parseInt(
            "00100000", 2);

    /** 秋实&DSP-低俗度 **/
    public static final Integer QIUSHI_DSP_VULGAR_TAG_BIT = 0x00000f00;
    public static final Integer QIUSHI_DSP_BLACK_VULGAR = Integer.parseInt(
            "000100000000", 2);
    public static final Integer QIUSHI_DSP_GRAY_VULGAR = Integer.parseInt(
            "001000000000", 2);
    public static final Integer QIUSHI_DSP_WHITE_VULGAR = Integer.parseInt(
            "001100000000", 2);

    /** 秋实&DSP-高危度 **/
    public static final Integer QIUSHI_DSP_HIGH_DANGER_TAG_BIT = 0x0000f000;
    public static final Integer QIUSHI_DSP_IS_HIGH_DANGER = Integer.parseInt(
            "0001000000000000", 2);
    public static final Integer QIUSHI_DSP_NOT_HIGH_DANGER = Integer.parseInt(
            "0010000000000000", 2);
    
}
