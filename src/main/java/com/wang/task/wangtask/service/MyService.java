package com.wang.task.wangtask.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wang.task.wangtask.dao.MyDao;
import com.wang.task.wangtask.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyService {
    @Autowired
    MyDao myDao;

    public Map<String, Object> userLogin(JSONObject jsonParam, HttpSession session) {
        Map<String, Object> dataMap = new HashMap<>();
        Map map = JSON.parseObject(jsonParam.toJSONString());
        map.put("userId", map.get("userId").toString());
        map.put("password", map.get("password").toString());
        Map<String, Object> data = myDao.userLogin(map);
        if (null != data && !data.isEmpty()) {
            dataMap.put("code", 200);
            dataMap.put("data", data);
            session.setAttribute("loginFlag", true);
        } else {
            dataMap.put("code", 404);
        }
        return dataMap;
    }
}
