package com.yali.vilivili.controller.base;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @Description 操作结果
 * @Date 2022/12/22 11:02
 * @Author pq
 */
public class OR<T> {
    private int httpStatus;
    private String code;
    private String msg;
    private T data;

    @ApiModelProperty("the error message")
    private List<String> errorMsg;

    public OR() {
        this.httpStatus = HttpStatus.OK.value();
        this.code = "";
    }

    public OR(boolean result) {
        this.httpStatus = HttpStatus.OK.value();
        this.code = "";
        this.errorMsg = Lists.newArrayList();
    }


    public OR<T> setData(T data) {
        this.data = data;
        return this;
    }

    public OR<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public OR<T> setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
        return this ;
    }



    public void setMsg(String msg) {
        this.msg = msg;
    }




    public int getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }


    public static class CommonCodes {
        public static final String RESULT_OK = "200";
        public static final String GENERAL_FAIL = "400";
        public static final String NO_LOGIN = "401";
        public static final String NO_ALLOW = "403";

        public static final String REAULT_FAIL = "500";
        public static final String INPUT_NOT_VALID = "505";
        public static final String ILLEGAL_FILETYPE = "601";
        public static final String ILLEGAL_FILECONTENT = "602";
        public static final String CAPTCHA_ERROR = "900";


        public CommonCodes() {
        }
    }


    public OR<T> fail(String message) {
        this.httpStatus = HttpStatus.BAD_REQUEST.value();
        this.msg = message;
        return this;
    }

}
