package awd.mis.servers.enums;

/**
 * @author WS
 * @Description: 消息分类
 * @date 2019/11/14 下午1:44
 */

public enum ClassifyEnum {

    REMIND("REMIND", "提醒"),
    BUSINESS("BUSINESS", "业务变动");
	
    private String id;
    //消息类型 提醒 业务
    private String value;

    ClassifyEnum(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
    	return super.toString();
    }
}
