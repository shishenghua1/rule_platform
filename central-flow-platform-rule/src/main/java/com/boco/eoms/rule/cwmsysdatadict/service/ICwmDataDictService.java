package com.boco.eoms.rule.cwmsysdatadict.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.rule.cwmsysdatadict.model.CwmDataDict;

/**

* 创建时间：2019年7月17日 下午3:23:32

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
public interface ICwmDataDictService {

    JSONObject deleteByCondition(String id, String dataModuleId, String dictEnName);

    /**
     * 数据字典的批量插入
     * @param cwmDataDictList
     */
    void batchInsert(List<CwmDataDict> cwmDataDictList);

    /**
     * 批量操作数据字典
     * @param cwmDataDictList
     * @return
     */
    JSONObject saveOperate(List<CwmDataDict> cwmDataDictList);

    int insert(CwmDataDict record) throws Exception;

    CwmDataDict selectByPrimaryKey(String id);

    int updateByPrimaryKey(CwmDataDict record);
    /**
     * 字典数据的条件查询
     * @param dictCnName
     * @param parentDictId
     * @return
     */
    List<CwmDataDict> selectByCondition(String dictCnName, String parentDictId);
}

