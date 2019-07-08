package com.school.children.exception;

public class MyException extends RuntimeException {

  private Integer status;

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public MyException(Integer status, String message) {
    super(message);
    this.status = status;
  }

}
