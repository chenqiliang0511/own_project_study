package com.school.children.dao;

import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Valid
@Component
@ConfigurationProperties(prefix = "children.limit")
public class Children {

  @Range(min = 7,max = 15,message = "年龄不对呀")
  private Integer age;

  @NotNull(message = "名称不能为空")
  private String name;

  @Digits(integer = 2,fraction = 2,message = "小数错误")
  private Double height;

  @Pattern(regexp = "^[0-9]{1,2}$",message = "家庭人数不对")
  private String homeNumber;

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public String getHomeNumber() {
    return homeNumber;
  }

  public void setHomeNumber(String homeNumber) {
    this.homeNumber = homeNumber;
  }
}
