/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.mainTask.bo;

import com.baidu.dpop.ctp.adtag.utils.TaskTypeUtils;

/**
 * 标注任务实体类
 * 
 * @author mading01
 * 
 */
public class Task extends TaskBase {

    private static final long serialVersionUID = -5883952277639500241L;

    public String taskTypeName;

    @Override
    public void setTaskType(Byte taskType) {
        super.setTaskType(taskType);
        setTaskTypeName(TaskTypeUtils.getType(getTaskType()).getName());
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

}