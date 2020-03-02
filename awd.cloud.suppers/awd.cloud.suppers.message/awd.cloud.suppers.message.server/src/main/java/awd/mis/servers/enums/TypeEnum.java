package awd.mis.servers.enums;

/**
 * @Description
 * @Author WS
 * @Date 2019-04-30 14:05
 */
public enum TypeEnum {
    SWJ("SWJ", "上位机"),
    JCJ("JCJ", "监仓机"),
    JWP("JWP", "监外屏"),
    REPORT("REPORT", "报表"),
    FINGER("FINGER", "指纹"),
    KSS("KSS", "看守所"),
    JLS("JLS", "拘留所"),
    JDS("JDS", "戒毒所"),
    AKYY("AKYY", "安康医院"),
    JHXT("JHXT", "监护系统"),
    LOGS("LOGS", "日志"),
    CHARTS("CHATRS", "图形分析平台");
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    TypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
