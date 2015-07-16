package com.baidu.dpop.ctp.industrytype.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.baidu.dpop.ctp.industrytype.bo.IndustryType;
import com.baidu.dpop.ctp.industrytype.bo.IndustryTypeTreeNode;
import com.baidu.dpop.ctp.industrytype.bo.NewIndustryType;
import com.baidu.dpop.ctp.industrytype.dao.IndustryTypeDao;
import com.baidu.dpop.ctp.industrytype.dao.NewIndustryTypeDao;

@RunWith(JMockit.class)
public class IndustryTypeServiceImplTest {

    @Tested
    IndustryTypeServiceImpl industryServiceImpl;

    @Injectable
    NewIndustryTypeDao newIndustryTypeDao;

    @Injectable
    IndustryTypeDao industryTypeDao;

    @Test
    public void testGetLevelThreeIndustryType() {
        new NonStrictExpectations() {
            {
                newIndustryTypeDao.selectLevel3Data();
                List<NewIndustryType> list = new ArrayList<NewIndustryType>();
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        for (int k = 0; k < 10; k++) {
                            NewIndustryType type = new NewIndustryType();
                            type.setFirstId(i);
                            type.setFirstName("一级任务" + i);
                            type.setSecondId(j);
                            type.setSecondName("二级任务" + j);
                            type.setThirdId(k);
                            type.setThirdName("三级任务" + k);
                            type.setFullId(i * 10000 + j * 100 + k);

                            type.setFullName(type.getFirstName() + "_" + type.getSecondName() + "_"
                                    + type.getThirdName());
                            list.add(type);
                        }
                    }
                }
                result = list;
            }
        };
        Map<Integer, IndustryTypeTreeNode> result = industryServiceImpl.getLevelThreeIndustryType();
        Assert.assertNotNull(result);

        IndustryTypeTreeNode tempNode = result.get(3);
        Assert.assertEquals("一级任务3", tempNode.getName());

        tempNode = tempNode.getData().get(6);
        Assert.assertEquals("二级任务6", tempNode.getName());

        new NonStrictExpectations() {
            {
                newIndustryTypeDao.selectLevel3Data();
                result = null;
            }
        };
        Assert.assertEquals(0, industryServiceImpl.getLevelThreeIndustryType().size());
    }

    @Test
    public void testGetLevelTwoIndustryType() {
        new NonStrictExpectations() {
            {
                industryTypeDao.selectLevel2Data();
                IndustryType type = new IndustryType();
                type.setFirstId(1);
                type.setFirstName("test");
                type.setSecondId(1);
                type.setSecondName("test");
                type.setFullId(101);
                type.setFullName("test_test");
                result = Arrays.asList(type);
            }
        };

        Map<Integer, String> result = industryServiceImpl.getLevelTwoIndustryType();
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("test_test", result.get(101));

        new NonStrictExpectations() {
            {
                industryTypeDao.selectLevel2Data();
                result = null;
            }
        };
        Assert.assertEquals(0, industryServiceImpl.getLevelTwoIndustryType().size());
    }

    @Test
    public void testGetLevel3Data() {
        industryServiceImpl.getLevel3Data();
        new Verifications() {
            {
                newIndustryTypeDao.selectLevel3Data();
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchGetMapped() {
        industryServiceImpl.batchGetMapped(Arrays.asList(1));
        new Verifications() {
            {
                newIndustryTypeDao.batchSelect((List<Integer>) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testbatchUpdateLevel2IdAndName() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1100; i++) {
            list.add("1\t1\t1");
        }
        industryServiceImpl.batchUpdateNewIndustryType(list);
        new Verifications() {
            {
                newIndustryTypeDao.batchUpdateLevel2IdAndName((List<NewIndustryType>) any);
                times = 2;
            }
        };
    }

}
