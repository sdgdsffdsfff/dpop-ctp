package com.baidu.dpop.ctp.review.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.common.constants.TagConstant;
import com.baidu.dpop.ctp.common.utils.WuliaoTypeUtils;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.bo.ReviewTask;
import com.baidu.dpop.ctp.review.constants.ReviewTaskType;
import com.baidu.dpop.ctp.review.constants.ReviewTaskStatus;
import com.baidu.dpop.ctp.review.constants.ReviewType;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.review.service.ReviewGroupService;
import com.baidu.dpop.ctp.review.service.ReviewService;
import com.baidu.dpop.ctp.review.service.ReviewTaskService;
import com.baidu.dpop.ctp.review.vo.QueryConditionVo;
import com.baidu.dpop.ctp.review.vo.ReviewInfoItem;
import com.baidu.dpop.ctp.review.vo.ReviewPageInfoVo;
import com.baidu.dpop.ctp.review.vo.ReviewTagStatisticsVo;
import com.baidu.dpop.ctp.review.vo.ReviewTaskAccuracyVo;
import com.baidu.dpop.ctp.review.vo.ReviewTaskBasicInfoVo;
import com.baidu.dpop.ctp.review.vo.WrongAdDetailVo;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.service.UserService;

/**
 * 审核相关service
 * 
 * @author cgd
 * @date 2015年3月29日 下午12:26:58
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewTaskService reviewTaskService;

    @Autowired
    private ReviewGroupService reviewGroupService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewAdTaskService reviewAdTaskService;

    @Override
    public ReviewTaskAccuracyVo getReviewTaskAccuracy(Integer reviewTaskId) {
        Assert.notNull(reviewTaskId);

        ReviewTaskAccuracyVo ret = new ReviewTaskAccuracyVo();

        // 获取所有审核已完成的记录明细
        List<WrongAdDetailVo> adDetailList = this.reviewGroupService.getReviewedAdDetail(reviewTaskId, 3);

        // 无明细
        if (CollectionUtils.isEmpty(adDetailList)) {
            ret.setDefaultValue(0.0);
        } else {
            // 所有已审核ad总数
            Integer totalCount = adDetailList.size();
            // 标错的AD Count
            Integer wrongCount = 0;
            // 图文&非图文创意标注准确率
            Integer textAdCount = 0;
            Integer wrongTextAdCount = 0;
            Integer otherAdCount = 0;
            Integer wrongOtherAdCount = 0;
            // 行业 & tag 标注准确率
            Integer tradeWrongCount = 0;
            Integer beautyTagWrongCount = 0;
            Integer cheatTagWrongCount = 0;
            Integer vulgarTagWrongCount = 0;
            Integer highDangerTagWrongCount = 0;

            for (WrongAdDetailVo item : adDetailList) {
                // 是否行业标注错误
                boolean isWrongTrade = false;
                if (!item.getTradeLevel3().equals(item.getReviewTradeLevel3())) {
                    isWrongTrade = true;
                }
                // 是否tag标注错误
                boolean isWrongTag = false;
                if (!item.getAdTag().equals(item.getReivewAdTag())) {
                    isWrongTag = true;
                }

                // 是否标注错误
                boolean isError = false;
                if (isWrongTrade || isWrongTag) {
                    isError = true;
                }

                // 是否是图文Ad
                boolean isTextAndPictureAd = false;
                if (WuliaoTypeUtils.isTextAndPictureAd(item.getDataType(), Integer.valueOf(item.getWuliaoType()))) {
                    isTextAndPictureAd = true;
                }

                // 标注错误
                if (isError) {
                    wrongCount++;
                }
                // 图文 & 标注错误
                if (isTextAndPictureAd) {
                    textAdCount++;
                    if (isError) {
                        wrongTextAdCount++;
                    }
                } else {
                    otherAdCount++;
                    if (isError) {
                        wrongOtherAdCount++;
                    }
                }

                // 行业标注错误
                if (isWrongTrade) {
                    tradeWrongCount++;
                }

                // 创意Data类型（北斗 or 秋实 or DSP）
                Byte dataType = item.getDataType();
                Map<String, Integer> tagInfo = new HashMap<String, Integer>();// AdTagUtils.getTagInfo(item.getAdTag(),
                                                                              // dataType);
                Map<String, Integer> reviewTagInfo = new HashMap<String, Integer>(); // AdTagUtils.getTagInfo(item.getReivewAdTag(),
                                                                                     // dataType);

                // 美观度对比
                Integer beautyValue = tagInfo.get(TagConstant.BEAUTY);
                Integer reviewBeautyValue = reviewTagInfo.get(TagConstant.BEAUTY);
                if (beautyValue != null && reviewBeautyValue != null && !beautyValue.equals(reviewBeautyValue)) {
                    beautyTagWrongCount++;
                }

                // 欺诈度对比
                Integer cheatValue = tagInfo.get(TagConstant.CHEAT);
                Integer reviewCheatValue = reviewTagInfo.get(TagConstant.CHEAT);
                if (cheatValue != null && reviewCheatValue != null && !cheatValue.equals(reviewCheatValue)) {
                    cheatTagWrongCount++;
                }

                // 低俗度对比
                Integer vulagrValue = tagInfo.get(TagConstant.VULGAR);
                Integer reviewVulgarValue = reviewTagInfo.get(TagConstant.VULGAR);
                if (vulagrValue != null && reviewVulgarValue != null && !vulagrValue.equals(reviewVulgarValue)) {
                    vulgarTagWrongCount++;
                }

                // 高危度对比
                Integer highDangerValue = tagInfo.get(TagConstant.HIGH_DANGER);
                Integer reviewHighDangerValue = reviewTagInfo.get(TagConstant.HIGH_DANGER);
                if (highDangerValue != null && reviewHighDangerValue != null
                        && !highDangerValue.equals(reviewHighDangerValue)) {
                    highDangerTagWrongCount++;
                }

            }

            // 标注准确率
            Double taskAccuracy = (totalCount - wrongCount) / (totalCount * 1.0);
            ret.setTaskAccuracy(taskAccuracy);

            // 图文 & 非图文创意标注准确率
            Double textAdAccuracy = 0.0;
            if (textAdCount > 0) {
                textAdAccuracy = (textAdCount - wrongTextAdCount) / (textAdCount * 1.0);
            }
            ret.setTextAdAccuracy(textAdAccuracy);
            Double otherAdAccuracy = 0.0;
            if (otherAdCount > 0) {
                otherAdAccuracy = (otherAdCount - wrongOtherAdCount) / (otherAdCount * 1.0);
            }
            ret.setOtherAdAccuracy(otherAdAccuracy);

            // 其他创意标注准确率

            // 行业 & tag 标注准确率
            ret.setTradeAccuracy((totalCount - tradeWrongCount) / (totalCount * 1.0));
            ret.setBeautyAccuracy((totalCount - beautyTagWrongCount) / (totalCount * 1.0));
            ret.setCheatAccuracy((totalCount - cheatTagWrongCount) / (totalCount * 1.0));
            ret.setVulgarAccuracy((totalCount - vulgarTagWrongCount) / (totalCount * 1.0));
            ret.setHighDangerAccuracy((totalCount - highDangerTagWrongCount) / (totalCount * 1.0));
        }

        // format (保留小数点后两位)
        this.formatAccuracy(ret);
        return ret;
    }

    /** format (保留小数点后两位) **/
    private void formatAccuracy(ReviewTaskAccuracyVo vo) {

        BigDecimal bd = new BigDecimal(vo.getTaskAccuracy() * 100);
        vo.setTaskAccuracy(bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        bd = new BigDecimal(vo.getTextAdAccuracy() * 100);
        vo.setTextAdAccuracy(bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bd = new BigDecimal(vo.getOtherAdAccuracy() * 100);
        vo.setOtherAdAccuracy(bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        bd = new BigDecimal(vo.getTradeAccuracy() * 100);
        vo.setTradeAccuracy(bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        bd = new BigDecimal(vo.getBeautyAccuracy() * 100);
        vo.setBeautyAccuracy(bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        bd = new BigDecimal(vo.getVulgarAccuracy() * 100);
        vo.setVulgarAccuracy(bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        bd = new BigDecimal(vo.getCheatAccuracy() * 100);
        vo.setCheatAccuracy(bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        bd = new BigDecimal(vo.getHighDangerAccuracy() * 100);
        vo.setHighDangerAccuracy(bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    @Override
    public ReviewTagStatisticsVo getReviewTagAccuracy(Integer reviewTaskId) {
        Assert.notNull(reviewTaskId);

        ReviewTagStatisticsVo ret = new ReviewTagStatisticsVo();
        ret.setDefaultValue(0);

        // 获取全部审核的Ad Detail
        List<WrongAdDetailVo> allAdDetailList = this.reviewGroupService.getReviewedAdDetail(reviewTaskId, 3);
        if (CollectionUtils.isNotEmpty(allAdDetailList)) {
            // 统计标注&审核各个tag数据
            this.countTag(ret, allAdDetailList);
        }

        return ret;
    }

    /**
     * 统计各个Tag对应的维度的数量
     * */
    private void countTag(ReviewTagStatisticsVo vo, List<WrongAdDetailVo> allAdDetailList) {
        Assert.notNull(vo);
        Assert.notEmpty(allAdDetailList);

        // 美观度统计信息（高中低）
        Integer tagHighBeautyNum = 0;
        Integer tagNormalBeautyNum = 0;
        Integer tagLowBeautyNum = 0;
        Integer reviewHighBeautyNum = 0;
        Integer reviewNormalBeautyNum = 0;
        Integer reviewLowBeautyNum = 0;

        // 低俗度（黑灰白）
        Integer tagBlackVulgarNum = 0;
        Integer tagGrayVulagrNum = 0;
        Integer tagWhiteVulgarNum = 0;
        Integer reviewBlackVulgarNum = 0;
        Integer reviewGrayVulagrNum = 0;
        Integer reviewWhiteVulgarNum = 0;

        // 欺诈度（是否）
        Integer tagIsCheatNum = 0;
        Integer tagNotCheatNum = 0;
        Integer reviewIsCheatNum = 0;
        Integer reviewNotCheatNum = 0;

        // 高危度（是否）
        Integer tagIsHighDangerNum = 0;
        Integer tagNotHighDangerNum = 0;
        Integer reviewIsHighDangerNum = 0;
        Integer reviewNotHighDangerNum = 0;

        // 遍历统计数量
        for (WrongAdDetailVo item : allAdDetailList) {
            // 创意Data类型（北斗 or 秋实 or DSP）
            Byte dataType = item.getDataType();
            Map<String, Integer> tagInfo = new HashMap<String, Integer>(); // AdTagUtils.getTagInfo(item.getAdTag(),
                                                                           // dataType);
            Map<String, Integer> reviewTagInfo = new HashMap<String, Integer>();// AdTagUtils.getTagInfo(item.getReivewAdTag(),
                                                                                // dataType);

            // 美观度
            Integer beautyValue = tagInfo.get(TagConstant.BEAUTY);
            Integer reviewBeautyValue = reviewTagInfo.get(TagConstant.BEAUTY);
            if (beautyValue != null) {
                if (TagConstant.LOW.equals(beautyValue)) {
                    tagLowBeautyNum++;
                } else if (TagConstant.NORMAL.equals(beautyValue)) {
                    tagNormalBeautyNum++;
                } else if (TagConstant.HIGH.equals(beautyValue)) {
                    tagHighBeautyNum++;
                }
            }
            if (reviewBeautyValue != null) {
                if (TagConstant.LOW.equals(reviewBeautyValue)) {
                    reviewLowBeautyNum++;
                } else if (TagConstant.NORMAL.equals(reviewBeautyValue)) {
                    reviewNormalBeautyNum++;
                } else if (TagConstant.HIGH.equals(reviewBeautyValue)) {
                    reviewHighBeautyNum++;
                }
            }

            // 欺诈度
            Integer cheatValue = tagInfo.get(TagConstant.CHEAT);
            Integer reviewCheatValue = reviewTagInfo.get(TagConstant.CHEAT);
            if (cheatValue != null) {
                if (TagConstant.YES.equals(cheatValue)) {
                    tagIsCheatNum++;
                } else if (TagConstant.NO.equals(cheatValue)) {
                    tagNotCheatNum++;
                }
            }
            if (reviewCheatValue != null) {
                if (TagConstant.YES.equals(reviewCheatValue)) {
                    reviewIsCheatNum++;
                } else if (TagConstant.NO.equals(reviewCheatValue)) {
                    reviewNotCheatNum++;
                }
            }

            // 低俗度
            Integer vulagrValue = tagInfo.get(TagConstant.VULGAR);
            Integer reviewVulgarValue = reviewTagInfo.get(TagConstant.VULGAR);
            if (vulagrValue != null) {
                if (TagConstant.BLACK.equals(vulagrValue)) {
                    tagBlackVulgarNum++;
                } else if (TagConstant.GRAY.equals(vulagrValue)) {
                    tagGrayVulagrNum++;
                } else if (TagConstant.WHITE.equals(vulagrValue)) {
                    tagWhiteVulgarNum++;
                }
            }
            if (reviewVulgarValue != null) {
                if (TagConstant.BLACK.equals(reviewVulgarValue)) {
                    reviewBlackVulgarNum++;
                } else if (TagConstant.GRAY.equals(reviewVulgarValue)) {
                    reviewGrayVulagrNum++;
                } else if (TagConstant.WHITE.equals(reviewVulgarValue)) {
                    reviewWhiteVulgarNum++;
                }
            }

            // 高危度对比
            Integer highDangerValue = tagInfo.get(TagConstant.HIGH_DANGER);
            Integer reviewHighDangerValue = reviewTagInfo.get(TagConstant.HIGH_DANGER);
            if (highDangerValue != null) {
                if (TagConstant.YES.equals(highDangerValue)) {
                    tagIsHighDangerNum++;
                } else if (TagConstant.NO.equals(highDangerValue)) {
                    tagNotHighDangerNum++;
                }
            }
            if (reviewHighDangerValue != null) {
                if (TagConstant.YES.equals(reviewHighDangerValue)) {
                    reviewIsHighDangerNum++;
                } else if (TagConstant.NO.equals(reviewHighDangerValue)) {
                    reviewNotHighDangerNum++;
                }
            }
        }

        vo.setTagLowBeautyNum(tagLowBeautyNum);
        vo.setTagNormalBeautyNum(tagNormalBeautyNum);
        vo.setTagHighBeautyNum(tagHighBeautyNum);
        vo.setReviewLowBeautyNum(reviewLowBeautyNum);
        vo.setReviewNormalBeautyNum(reviewNormalBeautyNum);
        vo.setReviewHighBeautyNum(reviewHighBeautyNum);

        vo.setTagBlackVulgarNum(tagBlackVulgarNum);
        vo.setTagGrayVulagrNum(tagGrayVulagrNum);
        vo.setTagWhiteVulgarNum(tagWhiteVulgarNum);
        vo.setReviewBlackVulgarNum(reviewBlackVulgarNum);
        vo.setReviewGrayVulagrNum(reviewGrayVulagrNum);
        vo.setReviewWhiteVulgarNum(reviewWhiteVulgarNum);

        vo.setTagIsCheatNum(tagIsCheatNum);
        vo.setTagNotCheatNum(tagNotCheatNum);
        vo.setReviewIsCheatNum(reviewIsCheatNum);
        vo.setReviewNotCheatNum(reviewNotCheatNum);

        vo.setTagIsHighDangerNum(tagIsHighDangerNum);
        vo.setTagNotHighDangerNum(tagNotHighDangerNum);
        vo.setReviewIsHighDangerNum(reviewIsHighDangerNum);
        vo.setReviewNotHighDangerNum(reviewNotHighDangerNum);

    }

    @Override
    public ReviewTaskBasicInfoVo getBasicInfo(Integer reviewTaskId) {
        Assert.notNull(reviewTaskId);
        ReviewTaskBasicInfoVo ret = new ReviewTaskBasicInfoVo();

        // 审核任务基本信息
        ReviewTask taskInfo = this.reviewTaskService.findById(Long.valueOf(reviewTaskId));
        if (taskInfo == null) {
            throw new RuntimeException("Cannot find Review Task by ID: " + reviewTaskId);
        }

        ret.setReviewTaskId(reviewTaskId);
        ret.setReviewTaskName(taskInfo.getTaskName());
        ret.setAddUser(taskInfo.getAddUser());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        ret.setAddTime(sdf.format(taskInfo.getAddTime()));
        ret.setTaskStatus(taskInfo.getStatus());
        ret.setTaskStatusDesc(ReviewTaskStatus.get(taskInfo.getStatus()).getDesc());
        ret.setTaskType(taskInfo.getModuserLevel());
        ret.setTaskTypeDesc(ReviewTaskType.get(taskInfo.getModuserLevel()).getDesc());
        if (taskInfo.getBlind()) {
            ret.setReviewTypeDesc(ReviewType.NO_BLIND_REVIEW.getDesc());
        } else {
            ret.setReviewTypeDesc(ReviewType.BLIND_REVIEW.getDesc());
        }

        // 审核进度信息
        String taskList = taskInfo.getTaskList();
        ret.setTaskCount(0);
        if (taskList != null && !taskList.equals("")) {
            ret.setTaskCount(taskList.split(",").length);
        }

        List<ReviewGroup> groupInfoList =
                this.reviewGroupService.getReviewGroupsByReviewTaskId(reviewTaskId.longValue());
        this.initProgressInfo(ret, groupInfoList);

        return ret;
    }

    /**
     * 审核进度信息初始化
     * */
    private void initProgressInfo(ReviewTaskBasicInfoVo vo, List<ReviewGroup> groupInfoList) {
        Assert.notNull(vo);

        // 已审核 && 未审核标注groups
        Integer isReviewedGroups = 0;
        Integer notReviewedGroups = 0;
        // 已审核 && 未审核标注创意数
        Integer isReviewedAds = 0;
        Integer notReviewedAds = 0;

        if (CollectionUtils.isNotEmpty(groupInfoList)) {
            for (ReviewGroup item : groupInfoList) {
                if (item.getStatus() == (byte) 2) {
                    isReviewedGroups += 1;
                    isReviewedAds += item.getAdNumReview();
                } else {
                    notReviewedGroups += 1;
                    notReviewedAds += item.getAdNumReview();
                }
            }
        }

        vo.setIsReviewedGroups(isReviewedGroups);
        vo.setNotReviewedGroups(notReviewedGroups);
        vo.setIsReviewedAds(isReviewedAds);
        vo.setNotReviewedAds(notReviewedAds);
    }

    @Override
    public ReviewPageInfoVo getPageInfo(QueryConditionVo vo) {
        Assert.notNull(vo);

        // 设置default值
        ReviewPageInfoVo ret = new ReviewPageInfoVo();

        // 未审核列表数据
        if (QueryConditionVo.NOT_REVIEW_TAB.equals(vo.getTabType())) {
            ret = this.reviewGroupService.getNotReviewedPageInfo(vo);
        }
        // 审核正确列表
        else if (QueryConditionVo.REVIEW_RIGHT_TAB.equals(vo.getTabType())) {
            ret = this.reviewGroupService.getReviewedRightPageInfo(vo);
            if (CollectionUtils.isNotEmpty(ret.getList())) {
                this.setReviewPageUserInfo(ret);
            }
        }
        // 审核错误列表
        else if (QueryConditionVo.REVIEW_WRONG_TAB.equals(vo.getTabType())) {
            ret = this.reviewGroupService.getReviewedWrongPageInfo(vo);
            if (CollectionUtils.isNotEmpty(ret.getList())) {
                this.setReviewPageUserInfo(ret);
                this.setReviewWrongDetailInfo(ret);
            }
        }

        return ret;
    }

    /**
     * 设置审核列表中审核有误的明细信息
     * */
    private void setReviewWrongDetailInfo(ReviewPageInfoVo pageInfo) {
        // Assert.notEmpty(pageInfo.getList());
        // List<ReviewInfoItem> reviewList = pageInfo.getList();
        //
        // List<Long> reveiwGroupIdList = new ArrayList<Long>();
        // Map<Long, ReviewInfoItem> reviewMap = new HashMap<Long,
        // ReviewInfoItem>();
        // for (ReviewInfoItem item : reviewList) {
        // reveiwGroupIdList.add(item.getReviewGroupId());
        // reviewMap.put(item.getReviewGroupId(), item);
        // }
        //
        // // 保存Group下AD的标注错误明细信息
        // Map<Long, Map<String, WrongTagDetailItem>> groupWrongTagInfoMap =
        // new HashMap<Long, Map<String, WrongTagDetailItem>>();
        // Map<Long, Map<String, WrongTradeDetailItem>> groupWrongTradeInfoMap =
        // new HashMap<Long, Map<String, WrongTradeDetailItem>>();
        //
        // if (CollectionUtils.isNotEmpty(reveiwGroupIdList)) {
        // List<ReviewAdTask> adTaskList =
        // this.reviewAdTaskService.getReviewWrongDetail(reveiwGroupIdList);
        // if (CollectionUtils.isNotEmpty(adTaskList)) {
        // for (ReviewAdTask adTask : adTaskList) {
        // Long reviewGroupId = adTask.getGroupIdReview();
        //
        // // 获取group对应的ad错误明细存储Map（用Map去重）
        // Map<String, WrongTagDetailItem> wrongTagInfoMap =
        // groupWrongTagInfoMap.get(reviewGroupId);
        // if (wrongTagInfoMap == null) {
        // wrongTagInfoMap = new HashMap<String, WrongTagDetailItem>();
        // }
        // Map<String, WrongTradeDetailItem> wrongTradeInfoMap =
        // groupWrongTradeInfoMap.get(reviewGroupId);
        // if (wrongTradeInfoMap == null) {
        // wrongTradeInfoMap = new HashMap<String, WrongTradeDetailItem>();
        // }
        //
        // // 如果行业信息不一致
        // if
        // (!adTask.getAdTradeIdLevel3().equals(adTask.getAdTradeIdLevel3Review()))
        // {
        // WrongTradeDetailItem wrongDetailInfoItem = new
        // WrongTradeDetailItem();
        // NewIndustryType tagTradeNode =
        // TradeUtils.getLevelThreeIndustryInfo(adTask.getAdTradeIdLevel3());
        // NewIndustryType reviewTradeNode =
        // TradeUtils.getLevelThreeIndustryInfo(adTask.getAdTradeIdLevel3Review());
        // if (tagTradeNode != null && reviewTradeNode != null) {
        // wrongDetailInfoItem.setBeforeTrade1(tagTradeNode.getFirstName());
        // wrongDetailInfoItem.setBeforeTrade2(tagTradeNode.getSecondName());
        // wrongDetailInfoItem.setBeforeTrade3(tagTradeNode.getThirdName());
        // wrongDetailInfoItem.setAfterTrade1(reviewTradeNode.getFirstName());
        // wrongDetailInfoItem.setAfterTrade2(reviewTradeNode.getSecondName());
        // wrongDetailInfoItem.setAfterTrade3(reviewTradeNode.getThirdName());
        //
        // String key =
        // String.valueOf(adTask.getAdTradeIdLevel3())
        // + String.valueOf(adTask.getAdTradeIdLevel3Review());
        // wrongTradeInfoMap.put(key, wrongDetailInfoItem);
        // }
        // }
        //
        // // 如何标注信息不一致
        // if (!adTask.getAdTag().equals(adTask.getAdTagReview())) {
        // // 创意Data类型（北斗 or 秋实 or DSP）
        // Byte dataType = Byte.valueOf(adTask.getDataType().toString());
        // Map<String, Integer> tagInfo =
        // AdTagUtils.getTagInfo(adTask.getAdTag(), dataType);
        // Map<String, Integer> reviewTagInfo =
        // AdTagUtils.getTagInfo(adTask.getAdTagReview(), dataType);
        //
        // // 美观度对比
        // Integer beautyValue = tagInfo.get(TagConstant.BEAUTY);
        // Integer reviewBeautyValue = reviewTagInfo.get(TagConstant.BEAUTY);
        // if (beautyValue != null && reviewBeautyValue != null &&
        // !beautyValue.equals(reviewBeautyValue)) {
        // String key = TagConstant.BEAUTY + beautyValue.toString() +
        // reviewBeautyValue.toString();
        // WrongTagDetailItem wrongDetailInfoItem = new WrongTagDetailItem();
        // wrongDetailInfoItem.setTagInfo(TagConstant.BEAUTY_DESC);
        // wrongDetailInfoItem.setBefore(AdTagUtils.getDesc(beautyValue));
        // wrongDetailInfoItem.setAfter(AdTagUtils.getDesc(reviewBeautyValue));
        //
        // wrongTagInfoMap.put(key, wrongDetailInfoItem);
        // }
        //
        // // 欺诈度对比
        // Integer cheatValue = tagInfo.get(TagConstant.CHEAT);
        // Integer reviewCheatValue = reviewTagInfo.get(TagConstant.CHEAT);
        // if (cheatValue != null && reviewCheatValue != null &&
        // !cheatValue.equals(reviewCheatValue)) {
        // String key = TagConstant.CHEAT + cheatValue.toString() +
        // reviewCheatValue.toString();
        // WrongTagDetailItem wrongDetailInfoItem = new WrongTagDetailItem();
        // wrongDetailInfoItem.setTagInfo(TagConstant.CHEAT_DESC);
        // wrongDetailInfoItem.setBefore(AdTagUtils.getDesc(cheatValue));
        // wrongDetailInfoItem.setAfter(AdTagUtils.getDesc(reviewCheatValue));
        //
        // wrongTagInfoMap.put(key, wrongDetailInfoItem);
        // }
        //
        // // 低俗度对比
        // Integer vulagrValue = tagInfo.get(TagConstant.VULGAR);
        // Integer reviewVulgarValue = reviewTagInfo.get(TagConstant.VULGAR);
        // if (vulagrValue != null && reviewVulgarValue != null &&
        // !vulagrValue.equals(reviewVulgarValue)) {
        // String key = TagConstant.VULGAR + vulagrValue.toString() +
        // reviewVulgarValue.toString();
        // WrongTagDetailItem wrongDetailInfoItem = new WrongTagDetailItem();
        // wrongDetailInfoItem.setTagInfo(TagConstant.VULGAR_DESC);
        // wrongDetailInfoItem.setBefore(AdTagUtils.getDesc(vulagrValue));
        // wrongDetailInfoItem.setAfter(AdTagUtils.getDesc(reviewVulgarValue));
        //
        // wrongTagInfoMap.put(key, wrongDetailInfoItem);
        // }
        //
        // // 高危度对比
        // Integer highDangerValue = tagInfo.get(TagConstant.HIGH_DANGER);
        // Integer reviewHighDangerValue =
        // reviewTagInfo.get(TagConstant.HIGH_DANGER);
        // if (highDangerValue != null && reviewHighDangerValue != null
        // && !highDangerValue.equals(reviewHighDangerValue)) {
        // String key =
        // TagConstant.HIGH_DANGER + highDangerValue.toString()
        // + reviewHighDangerValue.toString();
        // WrongTagDetailItem wrongDetailInfoItem = new WrongTagDetailItem();
        // wrongDetailInfoItem.setTagInfo(TagConstant.HIGH_DANGER_DESC);
        // wrongDetailInfoItem.setBefore(AdTagUtils.getDesc(highDangerValue));
        // wrongDetailInfoItem.setAfter(AdTagUtils.getDesc(reviewHighDangerValue));
        //
        // wrongTagInfoMap.put(key, wrongDetailInfoItem);
        // }
        // }
        //
        // // 保存获取的错误明细
        // groupWrongTagInfoMap.put(reviewGroupId, wrongTagInfoMap);
        // groupWrongTradeInfoMap.put(reviewGroupId, wrongTradeInfoMap);
        // }
        //
        // }
        //
        // // 设置group下的标注明细数据
        // for (ReviewInfoItem reviewInfoItem : reviewList) {
        // Long reviewGroupId = reviewInfoItem.getReviewGroupId();
        // Map<String, WrongTagDetailItem> wrongTagInfoMap =
        // groupWrongTagInfoMap.get(reviewGroupId);
        // Map<String, WrongTradeDetailItem> wrongTradeInfoMap =
        // groupWrongTradeInfoMap.get(reviewGroupId);
        //
        // if (wrongTagInfoMap != null && wrongTagInfoMap.size() > 0) {
        // List<WrongTagDetailItem> itemList = new
        // ArrayList<WrongTagDetailItem>();
        // for (WrongTagDetailItem item : wrongTagInfoMap.values()) {
        // itemList.add(item);
        // }
        // reviewInfoItem.setWrongTagInfoList(itemList);
        // }
        // if (wrongTradeInfoMap != null && wrongTradeInfoMap.size() > 0) {
        // List<WrongTradeDetailItem> itemList = new
        // ArrayList<WrongTradeDetailItem>();
        // for (WrongTradeDetailItem item : wrongTradeInfoMap.values()) {
        // itemList.add(item);
        // }
        // reviewInfoItem.setWrongTradeInfoList(itemList);
        // }
        // }
        //
        // }

    }

    /**
     * 设置审核列表标注&审核用户名称信息
     * */
    private void setReviewPageUserInfo(ReviewPageInfoVo pageInfo) {
        Assert.notEmpty(pageInfo.getList());

        List<ReviewInfoItem> reviewList = pageInfo.getList();
        List<Integer> userIdList = new ArrayList<Integer>();
        // 获取标注&审核User信息
        for (ReviewInfoItem item : reviewList) {
            if (item.getTagUserId() != null) {
                userIdList.add(item.getTagUserId());
            }
            if (item.getReviewUserId() != null) {
                userIdList.add(item.getReviewUserId());
            }
        }
        if (CollectionUtils.isEmpty(userIdList)) {
            return;
        }
        // 获取用户ID对应的用户名信息
        List<User> userList = this.userService.getUserByIdList(userIdList);
        Map<Integer, String> userNameMap = new HashMap<Integer, String>();
        if (CollectionUtils.isNotEmpty(userList)) {
            for (User item : userList) {
                userNameMap.put(item.getId(), item.getUserName());
            }
        }

        // 设置标注&审核用户名
        for (ReviewInfoItem item : reviewList) {
            if (item.getTagUserId() != null) {
                String userName = userNameMap.get(item.getTagUserId());
                item.setTagUser(userName == null ? "" : userName);
            }
            if (item.getReviewUserId() != null) {
                String userName = userNameMap.get(item.getReviewUserId());
                item.setReviewUser(userName == null ? "" : userName);
            }
        }
    }

}
