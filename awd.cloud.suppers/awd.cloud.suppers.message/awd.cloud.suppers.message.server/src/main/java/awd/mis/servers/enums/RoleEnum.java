package awd.mis.servers.enums;

/**
 * @author WS
 * @Description: 角色枚举
 * @date 2019/11/14 下午2:45
 */
public enum RoleEnum {
    KSSSY("看守所收押"),
    KSSGJ("看守所管教"),
    KSSCW("看守所财务");
    //角色名称
    private String comment;

    RoleEnum(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
