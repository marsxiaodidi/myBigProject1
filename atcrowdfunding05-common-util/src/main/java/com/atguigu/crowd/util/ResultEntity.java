package com.atguigu.crowd.util;

public class ResultEntity<T> {
    private static final  String SUCCESS = "SUCCESS";
    private static final  String FAILED = "FAILED";
    //data表示传递过去的数据
    private T data;
    //message表示如果有错误的错误信息
    private String message;
    //result表示结果是正确的还是错误的
    private String result;

    public ResultEntity(String result,String message,T data ) {
        this.data = data;
        this.message = message;
        this.result = result;
    }

    /**
     * 展现成功且返回数据的情况
     */
    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<E>(SUCCESS,null,data);

    }
    public static <E> ResultEntity<E> successWithOutDate() {
        return new ResultEntity<>(SUCCESS,null,null);

    }
    public static <E> ResultEntity<E> failed(String message){
        return new ResultEntity<>(FAILED,message,null);
    }

    public ResultEntity() {
    }

    public static String getSUCCESS() {
        return SUCCESS;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
