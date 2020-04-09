package com.boco.eoms.rule.cwmsysruleapps.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.boco.eoms.rule.cwmsysruleapps.model.CwmRuleApps;
import org.springframework.stereotype.Repository;

/**
 * 
 * 2019年5月27日下午3:14:01
 *ssh
 */
@Repository
public interface CwmRuleAppsMapper {
    int deleteByPrimaryKey(String id);

    int insert(CwmRuleApps record);

    CwmRuleApps selectByPrimaryKey(String id);

    int updateByPrimaryKey(CwmRuleApps record);
    
    /**
     * 根据规则场景名称查找场景
     * @param appName
     * @return
     */
    List<CwmRuleApps> selectByAppName(@Param("appName")String appName);
    
    /**
     * 根据创建人查找场景
     * @param appName
     * @return
     */
    List<CwmRuleApps> selectByUserId(@Param("createUserId")String createUserId);

    /**
     * 根据规则场景名字查询数量
     * @param appName
     * @return
     */
    int isExist(String appName);
}