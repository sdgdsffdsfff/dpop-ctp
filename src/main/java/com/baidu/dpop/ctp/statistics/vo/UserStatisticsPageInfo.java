package com.baidu.dpop.ctp.statistics.vo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

/**
 * 标注人员统计信息VO
 * 
 * @author cgd
 * @date 2015年3月17日 上午10:31:48
 */
public class UserStatisticsPageInfo {

    private List<UserStatisticsItem> list; // page data
    private Integer page = 0; // 当前页
    private Integer size = 0; // 每页的数量
    private Integer total = 0; // 总记录数

    /**
     * 获取默认排序的指定page和size的人员统计信息
     * */
    public void setPageInfo(QueryConditionVo conditionVo, List<UserStatisticsItem> data) {
        Assert.notNull(conditionVo);
        Integer pageSize = conditionVo.getPageSize();
        Integer pageNo = conditionVo.getPageNo();
        Integer order = conditionVo.getOrder();

        if (CollectionUtils.isEmpty(data)) {
            return;
        }

        // 页码超出范围
        Integer total = data.size();
        if ((total / pageSize + 1) < pageNo) {
            return;
        }

        // 选择的排序方式
        switch (order) {
            case 1:
                Collections.sort(data, new GroupUpComparator());
            case 2:
                Collections.sort(data, new GroupDownComparator());
            case 3:
                Collections.sort(data, new AdUpComparator());
            case 4:
                Collections.sort(data, new AdDownComparator());
            default:
                Collections.sort(data, new DefaultComparator());
        }

        if ((total / pageSize + 1) == pageNo) { // 最后一页
            this.setList(data.subList((pageNo - 1) * pageSize, total));
            this.setPage(pageNo);
            this.setSize(pageSize);
            this.setTotal(total);
        } else { // 其他页
            this.setList(data.subList((pageNo - 1) * pageSize, pageNo * pageSize));
            this.setPage(pageNo);
            this.setSize(pageSize);
            this.setTotal(total);
        }

    }

    /**
     * 标注人员统计信息默认Comparator 排名按照已标注推广组及已标注创意数降序排列
     * */
    class DefaultComparator implements Comparator<UserStatisticsItem> {

        @Override
        public int compare(UserStatisticsItem o1, UserStatisticsItem o2) {
            // 已标注组数倒序排序
            if (o1.getIsDoneGroups().compareTo(o2.getIsDoneGroups()) > 0) {
                return -1;
            } else if (o1.getIsDoneGroups().compareTo(o2.getIsDoneGroups()) < 0) {
                return 1;
            }

            // 已标创意数倒序排序
            if (o1.getIsDoneAds().compareTo(o2.getIsDoneAds()) > 0) {
                return -1;
            } else if (o1.getIsDoneAds().compareTo(o2.getIsDoneAds()) < 0) {
                return 1;
            }
            // 相同
            return 0;
        }

    }

    /**
     * 推广组升序Comparator 排名按照已标注推广组升序排列
     * */
    class GroupUpComparator implements Comparator<UserStatisticsItem> {
        @Override
        public int compare(UserStatisticsItem o1, UserStatisticsItem o2) {
            return o1.getIsDoneGroups().compareTo(o2.getIsDoneGroups());
        }

    }

    /**
     * 推广组降序Comparator 排名按照已标注推广组降序排列
     * */
    class GroupDownComparator implements Comparator<UserStatisticsItem> {
        @Override
        public int compare(UserStatisticsItem o1, UserStatisticsItem o2) {
            if (o1.getIsDoneGroups().compareTo(o2.getIsDoneGroups()) > 0) {
                return -1;
            } else if (o1.getIsDoneGroups().compareTo(o2.getIsDoneGroups()) < 0) {
                return 1;
            }
            return 0;
        }

    }

    /**
     * 推广组升序Comparator 排名按照已标注推广组升序排列
     * */
    class AdUpComparator implements Comparator<UserStatisticsItem> {
        @Override
        public int compare(UserStatisticsItem o1, UserStatisticsItem o2) {
            return o1.getIsDoneAds().compareTo(o2.getIsDoneAds());
        }

    }

    /**
     * 推广组降序Comparator 排名按照已标注推广组降序排列
     * */
    class AdDownComparator implements Comparator<UserStatisticsItem> {
        @Override
        public int compare(UserStatisticsItem o1, UserStatisticsItem o2) {
            if (o1.getIsDoneAds().compareTo(o2.getIsDoneAds()) > 0) {
                return -1;
            } else if (o1.getIsDoneAds().compareTo(o2.getIsDoneAds()) < 0) {
                return 1;
            }
            return 0;
        }

    }

    public List<UserStatisticsItem> getList() {
        return list;
    }

    public void setList(List<UserStatisticsItem> list) {
        this.list = list;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
