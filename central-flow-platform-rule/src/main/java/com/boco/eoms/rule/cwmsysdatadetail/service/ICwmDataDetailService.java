package com.boco.eoms.rule.cwmsysdatadetail.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.boco.eoms.rule.cwmsysdatadetail.model.CwmDataDetail;

/**

* 创建时间：2019年7月2日 上午10:28:30

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
public interface ICwmDataDetailService {
    /**
     * 数据的条件删除
     * @param id
     * @param dataModuleId
     * @param fieldEnName
     * @return
     */
    JSONObject deleteByCondition(String id,String dataModuleId,String fieldEnName);

    /**
     * 数据详情的批量插入
     * @param cwmDataDetailList
     * @return
     */
    void batchInsert(List<CwmDataDetail> cwmDataDetailList);

    /**
     * 批量操作数据详情内容
     * @param cwmDataDetailList
     * @return
     */
    JSONObject saveOperate(List<CwmDataDetail> cwmDataDetailList);

    int insert(CwmDataDetail record)  throws Exception;

    CwmDataDetail selectByPrimaryKey(String id);

    int updateByPrimaryKey(CwmDataDetail record);
    /**
     * 字段中文名 
     * @param fieldCnName
     * @return
     */
    List<CwmDataDetail> selectByFieldCnName(String fieldCnName,String dataModuleId);
    /**
     * 数据导入
     * @param file
     */
    JSONObject upload(MultipartFile file, String dataModuleId) throws Exception;
    /**
     * 全量数据导出
     * @param dataModuleId
     */
    void download(String dataModuleId,HttpServletResponse response)throws Exception;
    
    /**
     * 模版下载
     */
    void downloadTemplate(HttpServletResponse response)throws Exception;
}

