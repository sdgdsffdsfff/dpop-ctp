package com.baidu.dpop.ctp.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.common.constants.LocalCacheKey;
import com.baidu.dpop.ctp.industrytype.bo.NewIndustryType;
import com.baidu.dpop.ctp.industrytype.service.IndustryTypeService;
import com.baidu.dpop.frame.core.context.SpringContextUtil;

/**
 * 行业信息获取工具类（数据存缓存）
 * 
 * @author cgd
 * @date 2015年3月30日 下午8:38:37
 */
public class TradeUtils {

    /**
     * 获取指定3级行业ID的行业信息
     * */
    public static NewIndustryType getLevelThreeIndustryInfo(Integer tradeLevel3Id) {
        Assert.notNull(tradeLevel3Id);

        // 从缓存中获取行业数据
        Map<Integer, NewIndustryType> totalData = CacheUtils.get(LocalCacheKey.LEVEL_3_TRADE_INFO_KEY);
        if (totalData != null) {
            return totalData.get(tradeLevel3Id);
        }

        // 若缓存中不存在，则set到缓存中，并读取数据
        IndustryTypeService industryTypeService = SpringContextUtil.getBean(IndustryTypeService.class);
        List<NewIndustryType> tradeData = industryTypeService.getLevel3Data();
        Map<Integer, NewIndustryType> tradeDataMap = new HashMap<Integer, NewIndustryType>();
        if (CollectionUtils.isNotEmpty(tradeData)) {
            for (NewIndustryType item : tradeData) {
                tradeDataMap.put(item.getFullId(), item);
            }
        }

        CacheUtils.set(LocalCacheKey.LEVEL_3_TRADE_INFO_KEY, tradeDataMap);
        return tradeDataMap.get(tradeLevel3Id);
    }

}
