package top.suvvm.nilmusic.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class LoginModel extends HttpRespModel {
    private Integer uid;

    @JSONField(name="uid")
    public Integer getUid() {
        return uid;
    }

    @JSONField(name="uid")
    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
