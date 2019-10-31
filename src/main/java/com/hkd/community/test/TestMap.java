package com.hkd.community.test;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TestMap {
    public TreeMap<String , Object> searrstNode(String warn , TreeMap<String , Object> map){
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry);
        }
        return  null;
    }

    public static void main(String[] args) {
        TreeMap<String , Object> map1 = new TreeMap<>();
        map1.put("total",100);
        //map.put("aggregations",new TreeMap<String , Object>().put("buckets1",new TreeMap<String , Object>().put("levels"),new TreeMap<String , Object>().put("info"),))
        TreeMap<String , Object> map6 = new TreeMap<>();
        TreeMap<String , Object> map5 = new TreeMap<>();
        TreeMap<String , Object> map4 = new TreeMap<>();
        TreeMap<String , Object> map3 = new TreeMap<>();
        TreeMap<String , Object> map2 = new TreeMap<>();
        map5.put("count",90);
        map6.put("count",10);
        map4.put("info",map5);
        map3.put("warn",map6);
        map2.put("levels",map5);
    }
}

