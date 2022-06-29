package com.example.zmusic.exception;

public class BizException extends RuntimeException {
  private final Integer code;

  public BizException(ExceptionType type) {
    super(type.getMessage());
    this.code = type.getCode();
  }

  public BizException(Integer code) {
    this.code = code;
  }

  public BizException(String message, Integer code) {
    super(message);
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }
}
