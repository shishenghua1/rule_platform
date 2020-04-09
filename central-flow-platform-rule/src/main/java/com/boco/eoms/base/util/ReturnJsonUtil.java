package com.boco.eoms.base.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author ssh
 * @description:json返回格式工具类
 * @date 2019/9/416:25
 */
public class ReturnJsonUtil {

    /**
     *失败信息内容
     * @param failInfo
     * @param message
     * @return
     */
    public static JSONObject returnFailList(JSONObject failInfo,String message){
        failInfo.put("result", "fail");
        failInfo.put("message", message);
        return failInfo;
    }

    /**
     * json成功的返回信息
     * @param message
     * @return
     */
    public static JSONObject returnSuccessList(String message){
        JSONObject successInfo = new JSONObject();
        successInfo.put("result", "success");
        successInfo.put("message", message);
        return successInfo;
    }

    /**
     * json失败的返回信息
     * @param message
     * @return
     */
    public static JSONObject returnFailList(String message){
        JSONObject failInfo = new JSONObject();
        failInfo.put("result", "fail");
        failInfo.put("message", message);
        return failInfo;
    }
}
