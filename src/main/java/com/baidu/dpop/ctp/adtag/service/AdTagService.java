/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.adtag.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.vo.AdTagGetVo;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;
import com.baidu.dpop.ctp.user.bo.User;

public interface AdTagService extends GenericMapperService<AdTag, Long> {

    /**
     * 通过某group的主键id获取此group所包含的所有标注信息
     * 
     * @param groupId 需要获取的group的主键id
     * @return 获取到的标注信息列表
     */
    public List<AdTag> getByGroup(Long groupId);

    /**
     * 根据refId获取某个标注结果
     * 
     * @param refId
     * @param dataType
     * @return 获取到的结果
     */
    public AdTag getByRefId(Long refId, Number dataType);

    /**
     * 批量获取adTag
     * 
     * @param vo 获取参数，包括关联ad主键列表与数据类型
     * @return 获取到的列表
     */
    public List<AdTag> batchGet(AdTagGetVo vo);
    
    /**
     * 选取所有给定数据类型，并且ref_id在指定集合中的数据
     * 
     * @param list 给定的red_id列表
     * @param dataType 数据类型
     * @return 集合形式的ref_id列表
     */
    public Set<Long> getTestedAdTagByRefId(List<Long> list, Byte dataType);

    /**
     * 批量获取创意对应的标注信息Map
     * 
     * @param refAdIdList 标注明细主键List
     * @Param dataType 产品线类型
     * */
    public Map<Long, AdTag> getAdTagMap(List<Long> refAdIdList, Integer dataType);

    /**
     * 获取符合条件的adTag
     * 
     * @param vo 查询条件
     * @return 符合条件的adTag列表
     */
    public Map<Long, AdTag> getMapedTagedtags(GeneralTaskQueryVo vo);

    /**
     * 获取符合条件的adTag
     * 
     * @param vo 查询条件
     * @return 符合条件的adTag列表
     */
    public List<AdTag> getTagedtags(GeneralTaskQueryVo vo);

    /**
     * 根据taskId选择出此task下所有的adTag值, 分页获取
     * 
     * @param taskId 需要获取的taskId
     * @param page 分页参数
     * @param size 分页参数
     * @return 选择结果
     */
    public List<String> getAdTag(Integer taskId, Integer page, Integer size);

    /**
     * 根据条件统计ad数量
     * 
     * @param vo 查询条件
     * @return 查询到的符合条件的ad数量
     */
    public Integer getTagedCount(GeneralTaskQueryVo vo);

    /**
     * 提交任务
     * 
     * @param list 提交的具体数据
     * @param user 提交的用户
     * @param time 提交时间
     */
    public void submit(List<AdTag> list, User user, Date time);

    /**
     * 在向上游入库成功后调用 更新数据库，将已入库的标志位置为1
     * 
     * @param taskId 需要提交的taskId
     * @param dataType 需要提交的数据类型
     * @param ids adId列表
     */
    public void assignedAdTags(Integer taskId, Integer dataType, List<Long> ids);

    /**
     * 删除某一task下的所有数据
     * 
     * @param taskId 指定的task_id
     * */
    public void deleteAdDetail(Integer taskId);
}
