package com.boco.eoms.rule.cwmsysdatadict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.boco.eoms.rule.cwmsysdatadict.model.CwmDataDict;
import org.springframework.stereotype.Repository;

/**
 * 数据字典mapper
 * 2019年7月17日下午3:18:13
 *ssh
 */
@Repository
public interface CwmDataDictMapper {
    int deleteByPrimaryKey(String id);

    int insert(CwmDataDict record);

    void batchInsert(List<CwmDataDict> cwmDataDictList);

    /**
     * 数据字典集合批量修改
     * @param cwmDataDictList
     */
    void batchUpdate(List<CwmDataDict> cwmDataDictList);

    CwmDataDict selectByPrimaryKey(String id);

    int updateByPrimaryKey(CwmDataDict record);
    
    /**
     * 字典数据的条件查询
     * @param dictCnName
     * @param parentDictId
     * @return
     */
    List<CwmDataDict> selectByCondition(@Param("dictCnName")String dictCnName, @Param("parentDictId")String parentDictId);
}