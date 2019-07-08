package com.school.children.action;

import com.school.children.dao.Children;
import com.school.children.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/register")
@Validated
public class ChildrenRegisterAppAction {

  private static final Logger LOGGER = LoggerFactory.getLogger(ChildrenRegisterAppAction.class);

  @Autowired
  private Children children;

  @GetMapping("/getRegisterLimitInfo.do")
  public Children getRegisterLimitInfo(HttpServletRequest request, HttpServletResponse response, Children children, String s){
    LOGGER.info("方法执行中");
    return children;
  }

  @PostMapping("/getChildren.do")
  public Children getChildren(@Valid @NotNull(message = "入参对象不能为空") @RequestBody(required = false) Children children) throws Exception{
//    if (bindingResult.hasErrors()){
//      List<ObjectError> allErrors = bindingResult.getAllErrors();
//      for (ObjectError allError : allErrors) {
//        LOGGER.error(allError.getDefaultMessage());
//      }
//    }
    LOGGER.info("获取孩子方法执行中");
    if(children.getAge()==12){
      throw new MyException(100,"这货太小了吧");
    }
    return children;
  }

  @PostMapping("/getChildren1.do")
  public Children getChildren1(@Valid @NotNull(message = "入参对象不能为空") Children children) throws Exception{
//    if (bindingResult.hasErrors()){
//      List<ObjectError> allErrors = bindingResult.getAllErrors();
//      for (ObjectError allError : allErrors) {
//        LOGGER.error(allError.getDefaultMessage());
//      }
//    }
    LOGGER.info("获取孩子方法执行中");
//    if(children.getAge()==12){
//      throw new MyException(100,"这货太小了吧");
//    }
    return children;
  }

  @GetMapping("/getMyChildren.do")
  public Children getMyChildren(@RequestParam(value = "height",required = false) Double height,
                                @RequestParam(value = "age",required = false) Integer age,
                                @RequestParam(value = "name") String name
                                ){
//    if(bindingResult.hasErrors()){
//      for (ObjectError objectError : bindingResult.getAllErrors()) {
//        System.out.println(objectError.getDefaultMessage());
//      }
//    }
    Children children = new Children();
    children.setHeight(height);
    children.setAge(age);
    children.setName(name==null?"":name);
    return children;
  }

}
