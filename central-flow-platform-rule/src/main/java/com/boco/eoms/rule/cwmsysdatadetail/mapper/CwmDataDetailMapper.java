package com.boco.eoms.rule.cwmsysdatadetail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.boco.eoms.rule.cwmsysdatadetail.model.CwmDataDetail;
/**
 * 
 * 2019年7月2日上午10:05:55
 *ssh
 */
@Repository
public interface CwmDataDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(CwmDataDetail record);

    CwmDataDetail selectByPrimaryKey(String id);

    int updateByPrimaryKey(CwmDataDetail record);
    
    /**
     * 字段中文名 
     * @param fieldCnName
     * @return
     */
    List<CwmDataDetail> selectByFieldCnName(@Param("fieldCnName")String fieldCnName,@Param("dataModuleId")String dataModuleId);
    /**
     * 批量插入数据
     * @param cwmDataDetailList
     */
    void batchInsert(List<CwmDataDetail> cwmDataDetailList);

    /**
     *批量更新数据详情
     * @param cwmDataDetailList
     */
    void batchUpdate(List<CwmDataDetail> cwmDataDetailList);

    /**
     * 
     * @param dataModuleId
     * @return
     */
    List<CwmDataDetail> getDataDetailByDataModuleId(@Param("dataModuleId")String dataModuleId);
    
    /**
     * 根据模块id和字段英文名查询数据详情
     * @param moduleId
     * @param fieldEnName
     * @return
     */
    public List<CwmDataDetail> getDataDetailByModuleIdAndEnName(@Param("moduleId") String moduleId,@Param("fieldEnName")String fieldEnName);

    /**
     * 根据数据模块id查询变量类型里的计算公式
     * @param dataModuleId
     * @return
     */
    List<String> selectByModuleId(String dataModuleId);
}