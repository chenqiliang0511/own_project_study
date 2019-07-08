package com.school.children;



import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

public class Test {
  public static void main(String[] args) throws Exception{
    Map map = new HashMap();
    map.put("1",null);
    System.out.println(JSONObject.toJSONString(map, SerializerFeature.WRITE_MAP_NULL_FEATURES));
  }
}
