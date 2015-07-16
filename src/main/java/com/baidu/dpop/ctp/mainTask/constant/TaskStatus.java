package com.baidu.dpop.ctp.mainTask.constant;

import org.springframework.util.Assert;

public enum TaskStatus {

    NOT_STARTED(Byte.valueOf("0"), "任务未开始"), STARTED(Byte.valueOf("1"), "任务进行中"), FINISHED(Byte.valueOf("2"), "任务已完成"),
    CLOSED_1(Byte.valueOf("10"), "任务已关闭-从未开始关闭"), CLOSED_2(Byte.valueOf("11"), "任务已关闭-从进行中关闭"), CLOSED_3(Byte
            .valueOf("12"), "任务已关闭-从已完成关闭"), CANTOPEN_1(Byte.valueOf("110"), "任务不能打开-从进行中"), CANTOPEN_2(Byte
            .valueOf("111"), "任务不能打开-从进行中关闭"), CANTOPEN_3(Byte.valueOf("112"), "任务已关闭-从进行中关闭"), DELETED(Byte
            .valueOf("-1"), "已删除");

    private Byte id;
    private String desc;

    public static Byte closedToCantOpen(Byte status) {
        Assert.state(isCLOSED(status));
        return (byte) (status + 100);
    }

    public static Byte cantOpenToClosed(Byte status) {
        Assert.state(isCANTOPEN(status));
        return (byte) (status - 100);
    }

    public static Byte notClosedToClosed(Byte status) {
        Assert.state(isNOTCLOSED(status));
        return (byte) (status + 10);
    }

    public static Byte closedToNotClosed(Byte status) {
        Assert.state(isCLOSED(status));
        return (byte) (status - 10);
    }

    public static boolean isNOTCLOSED(Number id) {
        if (id.intValue() == NOT_STARTED.id.intValue()) {
            return true;
        } else if (id.intValue() == STARTED.id.intValue()) {
            return true;
        } else if (id.intValue() == FINISHED.id.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isCLOSED(Number id) {
        if (id.intValue() == CLOSED_1.id.intValue()) {
            return true;
        } else if (id.intValue() == CLOSED_2.id.intValue()) {
            return true;
        } else if (id.intValue() == CLOSED_3.id.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isCANTOPEN(Number id) {
        if (id.intValue() == CANTOPEN_1.id.intValue()) {
            return true;
        } else if (id.intValue() == CANTOPEN_2.id.intValue()) {
            return true;
        } else if (id.intValue() == CANTOPEN_3.id.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isNOTSTARTED(Number id) {
        return id.intValue() == NOT_STARTED.id.intValue();
    }

    public static boolean isDELETED(Number id) {
        return id.intValue() == DELETED.id.intValue();
    }

    private TaskStatus(Byte id, String desc) {
        this.id = id;
        this.desc = desc;
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

    public static boolean canConfirm(Byte id) {
        if (id.equals(STARTED.getId()) || id.equals(FINISHED.getId())) {
            return true;
        }
        return false;
    }
}
