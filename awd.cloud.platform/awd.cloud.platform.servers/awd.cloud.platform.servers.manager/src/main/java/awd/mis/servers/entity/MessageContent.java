package awd.mis.servers.entity;

import java.io.Serializable;

public class MessageContent implements Serializable {
    //JSBH
    private String jsbh;

    //档案号
    private String dah;

    // 监室号
    private String jsh;

    //发送者
    private String sender;

    //消息内容
    private String content;

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public String getDah() {
        return dah;
    }

    public void setDah(String dah) {
        this.dah = dah;
    }

    public String getJsh() {
        return jsh;
    }

    public void setJsh(String jsh) {
        this.jsh = jsh;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "{jsbh=" + jsbh + ", dah=" + dah + ", jsh=" + jsh + ", sender=" + sender + ", content=" + content + "}";
    }
}
