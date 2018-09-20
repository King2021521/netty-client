package com.zxm.me;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author zxm
 * @Description
 * @Date Create in 下午 2:38 2018/9/20 0020
 */
public class MessageHandler {
    private static final String HEADER = "header";
    private static final String MESSAGE_TYPE = "type";
    public static final String BODY = "body";

    public static final String LOGIN = "login";
    public static final String MESSAGE = "message";

    public static String encode(String type, String content){
        JSONObject payload = new JSONObject();
        JSONObject header = new JSONObject();
        header.put(MESSAGE_TYPE,type);
        payload.put(HEADER,header);
        payload.put(BODY,content);
        return payload.toJSONString();
    }

    public static JSONObject decode(String msg){
        return JSONObject.parseObject(msg);
    }
}
