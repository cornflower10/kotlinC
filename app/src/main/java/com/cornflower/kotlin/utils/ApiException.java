package com.cornflower.kotlin.utils;

/**
 * Created by xiejingbao on 2018/3/20.
 */

public class ApiException extends Exception {
    private String code;
    private String displayMessage;

    public ApiException(String code, String displayMessage) {
        super(displayMessage);
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public ApiException(String displayMessage) {
        super(displayMessage);
        this.displayMessage = displayMessage;
    }

    public ApiException(String code, String message, String displayMessage) {
        super(message);
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }


}
