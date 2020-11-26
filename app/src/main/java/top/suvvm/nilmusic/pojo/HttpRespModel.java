package top.suvvm.nilmusic.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class HttpRespModel {
    private Integer code;
    private String msg;

    @JSONField(name="code")
    public Integer getCode() {
        return code;
    }

    @JSONField(name="code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JSONField(name="msg")
    public String getMsg() {
        return msg;
    }

    @JSONField(name="msg")
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
