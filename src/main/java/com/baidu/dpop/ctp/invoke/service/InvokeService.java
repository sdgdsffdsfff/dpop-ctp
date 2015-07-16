package com.baidu.dpop.ctp.invoke.service;

import java.util.List;

import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;

public interface InvokeService {

	/**
	 * 上传文件，解析并将数据录入数据库中
	 * 
	 * @param file
	 *            上传的文件，类型为MultipartFile
	 * @return Map<String, Object>，至少应该包含成功添加的数据条目数与失败的adid列表
	 */
    public int uploadFile(List<GeneralTask> fileData, Byte dataType, List<String> elist);
	
	/**
	 * 获取所有已标注数据
	 * @param vo 查询条件
	 * @return 生成的压缩文件地址
	 */
	public String getTagedFile(GeneralTaskQueryVo vo);
}
