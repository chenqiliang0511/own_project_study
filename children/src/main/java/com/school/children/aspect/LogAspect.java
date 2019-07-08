package com.school.children.aspect;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Aspect
@Component
public class LogAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

  @Pointcut("execution(public * com.school.children.action..*(..)) || execution(public * com.school.children.dao..*(..))")
  public void aspect(){

  }

  @Around("aspect()")
  public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable{
    Signature signature = joinPoint.getSignature();
    //获取类名
    String className = signature.getDeclaringTypeName();
    //获取方法名
    String methodName = signature.getName();
    JSONArray jsonArray = new JSONArray();
    //获取参数值
    Object[] args = joinPoint.getArgs();
    if(signature instanceof MethodSignature){
      MethodSignature methodSignature = (MethodSignature) signature;
      //获取参数名
      String[] parameterNames = methodSignature.getParameterNames();
      //获取参数类型
      Class[] parameterTypes = methodSignature.getParameterTypes();
      int i =0;
      for (Object arg : args) {
        if(i>= parameterNames.length){
          break;
        }
        if(parameterTypes[i].equals(HttpServletRequest.class)
            || parameterTypes[i].equals(HttpServletResponse.class)
            || parameterTypes[i].equals(BindingResult.class)
            || arg instanceof MultipartFile
            || arg == null){
          i++;
          continue;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(parameterNames[i++],arg);
        jsonArray.add(jsonObject);
      }
    }
    LOGGER.info("入参：{}", JSONObject.toJSONString(jsonArray));
    LOGGER.info("{}!{}",className,methodName);
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    //获取请求头
    JSONArray headers = new JSONArray();
    if(requestAttributes != null){
      HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
      Enumeration<String> headerNames = request.getHeaderNames();
      while (headerNames.hasMoreElements()){
        JSONObject header = new JSONObject();
        String nextElement = headerNames.nextElement();
        String requestHeader = request.getHeader(nextElement);
        header.put(nextElement,requestHeader);
        headers.add(header);
      }
    }
    LOGGER.info("请求头：{}",JSONObject.toJSONString(headers));
    LOGGER.info("方法执行前");
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    //获取返回值
    Object o = joinPoint.proceed();
    stopWatch.stop();
    LOGGER.info("返回值：{}", JSON.toJSONString(o));
    //获取用时
    long totalTimeMillis = stopWatch.getTotalTimeMillis();
    LOGGER.info("用时：{}ms",totalTimeMillis);
    return o;
  }
}
