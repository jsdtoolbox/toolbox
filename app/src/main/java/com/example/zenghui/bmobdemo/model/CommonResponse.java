package com.example.zenghui.bmobdemo.model;

import java.util.Map;

/**
 * Created by zenghui on 2016/9/7.
 */
public class CommonResponse {
    String resultcode;
    String resson;
    Map<String,Object> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getResson() {
        return resson;
    }

    public void setResson(String resson) {
        this.resson = resson;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
