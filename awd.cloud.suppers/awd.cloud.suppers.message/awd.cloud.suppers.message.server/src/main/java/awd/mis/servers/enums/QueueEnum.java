package awd.mis.servers.enums;


/**
 * 队列配置枚举
 */
public enum QueueEnum {

    /**
     * 监外屏消息
     */
    AWD_REGISTER_JWP_MSG("awd.msg.jwp", "awd.queue.jwp"),
    
    /**
     * 分析统计、大屏消息
     */
    AWD_REGISTER_CHARTS_MSG("awd.msg.charts", "awd.queue.charts"),

    /**
     * 看守所消息
     */
    AWD_REGISTER_KSS_MSG("awd.msg.kss", "awd.queue.kss"),

    /**
     * 拘留所消息
     */
    AWD_REGISTER_JLS_MSG("awd.msg.jls", "awd.queue.jls"),
    
    /**
     * 戒毒所
     */
    AWD_REGISTER_JDS_MSG("awd.msg.jds", "awd.queue.jds"),

    /**
     * 日志消息
     */
    AWD_REGISTER_LOGS_MSG("awd.msg.log", "awd.queue.log"),
    
    /**
     * 上位机消息
     */
    AWD_REGISTER_SWJ_MSG("awd.msg.swj", "awd.queue.swj"),
    
	/**
	 * 平台消息
	 */
	AWD_REGISTER_PLATFORM_MSG("awd.msg.platform", "awd.queue.platform");


    /**
     * 队列名称
     */
    private String name;
    /**
     * 队列路由键
     */
    private String routingKey;

    public String getName() {
        return name;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    QueueEnum(String name, String routingKey) {
        this.name = name;
        this.routingKey = routingKey;
    }
}
