package com.baidu.dpop.ctp.common.rpc.service;

import java.util.List;

import com.baidu.dpop.ctp.outerinvoke.vo.CreativityTagResponse;
import com.baidu.dpop.ctp.outerinvoke.vo.CreativityTagResult;
import com.baidu.dpop.ctp.task.vo.CreativityInfo;

public interface NewDSPRpcService {
	
	public CreativityTagResponse<CreativityInfo> getCreativityInfo(List<Long> list);
	
	public CreativityTagResponse<Long> updateCreativityTag(List<CreativityTagResult> list);

}
