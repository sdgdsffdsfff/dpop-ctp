
/*
* Copyright 2014 baidu dpop
* All right reserved.
*
*/

package com.baidu.dpop.ctp.task.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.account.bo.AccountInfo;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.vo.DistributeGroupResult;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.invoke.vo.TaskTestVo;
import com.baidu.dpop.ctp.task.bo.NewDSPTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;


public interface NewDSPTaskService extends GenericMapperService<NewDSPTask,Long> {
	
	/**
	 * 获取某一group下所有的NewDSP任务
	 * 
	 * @param g
	 *            所需获取的group
	 * @param resultMap
	 *            用以填充结果的数据集
	 * @return
	 */
	public DistributeGroupResult getNewDSPTasksByGroup(Group g);

	/**
	 * 获取某任务的详细信息
	 * 
	 * @param id
	 *            任务id
	 * @param region
	 *            地域
	 * @return
	 */
	public NewDSPTask getNewDSPTaskById(Long id, Integer region);
	
	/**
	 * 获取某task下的全集数据
	 * 
	 * @param taskId
	 *            需要获取的taskId
	 * @param fileNames
	 *            文件名列表，生成的文件路径会加入此列表
	 * @param date
	 *            代表下载日期的时间字符串
	 * @return fileNames
	 */
	public List<String> getFullTaskInfoByTaskId(Integer taskId,
			List<String> fileNames, String date);

	/**
	 * 获取数据库中的重复数据
	 * 
	 * @param list
	 *            需要获取的list
	 * @return ad_id与task_id的组合
	 */
	public List<TaskTestVo> getTestedTasks(List<GeneralTask> list);
	
	/**
	 * 批量获取NewDSP任务
	 * 
	 * @param list
	 *            需要获取的id列表
	 * @return 获取到的NewDSP任务
	 */
	public List<NewDSPTask> batchGet(List<Long> list);

	/**
	 * 批量统计所有给定group_id的所有Group的adNum
	 * 
	 * @param groupIds
	 *            需要统计的group_id
	 * @return Group实例列表，只有id与adNum两项有数值
	 */
	public List<Group> getTaskCount(List<GroupCountVo> groupIds);

	/**
	 * 批量插入数据
	 * 
	 * @param tasks
	 *            列表形式的数据内容
	 * @return 成功插入的条目数
	 */
	public int batchInsertTasks(@Param("tasks") List<NewDSPTask> tasks);
	
	/**
     * 根据任务id更新new dsp task的公司信息，包括company及website
     * 
     * @param list 更新信息列表
     * @param taskId 需要更新的task
     */
	public void updateCompanyInfo(List<AccountInfo> list, List<Integer> taskIds);
	
	/**
	 * 删除某一task下的所有数据
	 * 
	 * @param taskId
	 *            指定的task_id
	 * */
	public void deleteAdDetail(Integer taskId);

}
