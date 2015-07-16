package com.baidu.dpop.ctp.group.constant;

import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;

/**
 * Group状态枚举，包含"未开始", "进行中", "已完成"三种状态
 * 
 * @author mading01
 * 
 */
public enum GroupStatus {

    NOT_STARTED(Byte.valueOf("0"), "任务未开始"), STARTED(Byte.valueOf("1"), "任务进行中"), FINISHED(
            Byte.valueOf("2"), "任务已完成");

    private Byte id;
    private String desc;

    private GroupStatus(Byte id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static boolean isFinished(Number id) {
        return id.intValue() == FINISHED.id.intValue();
    }

    public static TaskStatus get(Number id) {
        if (null == id) {
            return null;
        }
        for (TaskStatus temp : TaskStatus.values()) {
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
