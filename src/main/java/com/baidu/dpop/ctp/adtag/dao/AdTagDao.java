/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.adtag.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.vo.AdTagAssignedVo;
import com.baidu.dpop.ctp.adtag.vo.AdTagGetVo;
import com.baidu.dpop.ctp.adtag.vo.AdTagSubmitVo;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;

public interface AdTagDao extends GenericMapperDao<AdTag, Long> {

    // SELECT--------------------------------------------------------

    /**
     * 通过某group的主键id获取此group所包含的所有标注信息
     * 
     * @param groupId
     *            需要获取的group的主键id
     * @return 获取到的标注信息列表
     */
    public List<AdTag> selectByGroup(Long groupId);

    /**
     * 根据refId获取某个标注结果
     * 
     * @param vo
     *            获取条件，包含refId与dataType
     * @return 获取到的结果
     */
    public AdTag selectByRefId(AdTagGetVo vo);

    /**
     * 批量获取adTag
     * 
     * @param vo
     *            获取参数，包括关联ad主键列表与数据类型
     * @return 获取到的列表
     */
    public List<AdTag> batchSelect(AdTagGetVo vo);
    
    /**
     * 选取所有给定数据类型，并且ref_id在指定集合中的数据
     * 
     * @param list 给定的red_id列表
     * @param dataType 数据类型
     * @return 集合形式的ref_id列表
     */
    public Set<Long> selectTestedAdTagByRefId(List<Long> list, Byte dataType);

    /**
     * 获取符合条件的adTag
     * 
     * @param vo
     *            查询条件
     * @return 符合条件的adTag列表
     */
    public List<AdTag> selectTagedtags(GeneralTaskQueryVo vo);
    
    /**
     * 根据taskId选择出此task下所有的adTag值, 分页获取
     * 
     * @param taskId 需要获取的taskId
     * @param page 分页参数
     * @param size 分页参数
     * @return 选择结果
     */
    public List<String> selectAdTag(@Param("taskId") Integer taskId, @Param("page") Integer page,
            @Param("size") Integer size);


    /**
     * 根据条件统计ad数量
     * 
     * @param vo
     *            查询条件
     * @return 查询到的符合条件的ad数量
     */
    public Integer selectTagedCount(GeneralTaskQueryVo vo);

    // INSERT--------------------------------------------------------

    /**
     * 批量插入AdTag
     * 
     * @param list
     *            需要插入的数据
     * @return 实际插入的条目数
     */
    public int batchInsert(List<AdTag> list);

    // UPDATE--------------------------------------------------------

    /**
     * 记录是否入库成功
     * 
     * @param vo
     *            更新条件，主要包含task_id，dataType，更新的id列表
     */
    public void updateAssigned(AdTagAssignedVo vo);

    /**
     * 批量更新AdTag
     * 
     * @param vo
     *            更新条件
     */
    public void batchUpdate(AdTagSubmitVo vo);

    // DELETE--------------------------------------------------------

    /**
     * 删除某一task下的所有数据
     * 
     * @param taskId
     *            指定的task_id
     * */
    public void deleteAdDetail(Integer taskId);
}
