package com.boco.eoms.rule.cwmsysruleconfig.drools;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * KieSession获取
 * @author chenjianghe
 *
 */
@Component
public class KieSessionHelper {
	
	@Autowired
	private RuleLoaderNew ruleLoader;
	
	/**
	 * 获取KieSession
	 * @param ruleId
	 * @return
	 */
	public KieSession getKieSessionByRuleId(String ruleId) {
        return ruleLoader.getKieContainerByRuleId(ruleId).getKieBase().newKieSession();
    }
	
}
