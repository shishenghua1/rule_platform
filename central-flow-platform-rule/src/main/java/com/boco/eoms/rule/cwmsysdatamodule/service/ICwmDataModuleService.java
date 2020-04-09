package com.boco.eoms.rule.cwmsysdatamodule.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.rule.cwmsysdatamodule.model.CwmDataModule;

/**

* 创建时间：2019年7月2日 上午10:18:27

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
public interface ICwmDataModuleService {
     JSONObject deleteByPrimaryKey(String id);

     JSONObject insert(CwmDataModule record) throws Exception;

     CwmDataModule selectByPrimaryKey(String id);

     JSONObject updateByPrimaryKey(CwmDataModule record);
     /**
      * 条件查询
      * @param dataModule 数据所属模块
      * @return
      */
     List<CwmDataModule> selectByDataModule(String dataModule);
     
     /**
      * 根据模块分类id和数据类型查询数据
      * @param moduleId
      * @param dataType
      * @return
      */
     List<CwmDataModule> selectByCondition(String moduleId,String dataType);
     
     /**
      * 全量数据导出
      */
     void download(HttpServletResponse response)throws Exception;
}

