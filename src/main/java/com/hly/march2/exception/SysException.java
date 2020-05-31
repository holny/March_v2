package com.hly.march2.exception;

/**
 * 自定义异常类
 */
public class SysException extends Exception {
    // 存储提示信息
    private String msg;

    // 状态码 100-成功，200-失败
    private Integer code;

    private String errorUrl;

    private String redirectUrl;

    private String responseType = RESPONSE_TYPE_MV;

    public static final String RESPONSE_TYPE_JSON =  "Json";

    public static final String RESPONSE_TYPE_MV =  "ModelAndView";


    public SysException(String msg) {
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseJson() {
        this.responseType = RESPONSE_TYPE_JSON;
    }

    public void setResponseModelAndView() {
        this.responseType = RESPONSE_TYPE_MV;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}
