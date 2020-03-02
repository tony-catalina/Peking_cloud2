package awd.mis.servers.entity;

import java.io.Serializable;

/**
 * @author WS
 * @Description: 消息体
 * @date 2019/11/14 下午2:18
 */
public class MessageContent implements Serializable {
	//表单实体
	private String entity;
    //JSBH
    private String jsbh;

    //档案号
    private String dah;

    // 监室号
    private String jsh;
    
    // 人员编号
    private String rybh;

    //发送者
    private String sender;

    //消息内容
    private String content;
    
    
    public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

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

    public String getRybh() {
		return rybh;
	}

	public void setRybh(String rybh) {
		this.rybh = rybh;
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
		return "{"
				+ "\"jsbh\":\"" + jsbh + "\", "
				+ "\"dah\":\"" + dah + "\", "
				+ "\"jsh\":\"" + jsh + "\", "
				+ "\"rybh\":\"" + rybh + "\", "
				+ "\"sender\":\"" + sender + "\", "
				+ "\"content\":\"" + content + "\""
			+ "}";
	}
    
    
}
