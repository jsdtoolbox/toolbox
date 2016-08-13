package com.example.zenghui.bmobdemo.model;

import java.util.List;
import java.util.Map;

/**
 * Created by zenghui on 16/8/13.
 */
public class LawyerInfo {

    String reason;
    List<Map<String,String>> result;
    int error_code;


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Map<String, String>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, String>> result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
