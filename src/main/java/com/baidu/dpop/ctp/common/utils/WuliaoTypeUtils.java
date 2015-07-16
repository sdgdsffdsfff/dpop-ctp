package com.baidu.dpop.ctp.common.utils;

import org.springframework.util.Assert;

import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.mainTask.constant.BeidouMcType;
import com.baidu.dpop.ctp.mainTask.constant.DSPMcType;
import com.baidu.dpop.ctp.mainTask.constant.QiushiMcType;

/**   
 * 物料类型工具类
 * @author cgd  
 * @date 2015年4月7日 下午6:26:33 
 */
public class WuliaoTypeUtils {
    
    /**
     * 判定给定Ad是否是图文类型的Ad
     * */
    public static boolean isTextAndPictureAd(Byte dataType, Integer wuliaoType) {
        Assert.notNull(dataType);
        Assert.notNull(wuliaoType);
        
        // 北斗创意
        if(GroupDataType.BEIDOU.getId().equals(dataType)) {
            if(BeidouMcType.IMG.getId().equals(wuliaoType) || BeidouMcType.IMGTXT.getId().equals(wuliaoType)) {
                return true;
            }
        }
        
        // 秋实创意
        if(GroupDataType.QIUSHI.getId().equals(dataType)) {
            if(QiushiMcType.IMG.getId().equals(wuliaoType) || QiushiMcType.TXT.getId().equals(wuliaoType)
                    || QiushiMcType.SMALLIMGTXT.getId().equals(wuliaoType)
                    || QiushiMcType.BIGIMGTXT.getId().equals(wuliaoType)
                    || QiushiMcType.GENIMGTEXT.getId().equals(wuliaoType)) {
                return true;
            }
        }
        
        // DSP创意
        if(GroupDataType.DSP.getId().equals(dataType)) {
            if(DSPMcType.IMG.getId().equals(wuliaoType)) {
                return true;
            }
        }
        
        return false;
    }

}
