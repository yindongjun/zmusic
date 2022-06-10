package com.example.zmusic.exception;

public enum ExceptionType {
    BAD_REQUEST(400, "请求错误"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "无权操作"),
    NOT_FOUND(404, "未找到"),
    INNER_ERROR(500, "系统内部异常"),

    // 000_00_000
    // 400 错误
    USERNAME_DUPLICATE(40001001, "用户名重复"),
    USER_NOT_FOUND(40001002, "用户不存在"),
    USERNAME_PASSWORD_NOT_MATCH(40001003, "用户名或密码错误"),


    // MUSIC
    MUSIC_NOT_FOUND(40002001, "音乐不存在"),


    // FILE
    FILE_NOT_FOUND(40003001, "文件不存在"),

    // FILE COS
    STORAGE_NOT_FOUND(40003100, "文件上传服务不存在"),
    CREATE_FILE_FAIL(40003101, "创建文件失败"),
    DELETE_FILE_FAIL(40003102, "删除文件失败"),


    // 500 错误
    USER_NOT_ENABLE(50001001, "用户未启用"),
    USER_LOCKED(50001002, "用户被锁定");

    private final Integer code;

    private final String message;

    ExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
