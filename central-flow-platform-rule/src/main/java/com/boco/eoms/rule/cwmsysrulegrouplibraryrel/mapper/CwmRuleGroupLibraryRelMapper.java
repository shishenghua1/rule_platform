package com.boco.eoms.rule.cwmsysrulegrouplibraryrel.mapper;

import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.model.CwmRuleGroupLibraryRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CwmRuleGroupLibraryRelMapper {
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

    /**
     * 根据规则集合id查询
     * @param groupId
     * @return
     */
    List<CwmRuleGroupLibraryRel> selectByGroupId(String groupId);

    /**
     * 根据数据模块id查询数据
     * @param dataModuleId
     * @param fieldEnName 字段英文名
     * @return
     */
    int selectNumByDataModuleId(@Param("dataModuleId") String dataModuleId,@Param("fieldEnName")String fieldEnName);
}