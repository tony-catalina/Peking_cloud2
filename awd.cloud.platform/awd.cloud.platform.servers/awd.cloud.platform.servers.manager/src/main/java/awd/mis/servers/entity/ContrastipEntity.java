/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class ContrastipEntity extends SimpleGenericEntity<String> {

    //alias
    public static final String TABLE_ALIAS = "Contrastip";
    public static final String ALIAS_EQUIPMENTIP = "设备IP ";
    public static final String ALIAS_USERIP = "用户IP";
    public static final String ALIAS_DESCRIBE = "描述";

    //所有组
    @GroupSequence({CreateGroup.class, UpdateGroup.class})
    public interface All {
    }

    //columns START
    @Length(max = 15, message = "设备IP 最大长度15位", groups = CreateGroup.class)
    private String equipmentip;

    @Length(max = 15, message = "用户IP最大长度15位", groups = CreateGroup.class)
    private String userip;

    @Length(max = 255, message = "描述最大长度255位", groups = CreateGroup.class)
    private String describe;


    public ContrastipEntity() {
    }


    public String getEquipmentip() {
        return this.equipmentip;
    }

    public void setEquipmentip(String value) {
        this.equipmentip = value;
    }

    public String getUserip() {
        return this.userip;
    }

    public void setUserip(String value) {
        this.userip = value;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String value) {
        this.describe = value;
    }
}

