package com.baidu.dpop.ctp.review.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.bo.ReviewTask;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.review.service.ReviewGroupService;
import com.baidu.dpop.ctp.review.service.ReviewTaskService;
import com.baidu.dpop.ctp.review.vo.ReviewTaskBasicInfoVo;
import com.baidu.dpop.ctp.user.service.UserService;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

/**
 * ReviewServiceImplTest
 * 
 * @author cgd
 * @date 2015年4月2日 下午3:32:55
 */
public class ReviewServiceImplTest {

    @Tested
    private ReviewServiceImpl reviewService;
    @Injectable
    private ReviewTaskService reviewTaskService;
    @Injectable
    private ReviewGroupService reviewGroupService;
    @Injectable
    private UserService userService;
    @Injectable
    private ReviewAdTaskService reviewAdTaskService;

    @Test(expected = Exception.class)
    public void testgetBasicInfoWithException() {
        new NonStrictExpectations() {
            {
                reviewTaskService.findById(anyLong);
                result = null;
            }
        };
        this.reviewService.getBasicInfo(1);
    }

    @Test
    public void testgetBasicInfo() {
        new NonStrictExpectations() {
            {
                reviewTaskService.findById(anyLong);
                ReviewTask task = new ReviewTask();
                task.setAddTime(new Date());
                task.setAddUser("cgd");
                task.setBlind(true);
                task.setGroupNum(10);
                task.setGroupNumActual(5);
                task.setId(1);
                task.setModuserLevel(0);
                task.setStatus((byte) 0);
                task.setTaskList("1,2");
                task.setTaskName("task_name");
                result = task;

                reviewGroupService.getReviewGroupsByReviewTaskId(anyLong);
                List<ReviewGroup> groupInfoList = new ArrayList<ReviewGroup>();
                ReviewGroup item = new ReviewGroup();
                item.setStatus((byte) 0);
                item.setAdNum(10);
                item.setAdNumReview(5);
                groupInfoList.add(item);

                ReviewGroup item2 = new ReviewGroup();
                item2.setStatus((byte) 2);
                item2.setAdNum(5);
                item2.setAdNumReview(2);
                groupInfoList.add(item2);

                result = groupInfoList;
            }
        };
        ReviewTaskBasicInfoVo ret = this.reviewService.getBasicInfo(1);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getAddUser().equals("cgd"));
        Assert.assertTrue(ret.getIsReviewedGroups() == 1);
        Assert.assertTrue(ret.getNotReviewedAds() == 5);
    }

//    @SuppressWarnings("unchecked")
//    @Test
//    public void testgetPageInfo() {
//        new NonStrictExpectations() {
//            {
//                reviewGroupService.getNotReviewedPageInfo((QueryConditionVo) any);
//                result = new ReviewPageInfoVo();
//            }
//        };
//
//        QueryConditionVo vo = new QueryConditionVo();
//        vo.setTabType(1);
//        ReviewPageInfoVo ret = this.reviewService.getPageInfo(vo);
//        Assert.assertNotNull(ret);
//
//        new NonStrictExpectations() {
//            {
//                reviewGroupService.getReviewedRightPageInfo((QueryConditionVo) any);
//                ReviewPageInfoVo vo = new ReviewPageInfoVo();
//                List<ReviewInfoItem> itemList = new ArrayList<ReviewInfoItem>();
//                ReviewInfoItem item = new ReviewInfoItem();
//                item.setReviewUserId(1);
//                item.setTagUserId(1);
//                itemList.add(item);
//                vo.setList(itemList);
//                result = vo;
//
//                userService.getUserByIdList((List<Integer>) any);
//                List<User> userList = new ArrayList<User>();
//                User user = new User();
//                user.setId(1);
//                user.setUserName("cgd");
//                userList.add(user);
//                result = userList;
//            }
//        };
//        vo = new QueryConditionVo();
//        vo.setTabType(2);
//        ret = this.reviewService.getPageInfo(vo);
//        Assert.assertNotNull(ret);
//        Assert.assertTrue(ret.getList().size() > 0);
//        Assert.assertTrue(ret.getList().get(0).getReviewUser().equals("cgd"));
//
//        new NonStrictExpectations() {
//            {
//                reviewGroupService.getReviewedWrongPageInfo((QueryConditionVo) any);
//                ReviewPageInfoVo vo = new ReviewPageInfoVo();
//                List<ReviewInfoItem> itemList = new ArrayList<ReviewInfoItem>();
//                ReviewInfoItem item = new ReviewInfoItem();
//                item.setReviewGroupId(1L);
//                item.setReviewUserId(1);
//                item.setTagUserId(1);
//                itemList.add(item);
//                vo.setList(itemList);
//                result = vo;
//
//                userService.getUserByIdList((List<Integer>) any);
//                List<User> userList = new ArrayList<User>();
//                User user = new User();
//                user.setId(1);
//                user.setUserName("cgd");
//                userList.add(user);
//                result = userList;
//
//                reviewAdTaskService.getReviewWrongDetail((List<Long>) any);
//                List<ReviewAdTask> adTaskList = new ArrayList<ReviewAdTask>();
//                ReviewAdTask adTask1 = new ReviewAdTask();
//                adTask1.setAdTag(9873);
//                adTask1.setAdTagReview(4747);
//                adTask1.setAdTradeIdLevel3(510199);
//                adTask1.setAdTradeIdLevel3Review(510101);
//                adTask1.setDataType((byte)0);
//                adTask1.setGroupIdReview(1L);
//                adTaskList.add(adTask1);
//                result = adTaskList;
//            }
//        };
//        new MockUp<TradeUtils>() {
//            @Mock
//            public NewIndustryType getLevelThreeIndustryInfo(Integer level3) {
//                NewIndustryType item = new NewIndustryType();
//                item.setFirstName("trade_level1");
//                item.setSecondName("trade_level2");
//                item.setThirdName("trade_level3");
//                return item;
//            }
//        };
//
//        vo = new QueryConditionVo();
//        vo.setTabType(3);
//        ret = this.reviewService.getPageInfo(vo);
//        Assert.assertNotNull(ret);
//        Assert.assertTrue(ret.getList().size() > 0);
//        Assert.assertTrue(ret.getList().get(0).getWrongTagInfoList().size() > 0);
//        Assert.assertTrue(ret.getList().get(0).getWrongTradeInfoList().size() > 0);
//    }

//    @Test
//    public void testgetReviewTaskAccuracy() {
//        new NonStrictExpectations() {
//            {
//                reviewGroupService.getReviewedAdDetail(anyInt, anyInt);
//                List<WrongAdDetailVo> wrongDetailList = new ArrayList<WrongAdDetailVo>();
//                result = wrongDetailList;
//
//            }
//        };
//
//        ReviewTaskAccuracyVo ret = this.reviewService.getReviewTaskAccuracy(1);
//        Assert.assertNotNull(ret);
//        Assert.assertTrue(ret.getBeautyAccuracy() < 0.1);
//
//        new NonStrictExpectations() {
//            {
//                reviewGroupService.getReviewedAdDetail(anyInt, anyInt);
//                List<WrongAdDetailVo> wrongDetailList = new ArrayList<WrongAdDetailVo>();
//                WrongAdDetailVo item = new WrongAdDetailVo();
//                item.setDataType((byte) 0);
//                item.setWuliaoType((byte)2);
//                item.setAdTag(9873);
//                item.setReivewAdTag(4747);
//                item.setTradeLevel3(1);
//                item.setReviewTradeLevel3(1);
//                wrongDetailList.add(item);
//                result = wrongDetailList;
//
//            }
//        };
//
//        ret = this.reviewService.getReviewTaskAccuracy(1);
//        Assert.assertNotNull(ret);
//        Assert.assertTrue(ret.getBeautyAccuracy() < 99.0);
//    }

//    @Test
//    public void testgetReviewTagAccuracy() {
//
//        new NonStrictExpectations() {
//            {
//                reviewGroupService.getReviewedAdDetail(anyInt, anyInt);
//                List<WrongAdDetailVo> wrongDetailList = new ArrayList<WrongAdDetailVo>();
//                result = wrongDetailList;
//            }
//        };
//
//        ReviewTagStatisticsVo ret = this.reviewService.getReviewTagAccuracy(1);
//        Assert.assertTrue(ret.getReviewBlackVulgarNum() == 0);
//
//        new NonStrictExpectations() {
//            {
//                reviewGroupService.getReviewedAdDetail(anyInt, anyInt);
//                List<WrongAdDetailVo> wrongDetailList = new ArrayList<WrongAdDetailVo>();
//                WrongAdDetailVo item = new WrongAdDetailVo();
//                item.setDataType((byte) 0);
//                item.setAdTag(9873);
//                item.setReivewAdTag(4747);
//                item.setTradeLevel3(1);
//                item.setReviewTradeLevel3(1);
//                wrongDetailList.add(item);
//                result = wrongDetailList;
//            }
//        };
//
//        ret = this.reviewService.getReviewTagAccuracy(1);
//        Assert.assertTrue(ret.getTagNormalBeautyNum() == 1);
//
//    }

}
