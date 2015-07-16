package com.baidu.dpop.ctp.review.constants;

/**   
 * 审核任务类型（内部/外部任务，0-内部， 1-外部）
 * @author cgd  
 * @date 2015年3月29日 下午3:14:14 
 */
public enum ReviewTaskType {
    INTERNAL_TASK(0, "内部审核"), OUTER_TASK(1, "外部审核"), ALL(2, "全部");
    
    private Integer id;
    private String desc;

    private ReviewTaskType(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }
    
    public static ReviewTaskType get(Integer id) {
        if (null == id) {
            return null;
        }
        for (ReviewTaskType temp : ReviewTaskType.values()) {
            if (temp.getId().equals(id)) {
                return temp;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
