package com.boco.eoms.rule.cwmsysrulemodules.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.boco.eoms.rule.cwmsysruleapps.model.CwmRuleApps;
import com.boco.eoms.rule.cwmsysrulemodules.model.CwmRuleModules;
/**
 * 
 * 2019年5月27日下午3:12:22
 *ssh
 */
@Repository
public interface CwmRuleModulesMapper {
    int deleteByPrimaryKey(@Param("moduleId")String moduleId);

    int isExist(@Param("appId")String appId,@Param("moduleName")String moduleName);

    int insert(CwmRuleModules record);

    CwmRuleModules selectByPrimaryKey(String id);

    int updateByPrimaryKey(CwmRuleModules record);
    
    /**
     * 根据所属系统查询模块
     * @param appId
     * @return
     */
    public List<CwmRuleModules> selectByAppId(String appId);
    /**
     * 查询系统集合下的所有模块
     * @param appList
     * @return
     */
    public List<CwmRuleModules> selectByAppIds(List<String> appList);
}