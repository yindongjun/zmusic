package com.example.zmusic.exception;

public enum ExceptionType {
  BAD_REQUEST(400, "请求错误"),
  UNAUTHORIZED(401, "未登录"),
  FORBIDDEN(403, "无权操作"),
  NOT_FOUND(404, "未找到"),
  INNER_ERROR(500, "系统内部异常"),

  // 000_00_000
  // 400 错误
  // User
  USERNAME_DUPLICATE(40000001, "用户名重复"),
  USER_NOT_FOUND(40000002, "用户不存在"),
  USERNAME_PASSWORD_NOT_MATCH(40000003, "用户名或密码错误"),
  CONFIRM_PASSWORD_NOT_MATCH(40000004, "密码和确认密码不一致"),

  // Role
  ROLE_NOT_FOUND(40001001, "角色不存在"),
  ROLE_TITLE_DUPLICATE(40001002, "角色标识重复"),

  // Music
  MUSIC_NOT_FOUND(40002001, "音乐不存在"),

  // File
  FILE_NOT_FOUND(40003000, "文件不存在"),

  STORAGE_NOT_FOUND(40003101, "文件上传服务不存在"),

  CREATE_FILE_FAIL(40003102, "创建文件失败"),

  DELETE_FILE_FAIL(40003103, "删除文件失败"),

  FILE_KEY_NOT_NULL(40003104, "文件 KEY 不能为空"),

  FILE_URL_NOT_NULL(40003104, "文件路径不能为空"),

  // Artist
  ARTIST_NOT_FOUND(40004100, "艺术家不存在"),

  // Playlist
  PLAYLIST_NOT_FOUND(4000500, "歌单不存在"),

  // 500 错误
  USER_NOT_ENABLE(50001001, "用户被禁用"),
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
