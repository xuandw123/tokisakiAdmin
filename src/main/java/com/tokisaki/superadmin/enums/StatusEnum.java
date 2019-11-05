package com.tokisaki.superadmin.enums;

/**

 */
public enum StatusEnum {
    /**/
    Normal("Enum.Status.Normal"),
    Frozen("Enum.Status.Frozen"),
    ;
	

    /**
     * 返回结果信息
     */
    private String message;

    StatusEnum(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
