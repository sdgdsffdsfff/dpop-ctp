package com.baidu.dpop.ctp.task.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.task.bo.BeidouTask;
import com.baidu.dpop.ctp.task.bo.DSPTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.bo.NewDSPTask;
import com.baidu.dpop.ctp.task.bo.QiushiTask;
import com.baidu.dpop.ctp.task.vo.CreativityInfo;

public class GeneralTaskUtils {

    private static final Logger LOG = Logger.getLogger(GeneralTaskUtils.class);
    
    public static String getReviewDownloadTitle(Number dataType, Number taskType) {
        if (GroupDataType.isBeidou(dataType)) {
            return BeidouTask.genFullStringTitle(taskType);
        } else if (GroupDataType.isQiushi(dataType)) {
            return QiushiTask.genFullStringTitle(taskType);
        } else if (GroupDataType.isDSP(dataType)) {
            return DSPTask.genFullStringTitle(taskType);
        } else if (GroupDataType.isNewDsp(dataType)) {
            return NewDSPTask.genFullStringTitle(taskType);
        }
        
        return "";
    }

    public static GeneralTask getNewGeneralTask(Number dataType) {
        if (GroupDataType.isBeidou(dataType)) {
            return new BeidouTask();
        } else if (GroupDataType.isQiushi(dataType)) {
            return new QiushiTask();
        } else if (GroupDataType.isDSP(dataType)) {
            return new DSPTask();
        } else if (GroupDataType.isNewDsp(dataType)) {
            return new NewDSPTask();
        }

        return null;
    }

    public static Long sumPriority(Long p1, Long p2, Number dataType) {
        if (GroupDataType.isNewDsp(dataType)) {
            return p1 > p2 ? p1 : p2;
        } else {
            long fmask = 0xFF00000000000000L;
            long smask = 0x00FFFFFFFFFFFFFFL;
            long first = (p1 & fmask) > (p2 & fmask) ? (p1 & fmask) : (p2 & fmask);
            long second = (p1 & smask) + (p2 & smask);
            return first + second;
        }
    }

    public static List<GeneralTask> getGeneralTasks(List<String> slist, Number dataType) {
        List<GeneralTask> list = new ArrayList<GeneralTask>();
        Date date = new Date();
        if (GroupDataType.isNewDsp(dataType)) {
            try {
                ObjectMapper objMapper =
                        new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                objMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
                objMapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
                for (String line : slist) {
                    List<CreativityInfo> data = objMapper.readValue(line, new TypeReference<List<CreativityInfo>>() {
                    });
                    for (CreativityInfo item : data) {
                        list.add(item.toNewDSPTask(date));
                    }
                }
            } catch (Exception e) {
                LOG.error(e);
            }
        } else {
            for (String line : slist) {
                GeneralTask task = getNewGeneralTask(dataType);
                task.setArgs(line);
                list.add(task);
            }
        }
        return list;
    }
}
