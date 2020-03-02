/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.activiti.sevice;

import awd.framework.common.utils.StringUtils;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.activiti.entity.FlowMap;
import awd.mis.activiti.entity.FlowNode;
import awd.mis.activiti.entity.RequestParameters;
import awd.mis.activiti.mapper.FlowMapMapper;
import awd.mis.activiti.mapper.FlowNodeMapper;
import awd.mis.activiti.utils.CacheUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("flowmapService")
public class FlowmapService {

    @Autowired
    private FlowMapMapper flowMapMapper;
    @Autowired
    private FlowNodeMapper flowNodeMapper;
    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private RedisUtils redisUtils;

    public boolean initActiviti(String jsbh) {
        System.out.println(jsbh);
        List<RequestParameters> list = workflowService.listRequestParameters(jsbh);

		/*try{
			for(RequestParameters p:list) {
				System.err.println(p.toString());
				this.updateFlowMapFlowNodeCache(p);
			}
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}*/
        return true;
    }

    public void updateFlowMapFlowNodeCache(RequestParameters requestParameters) {

        String jsbh = requestParameters.getJsbh();
        //判断jsbh是否为空  空：保持不变 ，否则填充新第jsbh
        FlowMap entity = requestParameters.getFlowmapEntity();
        //mapkey查询flowMap
        FlowMap flowmapEntity = flowMapMapper.selectBymapkey(entity.getMapkey());
        System.err.println(flowmapEntity);
        // 如果为空则执行添加操作-------(更新或者保存，防止id重复)

        if (StringUtils.isNullOrEmpty(flowmapEntity)) {
            flowMapMapper.insert(entity);

        } else {
            entity.setId(flowmapEntity.getId());
            flowMapMapper.update(entity);

        }
        String key = CacheUtils.CACHE_FLOWMAP + entity.getMapkey();
        System.err.println(key);
        redisUtils.set(key, JSONObject.toJSONString(entity));

        // 流程图获取的节点信息
        List<FlowNode> nodeNew = requestParameters.getFlownodeEntityList();
		/*for (FlowNode fd : nodeNew) {
			 System.err.println("------1----->"+fd.toString());
		}*/
        // 数据库查询出来的节点信息信息
        List<FlowNode> nodeOld = flowNodeMapper.selectByFlowMapId(entity.getId());
        if (StringUtils.isNullOrEmpty(jsbh)) {
            jsbh = nodeOld.get(0).getJsbh();
        }
        //数据库没有则添加
        if (nodeOld.size() == 0) {

            for (FlowNode nnew : nodeNew) {

                nnew.setFlowmapid(entity.getId());

                flowNodeMapper.insert(nnew);

                //System.err.println("无数据插入--> id为" + id);

            }

        } else {

            // 新流程节点数量大于数据节点数量
            if (nodeOld.size() < nodeNew.size()) {

                for (FlowNode nnew : nodeNew) {

                    nnew.setFlowmapid(entity.getId());

                    boolean flag = true;

                    for (FlowNode old : nodeOld) {

                        if (nnew.getNodecode() != null && nnew.getNodecode().equals(old.getNodecode())) {

                            nnew.setId(old.getId());

                            flowNodeMapper.insert(nnew);

                            flag = false;
                        }
                    }
                    if (flag) {
                        flowNodeMapper.insert(nnew);
                    }
                }

            }

            // 新流程节点数量小于等于数据节点数量
            if (nodeOld.size() >= nodeNew.size()) {
                for (FlowNode old : nodeOld) {
                    boolean flag = true;

                    for (FlowNode nnew : nodeNew) {

                        if (nnew.getNodecode() != null && nnew.getNodecode().equals(old.getNodecode())) {

                            nnew.setFlowmapid(entity.getId());

                            nnew.setId(old.getId());
                            //jsbh
                            nnew.setJsbh(jsbh);
                            if (!StringUtils.isNullOrEmpty(nnew.getId()) && null != flowNodeMapper.selectById(nnew.getId())) {
                                System.err.println(nnew.toString());
                                flowNodeMapper.update(nnew);
                            } else {
                                flowNodeMapper.insert(nnew);
                            }
                            //	flowNodeMapper.saveOrUpdate(nnew);

                            flag = false;
                        }
                    }
                    if (flag) {
                        flowNodeMapper.delete(old.getId());
                        ;
                    }
                }

            }

        }
    }

    /**
     * @param
     * @Description: 流程图初始化缓存加载
     * @return: void
     * @Author: 王帅
     * @Date: 2018/4/26 11:33
     **/
    public void initFlowMapCache() {
        List<FlowMap> flowmapEntityList = flowMapMapper.selectList();
        String key = CacheUtils.CACHE_FLOWMAP;
        flowmapEntityList.stream()
                .filter(p -> "".equals(redisUtils.get(key + p.getJsbh() + "_" + p.getMapkey())))
                .forEach(p -> redisUtils.set(key + p.getJsbh() + "_" + p.getMapkey(), JSONObject.toJSONString(p)));
    }

    public String deleteByMapname(String mapname) {
        FlowMap fm = flowMapMapper.selectBymapName(mapname);
        //flowMapID
        String id = fm.getId();
        int i = flowMapMapper.delete(id);
        ;
        if (!(i > 0)) {
            return "";
        }
        int j = flowNodeMapper.deleteForMapId(id);
        if (!(j > 0)) {
            return "";
        }
        return id;
    }

    //添加或者更新FlowMap
    protected String saveOrUpdate(FlowMap entity) {
        if (!StringUtils.isNullOrEmpty(entity.getId()) && !StringUtils.isNullOrEmpty(flowMapMapper.selectById(entity.getId()))) {
            flowMapMapper.update(entity);
        } else {
            flowMapMapper.insert(entity);
        }

        return entity.getId();
    }

    public List<FlowMap> flowMapList() {
        return flowMapMapper.selectList();
    }
}
