package com.baidu.dpop.ctp.common.utils;

import com.baidu.noah.naming.BNSClient;
import com.baidu.noah.naming.BNSException;
import com.baidu.noah.naming.BNSInstance;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * BNS 工具类
 */
public class BnsUtils {

    // 此处需要调研BNSClient是否为线程安全的才可以打开注释
    // private static BNSClient bnsClient = new BNSClient();

    private static Logger logger = Logger.getLogger(BnsUtils.class);
    private static final String COLON = ":";
    private static final String SEPERATOR = ",";
    private static final int TRYTIMES = 30;

    /**
     * 获取name对应的所有IP+端口List
     * 
     * @param name 远程接口在bns中配置的serverName
     * */
    public static List<String> getIpPortList(String name) {
        List<String> res = new ArrayList<String>();
        for (int i = 0; i != TRYTIMES; i++) {
            res = getResultList(name, false);
            if (!res.isEmpty()) {
                break;
            }
        }

        if (res.isEmpty()) {
            logger.error(String.format("Error with bns: %s", name));
        }
        return res;
    }

    /**
     * 获取name对应的所有Host+端口List
     * 
     * @param name 远程接口在bns中配置的serverName
     * */
    public static List<String> getHostPortList(String name) {
        List<String> res = new ArrayList<String>();
        for (int i = 0; i != TRYTIMES; i++) {
            res = getResultList(name, true);
            if (!res.isEmpty()) {
                break;
            }
        }

        if (res.isEmpty()) {
            logger.error(String.format("Error with bns: %s", name));
        }
        return res;
    }

    public static String getIpPortString(String name) {
        String res = new String();
        for (int i = 0; i != TRYTIMES; i++) {
            res = getResultString(name, false);
            if (!res.isEmpty()) {
                break;
            }
        }

        if (res.isEmpty()) {
            logger.error(String.format("Error with bns: %s", name));
        }
        return res;
    }

    public static String getHostPortString(String name) {
        String res = new String();
        for (int i = 0; i != TRYTIMES; i++) {
            res = getResultString(name, true);
            if (!res.isEmpty()) {
                break;
            }
        }

        if (res.isEmpty()) {
            logger.error(String.format("Error with bns: %s", name));
        }
        return res;
    }

    private static List<String> getResultList(String name, boolean isHost) {
        BNSClient bnsClient = new BNSClient();
        List<String> result = new ArrayList<String>();
        try {
            List<BNSInstance> instanceList = (List<BNSInstance>) (bnsClient.getInstanceByService(name, 3000));
            if (CollectionUtils.isNotEmpty(instanceList)) {
                for (BNSInstance instance : instanceList) {
                    if (isHost) {
                        result.add(instance.getHostName() + COLON + instance.getPort());
                    } else {
                        result.add(instance.getDottedIP() + COLON + instance.getPort());
                    }
                }
            }
        } catch (BNSException e) {
            logger.error("Encounter an error while using BNS:" + e.getMessage(), e);
        }
        return result;
    }

    private static String getResultString(String name, boolean isHost) {
        BNSClient bnsClient = new BNSClient();
        StringBuffer sb = new StringBuffer();
        try {
            List<BNSInstance> instanceList = (List<BNSInstance>) (bnsClient.getInstanceByService(name, 3000));
            if (CollectionUtils.isEmpty(instanceList)) {
                return StringUtils.EMPTY;
            }

            for (int i = 0; i < instanceList.size(); i++) {
                BNSInstance instance = instanceList.get(i);
                if (isHost) {
                    sb.append(instance.getHostName());
                } else {
                    sb.append(instance.getDottedIP());
                }
                sb.append(COLON);
                sb.append(instance.getPort());
                if (i != instanceList.size() - 1) {
                    sb.append(SEPERATOR);
                }
            }

        } catch (BNSException e) {
            logger.error("Encounter an error while using BNS:" + e.getMessage(), e);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String SMART_IDEA_SERVER_NAME = "api.beidou.all";
        List<String> res = BnsUtils.getIpPortList(SMART_IDEA_SERVER_NAME);
        System.out.println(res.toString());
        
        System.out.println(BnsUtils.getHostPortList(SMART_IDEA_SERVER_NAME));
    }

}
