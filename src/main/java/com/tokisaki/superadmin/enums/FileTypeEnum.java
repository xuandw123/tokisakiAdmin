package com.tokisaki.superadmin.enums;

/**

 */
public enum FileTypeEnum {
    /**/
    Task("Enum.FileType.Task"),
    UserTask("Enum.FileType.UserTask"),
    ScoreAward("Enum.FileType.ScoreAward")
    ;

    /**
     * 返回结果信息
     */
    private String message;

    FileTypeEnum(String message) {
        this.message = message;
    }



    public String getMessage() {
        return message;
    }
}
