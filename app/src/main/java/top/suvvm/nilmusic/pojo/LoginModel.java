package top.suvvm.nilmusic.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class LoginModel extends HttpRespModel {
    private String uid;

    @JSONField(name="uid")
    public String getUid() {
        return uid;
    }

    @JSONField(name="uid")
    public void setUid(String uid) {
        this.uid = uid;
    }
}
