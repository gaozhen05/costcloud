package com.njwd.costfinance.entity;


import com.njwd.costfinance.constant.ErrorCode;
import com.njwd.costfinance.util.DateTimeUtil;

/**
 * 响应体
 * @Description
 * @Author 顾小伟
 * @Date 2019/5/22 14:43
 */
public class ApiMessage {

    public static final int SUCCESS = 200;

    public static final int ERROR = 200;

    private String timestamp;

    private int status;

    private String message;

    private String errorCode;

    private Object data;

    public ApiMessage(String timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public ApiMessage(String timestamp, int status, String message, String errorCode) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }

    public ApiMessage(String timestamp, int status, String message, Object data) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ApiMessage success(String message){
        return new ApiMessage(DateTimeUtil.initCurrentTime(),SUCCESS, message);
    }

    public static ApiMessage success(String message, Object data){
        return new ApiMessage(DateTimeUtil.initCurrentTime(),SUCCESS, message,data);
    }

    public static ApiMessage errorParam(String message){
        return new ApiMessage(DateTimeUtil.initCurrentTime(),ERROR,message, ErrorCode.PARAM_ERROR.getCode());
    }

    public static ApiMessage error(ErrorCode errorCode){
        return new ApiMessage(DateTimeUtil.initCurrentTime(),ERROR,errorCode.getMessage(), errorCode.getCode());
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
