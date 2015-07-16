package com.baidu.dpop.ctp.review.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.base.AbstractDAOTests;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.review.dao.ReviewAdTaskDao;

/**   
 * ReviewAdTaskDaoImpl Test
 * @author cgd  
 * @date 2015年3月31日 下午8:11:03 
 */
public class ReviewAdTaskDaoImplTest extends AbstractDAOTests {
    
    @Resource
    private ReviewAdTaskDao reviewAdTaskDao;
    
    @Before
    public void setUp() {
        this.executeDatas("review/review_ad_task_dataset_init.sql");
    }
    
    @Test
    public void testTest() {
    	
    }
    
//    @Test
//    public void testGetReviewWrongDetail() {
//        List<ReviewAdTask> taskList = this.reviewAdTaskDao.getReviewWrongDetail(Arrays.asList(1222L, 1223L));
//        Assert.assertTrue(taskList.size() == 1);
//    }

}
