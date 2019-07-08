package com.school.children.exception;

import com.school.children.dao.Children;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;


@RestControllerAdvice
public class MyExceptionAdvice {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyExceptionAdvice.class);
  @ExceptionHandler(Exception.class)
  public String doException(Exception e){
    LOGGER.error("拦截的错误信息是：{}",e);
    if(e instanceof MethodArgumentNotValidException){
      StringBuilder stringBuilder = new StringBuilder();
      BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
      if(bindingResult.hasErrors()){
        for (ObjectError objectError : bindingResult.getAllErrors()) {
          String message = objectError.getDefaultMessage();
          stringBuilder.append(message).append(";");
        }
      }
      String collect = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
      return collect;
    }else if(e instanceof MyException){
      return ((MyException) e).getStatus() + ";" + e.getMessage();
    }else {
      return "内部错误";
    }
  }

//  @ExceptionHandler(ConstraintViolationException.class)
//  public String handleValidationException(ConstraintViolationException e){
//    for(ConstraintViolation<?> s:e.getConstraintViolations()){
//      return s.getInvalidValue()+": "+s.getMessage();
//    }
//    return "请求参数不合法";
//  }
}
