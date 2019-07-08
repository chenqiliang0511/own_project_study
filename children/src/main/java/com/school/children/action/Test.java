package com.school.children.action;

import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test {
  private static void function(){
    List<String> list = Arrays.asList("4", "2", "1", "3");
    ArrayList<String> stringList = new ArrayList<>(list);
    for (String s : stringList) {
      if(s.equals("4"))
      stringList.remove(s);
    }
    System.out.println(stringList.size());
  }

  private static void function1(){
    List<String> list = Arrays.asList("4", "2", "1", "3");
    ArrayList<String> stringList = new ArrayList<>(list);
    stringList.forEach(s -> {
      if(s.equals("4"))
        stringList.remove(s);
    });
    System.out.println(stringList.size());
  }

  private static void function2(){
    List<String> list = Arrays.asList("4", "2", "1", "3");
    ArrayList<String> stringList = new ArrayList<>(list);
    stringList.removeIf(s -> s.equals("4"));
    System.out.println(stringList.size());
  }

  private static void function3(){
    List<String> list = Arrays.asList("4", "2", "1", "3");
    ArrayList<String> stringList = new ArrayList<>(list);
    Iterator<String> iterator = stringList.iterator();
    while (iterator.hasNext()){
      String s = iterator.next();
      if(s.equals("4")) {
        iterator.remove();
      }
    }
    System.out.println(stringList.size());
  }

  public static void main(String[] args) {
    function2();
  }
}
