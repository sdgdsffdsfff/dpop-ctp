package com.baidu.dpop.ctp.review.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.review.dao.ReviewAdTaskDao;

/**
 * ReviewAdTaskServiceImplTest
 * 
 * @author cgd
 * @date 2015年3月31日 下午8:29:46
 */
public class ReviewAdTaskServiceImplTest {

    @Tested
    private ReviewAdTaskServiceImpl reviewAdTaskService;

    @Injectable
    private ReviewAdTaskDao reviewAdTaskDao;

    @SuppressWarnings("unchecked")
    @Test
    public void testGetReviewWrongDetail() {
        new NonStrictExpectations() {
            {
                reviewAdTaskDao.selectReviewWrongDetail((List<Long>) any);
                result = new ArrayList<ReviewAdTask>();
            }
        };

        List<ReviewAdTask> ret = reviewAdTaskService.getReviewWrongDetail(Arrays.asList(1L));
        Assert.assertTrue(ret.size() == 0);
    }

}
