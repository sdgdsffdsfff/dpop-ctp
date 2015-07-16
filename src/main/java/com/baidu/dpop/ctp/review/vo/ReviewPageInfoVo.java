package com.baidu.dpop.ctp.review.vo;

import java.util.List;

/**   
 * ReviewpageInfo Vo
 * @author cgd  
 * @date 2015年3月29日 上午10:41:44 
 */
public class ReviewPageInfoVo {
    
    private List<ReviewInfoItem> list; // page data
    private Integer page = 0; // 当前页
    private Integer size = 0; // 每页的数量
    private Long total = 0L; // 总记录数
    

    public List<ReviewInfoItem> getList() {
        return list;
    }

    public void setList(List<ReviewInfoItem> list) {
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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
    
}

