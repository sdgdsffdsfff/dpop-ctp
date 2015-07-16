package com.baidu.dpop.ctp.review.web.controller;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.bo.ReviewTask;
import com.baidu.dpop.ctp.review.bo.ReviewTaskCondition;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.review.service.ReviewGroupService;
import com.baidu.dpop.ctp.review.service.ReviewTaskConditionService;
import com.baidu.dpop.ctp.review.service.ReviewTaskService;
import com.baidu.dpop.ctp.review.vo.DistributeReviewGroupResult;
import com.baidu.dpop.ctp.review.vo.SubmitReviewTaskVo;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.frame.core.base.web.JsonResult;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

/**   
 * ReviewGroupControllerTest
 * @author cgd  
 * @date 2015年5月14日 下午1:43:26 
 */
public class ReviewGroupControllerTest {
    
    @Tested
    private ReviewGroupController reviewGroupController;
    @Injectable
    private ReviewTaskService reviewTaskService;
    @Injectable
    private ReviewTaskConditionService reviewTaskConditionService;
    @Injectable
    private ReviewAdTaskService adTaskService;
    @Injectable
    private UserService userService;
    @Injectable
    private ReviewGroupService reviewGroupService;
    
    @Test
    public void testgetTasks() {
        new NonStrictExpectations() {
            {
                reviewTaskService.findById(anyLong);
                result = new RuntimeException();
            }
        };
        JsonResult ret = this.reviewGroupController.getTasks(1);
        Assert.assertTrue(ret.getSuccess().equals("false"));
        
        new NonStrictExpectations() {
            {
                reviewTaskService.findById(anyLong);
                ReviewTask rt = new ReviewTask();
                rt.setTaskCondition(1);
                result = rt;
                
                reviewTaskConditionService.findById(anyLong);
                ReviewTaskCondition condi = new ReviewTaskCondition();
                result = condi;
                
                userService.getCurrentLoginUser();
                result = DefaultBOUtils.getUser(1, "user1",
                        UserRoleType.OUTSIDE_TAG_USER.getId());
            }
        };
        new NonStrictExpectations() {
            {
                reviewGroupService.distributeNewReviewGroup((ReviewTask)any);
                result = new ReviewGroup();
                
                reviewTaskService.getTasksByGroup((ReviewTask)any, (ReviewGroup)any, (User)any);
                result = new DistributeReviewGroupResult();
            }
        };
        ret = this.reviewGroupController.getTasks(1);
        Assert.assertTrue(ret.getSuccess().equals("true"));
        
        new NonStrictExpectations() {
            {
                reviewGroupService.distributeNewReviewGroup((ReviewTask)any);
                result = new ReviewGroup();
                
                reviewTaskService.getTasksByGroup((ReviewTask)any, (ReviewGroup)any, (User)any);
                result = new RuntimeException();
            }
        };
        ret = this.reviewGroupController.getTasks(1);
        Assert.assertTrue(ret.getSuccess().equals("false"));
        
        new NonStrictExpectations() {
            {
                reviewGroupService.distributeNewReviewGroup((ReviewTask)any);
                result = null;
                reviewGroupService.getStartedGroupByUser((ReviewTask)any, (User)any);
                result = null;
            }
        };
        ret = this.reviewGroupController.getTasks(1);
        Assert.assertTrue(ret.getSuccess().equals("true"));
        
        new NonStrictExpectations() {
            {
                reviewGroupService.distributeNewReviewGroup((ReviewTask)any);
                result = null;
                reviewGroupService.getStartedGroupByUser((ReviewTask)any, (User)any);
                result = new RuntimeException();
            }
        };
        ret = this.reviewGroupController.getTasks(1);
        Assert.assertTrue(ret.getSuccess().equals("false"));
    }
    
//    @Test
//    public void testgetTasksByGroupId() {
//        new NonStrictExpectations() {
//            {
//                reviewGroupService.findById(anyLong);
//                result = new RuntimeException();
//            }
//        };
//        JsonResult ret = this.reviewGroupController.getTasksByGroupId(1L);
//        Assert.assertTrue(ret.getSuccess().equals("false"));
//        
//        new NonStrictExpectations() {
//            {
//                reviewGroupService.findById(anyLong);
//                result = null;
//            }
//        };
//        ret = this.reviewGroupController.getTasksByGroupId(1L);
//        Assert.assertTrue(ret.getSuccess().equals("false"));
//        
//        new NonStrictExpectations() {
//            {
//                reviewGroupService.findById(anyLong);
//                ReviewGroup rg = new ReviewGroup();
//                rg.setTaskIdReview(1);
//                result = rg;
//                
//                reviewTaskService.findById(anyLong);
//                result = new ReviewTask();
//                
//                userService.getCurrentLoginUser();
//                result = DefaultBOUtils.getUser(1, "user1",
//                        UserRoleType.OUTSIDE_TAG_USER.getId());
//                
//                reviewTaskService.getTasksByGroup((ReviewTask)any, (ReviewGroup)any, (User)any);
//                result = new DistributeReviewGroupResult();
//            }
//        };
//        ret = this.reviewGroupController.getTasksByGroupId(1L);
//        Assert.assertTrue(ret.getSuccess().equals("true"));
//        
//    }
    
//    @Test
//    public void testsubmitTasksByGroupId() {
//        new NonStrictExpectations() {
//            {
//                reviewGroupService.submitReviewTasks((SubmitReviewTaskVo)any, (User)any);
//                result = new RuntimeException();
//            }
//        };
//        
//        JsonResult ret = this.reviewGroupController.submitTasksByGroupId(new SubmitReviewTaskVo());
//        Assert.assertTrue(ret.getSuccess().equals("false"));
//        
//        new NonStrictExpectations() {
//            {
//                reviewGroupService.submitReviewTasks((SubmitReviewTaskVo)any, (User)any);
//            }
//        };
//        
//        ret = this.reviewGroupController.submitTasksByGroupId(new SubmitReviewTaskVo());
//        Assert.assertTrue(ret.getSuccess().equals("true"));
//    }
    
    @Test
    public void testgiveUpGroup() {
        new NonStrictExpectations() {
            {
                userService.getCurrentLoginUser();
                result = DefaultBOUtils.getUser(1, "user1",
                        UserRoleType.OUTSIDE_TAG_USER.getId());
            }
        };
        new NonStrictExpectations() {
            {
                reviewGroupService.findById(anyLong);
                ReviewGroup rg = new ReviewGroup();
                rg.setStatus((byte)2);
                result = rg;
            }
        };
        JsonResult ret = this.reviewGroupController.giveUpGroup(1L);
        Assert.assertTrue(ret.getSuccess().equals("true"));
        
        new NonStrictExpectations() {
            {
                reviewGroupService.findById(anyLong);
                ReviewGroup rg = new ReviewGroup();
                rg.setStatus((byte)1);
                rg.setModifyUserIdReview(2);
                result = rg;
            }
        };
        ret = this.reviewGroupController.giveUpGroup(1L);
        Assert.assertTrue(ret.getSuccess().equals("false"));
        
        new NonStrictExpectations() {
            {
                reviewGroupService.findById(anyLong);
                ReviewGroup rg = new ReviewGroup();
                rg.setStatus((byte)1);
                rg.setModifyUserIdReview(1);
                result = rg;
                
                reviewGroupService.giveUpGroup((ReviewGroup)any);
                result = new RuntimeException();
            }
        };
        ret = this.reviewGroupController.giveUpGroup(1L);
        Assert.assertTrue(ret.getSuccess().equals("false"));
        
        new NonStrictExpectations() {
            {
                reviewGroupService.findById(anyLong);
                ReviewGroup rg = new ReviewGroup();
                rg.setStatus((byte)1);
                rg.setModifyUserIdReview(1);
                result = rg;
                
                reviewGroupService.giveUpGroup((ReviewGroup)any);
            }
        };
        ret = this.reviewGroupController.giveUpGroup(1L);
        Assert.assertTrue(ret.getSuccess().equals("true"));
    }

}
