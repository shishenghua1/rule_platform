package com.boco.eoms.rule.cwmsysdatamodule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.boco.eoms.rule.cwmsysdatamodule.model.CwmDataModule;
/**
 * 
 * 2019年7月2日上午10:06:03
 *ssh
 */
@Repository
public interface CwmDataModuleMapper {
    int deleteByPrimaryKey(String id);

    int insert(CwmDataModule record);

    CwmDataModule selectByPrimaryKey(String id);

    int updateByPrimaryKey(CwmDataModule record);

    /**
     * 判断数据库是否存在指定名字的数据
     * @param dataModule
     * @param moduleId
     * @return
     */
    int isExist(@Param("dataModule")String dataModule,@Param("moduleId")String moduleId);
    /**
     * 条件查询
     * @param dataModule 数据所属模块
     * @return
     */
    List<CwmDataModule> selectByDataModule(@Param("dataModule")String dataModule);
    /**
     * 查询全量数据
     * @return
     */
    List<CwmDataModule> findAll();
    /**
     * 根据模块分类id查询数据
     * @param moduleId
     * @return
     */
    List<CwmDataModule> selectByModuleId(String moduleId);
    
    /**
     * 根据模块分类id和数据类型查询数据
     * @param moduleId
     * @param dataType
     * @return
     */
    List<CwmDataModule> selectByCondition(@Param("moduleId")String moduleId,@Param("dataType")String dataType);
}