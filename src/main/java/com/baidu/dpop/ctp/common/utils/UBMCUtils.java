package com.baidu.dpop.ctp.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.mainTask.constant.BeidouMcType;
import com.baidu.dpop.ctp.mainTask.constant.DSPMcType;
import com.baidu.dpop.ctp.mainTask.constant.GeneralMcType;
import com.baidu.dpop.ctp.mainTask.constant.UBMCAppId;
import com.baidu.dpop.ctp.mainTask.service.UBMCService;
import com.baidu.dpop.ctp.mainTask.vo.IdeaInfo;
import com.baidu.dpop.ctp.task.vo.PresentedTaskDetail;

public class UBMCUtils {

	private static final Logger LOG = Logger.getLogger(UBMCUtils.class);

	public static boolean needFlash(Number wuliaoType, Number dataType) {
		if (GroupDataType.isBeidou(dataType)) {
			return BeidouMcType.needFlash(wuliaoType);
		} else if (GroupDataType.isDSP(dataType)) {
			return DSPMcType.needFlash(wuliaoType);
		}
		return false;
	}

	public static boolean needSmartUrl(Number wuliaoType, Number dataType) {
		if (GroupDataType.isBeidou(dataType)) {
			return BeidouMcType.needSmartUrl(wuliaoType);
		}
		return false;
	}

	public static Integer getAppId(Number dataType) {
		if (GroupDataType.isBeidou(dataType)) {
			return UBMCAppId.BEIDOU.getId();
		} else if (GroupDataType.isQiushi(dataType)) {
			return UBMCAppId.QIUSHI.getId();
		} else if (GroupDataType.isDSP(dataType)) {
			return UBMCAppId.DSP.getId();
		}
		return -1;
	}

	public static void setUBMCImgUrl(List<PresentedTaskDetail> list,
			UBMCService service, Number dataType) {

		Map<Long, PresentedTaskDetail> tasks = new HashMap<Long, PresentedTaskDetail>();
		List<IdeaInfo> ideaList = new ArrayList<IdeaInfo>();
		for (PresentedTaskDetail gTask : list) {
			tasks.put(gTask.getAdId().longValue(), gTask);
			if (GeneralMcType.needUrl(gTask.getWuliaoType())) {
				IdeaInfo idfo = new IdeaInfo();
				idfo.setMcId(gTask.getMcId().intValue());
				idfo.setMcVersionId(gTask.getMcVersionId().intValue());
				idfo.setMcType(gTask.getWuliaoType().intValue());
				idfo.setAdId(gTask.getAdId().longValue());
				ideaList.add(idfo);
			}

			if (needSmartUrl(gTask.getWuliaoType().intValue(), dataType)) {
				gTask.setImgUrl("ctp/unitinfo/getPagedTasks.do?userId="
						+ gTask.getUserId() + "&unitId=" + gTask.getAdId());
			}
		}

		try {
			Integer appId = UBMCAppId.BEIDOU.getId();
			if (GroupDataType.isBeidou(dataType)) {
				appId = UBMCAppId.BEIDOU.getId();
			} else if (GroupDataType.isQiushi(dataType)) {
				appId = UBMCAppId.QIUSHI.getId();
			} else if (GroupDataType.isDSP(dataType)) {
				appId = UBMCAppId.DSP.getId();
			}
			if (!CollectionUtils.isEmpty(ideaList)) {
				ideaList = service.getIdeasInfoFromUBMC(appId, ideaList);
				for (IdeaInfo idfo : ideaList) {
					PresentedTaskDetail gTask = tasks.get(idfo.getAdId());
					gTask.setImgUrl(idfo.getUrl());
				}
			}
		} catch (Exception e) {
			// 这里暂时吃掉了这个错误，因为目前数据库中数据有误，因此无法正常显示，一定会报错
			// 为了正常显示页面，这里暂时不处理此错误，仅仅将Url设置为null
			LOG.error("获取UBMC数据失败，原因:" + e.getLocalizedMessage(), e);
		}

	}

	public static void setUBMCImgUrl(PresentedTaskDetail task, UBMCService service,
			Number dataType) {
		if (GroupDataType.isNewDsp(dataType)) {
			// newdsp不在这里处理
			return;
		}
		
		if (GeneralMcType.needUrl(task.getWuliaoType())) {
			List<IdeaInfo> ideaList = new ArrayList<IdeaInfo>();
			IdeaInfo idfo = new IdeaInfo();
			idfo.setMcId(task.getMcId().intValue());
			idfo.setMcVersionId(task.getMcVersionId().intValue());
			idfo.setMcType(task.getWuliaoType().intValue());
			idfo.setAdId(task.getAdId().longValue());
			ideaList.add(idfo);

			Integer appId = UBMCAppId.BEIDOU.getId();
			if (GroupDataType.isBeidou(dataType)) {
				appId = UBMCAppId.BEIDOU.getId();
			} else if (GroupDataType.isQiushi(dataType)) {
				appId = UBMCAppId.QIUSHI.getId();
			} else if (GroupDataType.isDSP(dataType)) {
				appId = UBMCAppId.DSP.getId();
			}

			ideaList = service.getIdeasInfoFromUBMC(appId, ideaList);
			task.setImgUrl(ideaList.get(0).getUrl());
		}

		if (needFlash(task.getWuliaoType().intValue(), dataType)) {
			task.setFlash(service.getFlashIdeasInfo(task.getMcId().intValue(),
					task.getMcVersionId().intValue()));
		}

		if (needSmartUrl(task.getWuliaoType().intValue(), dataType)) {
			task.setImgUrl("ctp/unitinfo/getPagedTasks.do?userId="
					+ task.getUserId() + "&unitId=" + task.getAdId());
		}
	}

}
