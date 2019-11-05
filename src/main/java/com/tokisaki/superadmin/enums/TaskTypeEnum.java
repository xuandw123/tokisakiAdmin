package com.tokisaki.superadmin.enums;

/**

 */
public enum TaskTypeEnum {
    /**/
    ShortTerm("Enum.TaskType.ShortTerm"),
    LongTerm("Enum.TaskType.LongTerm"),
    ;

    /**
     * 返回结果信息
     */
    private String message;

    TaskTypeEnum(String message) {
        this.message = message;
    }



    public String getMessage() {
        return message;
    }
}
