package com.boco.eoms.rule.cwmsysruleatoms.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.boco.eoms.rule.cwmsysruleatoms.model.CwmRuleAtoms;
/**
 * 
 * 2019年5月27日下午3:11:43
 *ssh
 */
@Repository
public interface CwmRuleAtomsMapper {
    int deleteByPrimaryKey(String id);

    int insert(CwmRuleAtoms record);

    CwmRuleAtoms selectByPrimaryKey(String id);

    int updateByPrimaryKey(CwmRuleAtoms record);
    
    /**
     * 批量插入数据
     * @param cwmRuleAtomsList
     */
    void batchInsert(List<CwmRuleAtoms> cwmRuleAtomsList);
    
    /**
     * 根据原子id集合查询数据
     * @param atomIds
     * @return
     */
    List<CwmRuleAtoms> selectByAtomIds(List<String> atomIds);
    /**
     * 根据原子id集合删除数据
     * @param atomIds
     * @return
     */
    void delByAtomIds(List<String> atomIds);
    
    /**
     * 根据规则集合id查询原子规则集合
     * @param groupId
     * @return
     */
    public List<CwmRuleAtoms> selectByGroupId(String groupId);
    
    /**
     * 查询所有原子规则
     * @return
     */
    public List<CwmRuleAtoms> selectAllAtoms();
    
}