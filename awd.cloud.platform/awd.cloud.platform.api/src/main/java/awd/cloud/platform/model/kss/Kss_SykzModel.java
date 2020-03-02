package awd.cloud.platform.model.kss;

import awd.bj.kss.model.JbxxModel;
import awd.cloud.platform.entity.JbxxEntity;
import awd.cloud.platform.model.Model;

public class Kss_SykzModel implements Model {
    private JbxxModel jbxxEntity;

    public JbxxModel getJbxxEntity() {
        return jbxxEntity;
    }

    public void setJbxxEntity(JbxxModel jbxxEntity) {
        this.jbxxEntity = jbxxEntity;
    }
}
