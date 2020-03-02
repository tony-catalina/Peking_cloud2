/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */
package awd.mis.activiti.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.activiti.entity.FlowNode;
import awd.mis.activiti.mapper.FlowNodeMapper;
import awd.mis.activiti.utils.CacheUtils;

@Service("flownodeService")
public class FlownodeService {

    @Autowired
    private FlowNodeMapper flowNodeMapper;

    @Autowired
    private RedisUtils redisUtils;

    public boolean saveNodeForMap(String mapkey, String flowmapid, List<FlowNode> flownodeEntity) {
        String jsbh = flownodeEntity.get(0).getJsbh();
        redisUtils.removePattern(CacheUtils.CACHE_FLOWNODE + jsbh + "_" + mapkey + "*");
        flownodeEntity.stream().forEach(p -> {
            FlowNode flownode = flowNodeMapper.selectByFlowMapIdAndNodeCode(flowmapid, p.getNodecode());
            String key = CacheUtils.CACHE_FLOWNODE + p.getJsbh() + "_" + p.getNodecode();
            p.setFlowmapid(flowmapid);
            if (flownode == null) {
                flowNodeMapper.insert(p);
            } else {
                p.setId(flownode.getId());
                flowNodeMapper.update(p);
            }
            redisUtils.set(key, JSONObject.toJSONString(p));
        });
        return false;

    }

    public int removeByPk(String id) {
        return flowNodeMapper.delete(id);
    }

    public int deletByFlowmapid(String flowmapid) {
        return flowNodeMapper.deleteForMapId(flowmapid);
    }

    public void cached() {
        List<FlowNode> list = flowNodeMapper.selectAll();
        for (FlowNode flownodeEntity : list) {
            String key = CacheUtils.CACHE_FLOWNODE + flownodeEntity.getJsbh() + "_" + flownodeEntity.getNodecode();
            redisUtils.set(key, JSONObject.toJSONString(flownodeEntity));
        }

    }

    public List<FlowNode> flownodeList() {
        return flowNodeMapper.selectAll();
    }


}
