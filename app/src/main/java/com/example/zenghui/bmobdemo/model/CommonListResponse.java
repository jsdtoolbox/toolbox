package com.example.zenghui.bmobdemo.model;

import java.util.List;
import java.util.Map;

/**
 * Created by zenghui on 2016/9/7.
 */
public class CommonListResponse {

    String resultcode;
    String resson;
    List<Object> result;

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

    public List<Object> getResult() {
        return result;
    }

    public void setResult(List<Object> result) {
        this.result = result;
    }
}
