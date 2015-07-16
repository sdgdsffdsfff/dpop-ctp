package com.baidu.dpop.ctp.review.constants;

/**   
 * 审核任务类型（内部/外部任务，0-内部， 1-外部）
 * @author cgd  
 * @date 2015年3月29日 下午3:14:14 
 */
public enum ReviewType {
    BLIND_REVIEW(Byte.valueOf("0"), "盲审"), NO_BLIND_REVIEW(Byte.valueOf("1"), "明审");
    
    private Byte id;
    private String desc;
    
    public static boolean isBlind(Number id) {
    	if (id.intValue() == BLIND_REVIEW.id.intValue()) {
    		return true;
    	}
    	return false;
    }

    private ReviewType(Byte id, String desc) {
        this.id = id;
        this.desc = desc;
    }
    
    public static ReviewType get(Byte id) {
        if (null == id) {
            return null;
        }
        for (ReviewType temp : ReviewType.values()) {
            if (temp.getId().equals(id)) {
                return temp;
            }
        }
        return null;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
