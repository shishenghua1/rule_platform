package com.boco.eoms.rule.cwmsysruleconfig.service;

import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieSession;

public interface LoadRuleService {

    /**
     * 加载规则
     */
    public void loadService(Map<String,String> map);
    
    public KieSession getKieSessionBySceneId(String id);

    /**
     * 获取所有drl集合
     * @return
     */
    public Map<String,String> getAllDrl();

    /**
     * 获取drl集合
     * @return
     */
    public String getDrl(String groupId);
    
    /**
     * 加载单条规则
     * @param drl
     */
    public void loadService(String drl);
}
