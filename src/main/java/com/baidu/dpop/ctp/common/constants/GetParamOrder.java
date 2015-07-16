package com.baidu.dpop.ctp.common.constants;

public enum GetParamOrder {
	
	DESC("desc", "内部用户登录"),
    ASC("asc", "外部用户登录");
    
    
    private String value;
    private String desc;

    private GetParamOrder(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static GetParamOrder get(String value) {
        if (null == value) {
            return null;
        }
        for (GetParamOrder temp : GetParamOrder.values()) {
            if (temp.getValue().equals(value)) {
                return temp;
            }
        }
        return null;
    }
    
    public String getValue() {
        return value;
    }

    public void setId(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
	

}
