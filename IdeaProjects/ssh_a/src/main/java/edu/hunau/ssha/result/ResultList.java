package edu.hunau.ssha.result;
public class ResultList {

    private int code;

    private String msg;

    private Object obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public ResultList(int code, String msg, Object obj) {
        super();
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public static ResultList getBaseResult(int code, String msg, Object obj) {
        return new ResultList(code, msg, obj);
    }
}
