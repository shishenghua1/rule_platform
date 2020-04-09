package com.boco.eoms.rule.cwmsysrulegroups.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;
/**
 * 
 * 2019年5月28日上午8:38:23
 *ssh
 */
@Repository
public interface CwmRuleGroupsMapper {

    int isExist(@Param("groupName")String groupName,@Param("moduleId")String moduleId);

    int deleteByPrimaryKey(@Param("id")String id);

    int insert(CwmRuleGroups record);

    CwmRuleGroups selectByPrimaryKey(String id);
    
    /**
     * 查询开启的规则
     * @param id
     * @return
     */
    CwmRuleGroups selectEnableRuleByRuleId(String id);

    int updateByPrimaryKey(CwmRuleGroups record);
    
    /**
     * 根据模块id查询规则集合
     * @param moduleId
     * @return
     */
    public List<CwmRuleGroups> selectByModuleId(@Param("moduleId") String moduleId,@Param("groupType")String groupType);
    /**
     * 规则id的集合信息
     * @param groupIdArr
     * @return
     */
    public List<CwmRuleGroups> queryByGroupIds(String[] groupIdArr);

//    /**
//     * 根据系统和模块查询规则集合
//     * @param appName 系统名称
//     * @param moduleName 模块名称
//     * @return
//     */
//    public List<CwmRuleGroups> selectByCondition(@Param("appName")String appName,
//    		@Param("moduleName")String moduleName,@Param("groupType")String groupType,@Param("draftFlag")String draftFlag);
    
    /**
     * 根据系统和模块查询规则集合
     * @param condition 条件map
     * @return
     */
    public List<CwmRuleGroups> selectByCondition(Map<String,Object> condition);
    
    /**
     * 获取所有规则集合
     * @return
     */
    public List<CwmRuleGroups> getAllRuleGroups();
    
    /**
     * 获取所有非草稿的规则集合
     * @return
     */
    public List<CwmRuleGroups> getAllRuleGroupsNonDraft();

    /**
     * 根据规则集合id判断是否存在装配里
     * @param id
     * @return
     */
    public int isExistInAssembling(String id);
}