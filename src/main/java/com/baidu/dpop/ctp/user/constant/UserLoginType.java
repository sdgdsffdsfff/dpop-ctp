package com.baidu.dpop.ctp.user.constant;

/**   
 * 用户登录类型常量定义
 * @author cgd  
 * @date 2014年12月24日 下午4:50:25 
 */
public enum UserLoginType {
    INNER_USER_LOGIN(Byte.valueOf("0"), "内部用户登录"),
    OUTSIDE_USER_LOGIN(Byte.valueOf("1"), "外部用户登录");
    
    
    private Byte id;
    private String desc;

    private UserLoginType(Byte id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static UserLoginType get(Number id) {
        if (null == id) {
            return null;
        }
        for (UserLoginType temp : UserLoginType.values()) {
            if (temp.getId().equals(id.byteValue())) {
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
