package com.boco.eoms.rule.cwmsysruleatoms.service;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleatoms.model.CwmRuleAtoms;

/**

* 创建时间：2019年5月27日 下午3:14:53

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则平台原子规则业务接口层,基础方法为自动生成，不做说明

*/
public interface ICwmRuleAtomsService {

    int deleteByPrimaryKey(String id);

    int insert(CwmRuleAtoms record);

    CwmRuleAtoms selectByPrimaryKey(String id);

    int updateByPrimaryKey(CwmRuleAtoms record);
    
    /**
     * 查询所有原子规则
     * @return
     */
    public List<CwmRuleAtoms> selectAllAtoms();

}

