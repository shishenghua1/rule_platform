package com.boco.eoms.rule.cwmsysrulegrouplibraryrel.service;

import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.model.CwmRuleGroupLibraryRel;

import java.util.List;

/**
 * @author ssh
 * @description:
 * @date 2019/8/2814:34
 */
public interface ICwmRuleGroupLibraryRelService {
    /**
     * 规则集合库关联的批量插入
     * @param list
     */
    void batchInsert(List<CwmRuleGroupLibraryRel> list);

    /**
     * 规则集合库关联的删除
     * @param groupId
     */
    void deleteByGroupId(String groupId);
}
