package com.boco.eoms.rule.cwmsysdatamodule.service.impl;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.ReturnJsonUtil;
import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.mapper.CwmRuleGroupLibraryRelMapper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.base.util.excel.ExcelUtil;
import com.boco.eoms.rule.cwmsysdatamodule.mapper.CwmDataModuleMapper;
import com.boco.eoms.rule.cwmsysdatamodule.model.CwmDataModule;
import com.boco.eoms.rule.cwmsysdatamodule.service.ICwmDataModuleService;

/**

* 创建时间：2019年7月2日 上午10:23:47

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：数据模块表

*/
@Service
public class CwmDataModuleServiceImpl implements ICwmDataModuleService{

	@Autowired
	private CwmDataModuleMapper cwmDataModuleMapper;
	@Autowired
	private CwmRuleGroupLibraryRelMapper cwmRuleGroupLibraryRelMapper;

	@Override
	@Transactional
	public JSONObject deleteByPrimaryKey(String id) {
		int dataModuleNum = cwmRuleGroupLibraryRelMapper.selectNumByDataModuleId(id,"");
		if(dataModuleNum<=0){
			cwmDataModuleMapper.deleteByPrimaryKey(id);
			return ReturnJsonUtil.returnSuccessList("删除成功");
		}else {
			return ReturnJsonUtil.returnFailList("删除失败,该规则库已经使用。");
		}
	}

	@Override
	@Transactional
	public JSONObject insert(CwmDataModule record) throws Exception {
		//获取当前用户
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		record.setCreateUserId(userId);
		record.setCreateTime(new Date());
		// TODO Auto-generated method stub
		String id = StaticMethod.nullObject2String(record.getId());
		if("".equals(id)) {
			record.setId(UUIDHexGenerator.getInstance().getID());
		}
		String dataModule = StaticMethod.nullObject2String(record.getDataModule());
		String moduleId = StaticMethod.nullObject2String(record.getModuleId());
		int dataModuleNum = cwmDataModuleMapper.isExist(dataModule,moduleId);
		if(dataModuleNum<=0){
			cwmDataModuleMapper.insert(record);
			return ReturnJsonUtil.returnSuccessList("保存成功");
		}
		return ReturnJsonUtil.returnFailList("保存失败,数据模块名字已经存在");
	}

	@Override
	public CwmDataModule selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmDataModuleMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public JSONObject updateByPrimaryKey(CwmDataModule record) {
		String id = StaticMethod.nullObject2String(record.getId());
		String dataModule = StaticMethod.nullObject2String(record.getDataModule());
		String moduleId = StaticMethod.nullObject2String(record.getModuleId());
		CwmDataModule cwmDataModule = cwmDataModuleMapper.selectByPrimaryKey(id);
		if(cwmDataModule !=  null) {
			String dataModuleSql = StaticMethod.nullObject2String(cwmDataModule.getDataModule());
			if(!dataModule.equals(dataModuleSql)) {
				int dataModuleNum = cwmDataModuleMapper.isExist(dataModule,moduleId);
				if(dataModuleNum<=0){
					cwmDataModuleMapper.insert(record);
					return ReturnJsonUtil.returnSuccessList("保存成功");
				}else {
					return ReturnJsonUtil.returnFailList("保存失败,数据模块名字已经存在");
				}
			}else {
				return ReturnJsonUtil.returnSuccessList("更新成功");
			}
		}
		int dataModuleNum = cwmRuleGroupLibraryRelMapper.selectNumByDataModuleId(record.getId(),"");
		if(dataModuleNum<=0){
			cwmDataModuleMapper.updateByPrimaryKey(record);
			return ReturnJsonUtil.returnSuccessList("更新成功");
		}else {
			return ReturnJsonUtil.returnFailList("更新失败,该规则库已经使用。");
		}
	}

	@Override
	public List<CwmDataModule> selectByDataModule(String dataModule) {
		// TODO Auto-generated method stub
		return cwmDataModuleMapper.selectByDataModule(dataModule);
	}

	@Override
	public void download(HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个Excel表单,参数为sheet的名字
        HSSFSheet sheet = workbook.createSheet("数据模块表");
        //初始化集合
        List<String> titleList = Stream.of("数据所属模块", "数据版本", "描述").collect(Collectors.toList());
        //创建表头
        ExcelUtil.setTitle(workbook, sheet,titleList);
        List<CwmDataModule> cwmDataModuleList = cwmDataModuleMapper.findAll();

        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for (CwmDataModule cwmDataModule:cwmDataModuleList) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(cwmDataModule.getDataModule());
            row.createCell(1).setCellValue(cwmDataModule.getDataVersion());
            row.createCell(2).setCellValue(cwmDataModule.getDescription());
            rowNum++;
        }
        String fileName = "dataModule.xlsx";
        //清空response  
        response.reset();  
        //设置response的Header  
        response.addHeader("Content-Disposition", "attachment;filename="+ fileName);  
        OutputStream os = new BufferedOutputStream(response.getOutputStream());  
        response.setContentType("application/vnd.ms-excel;charset=gb2312"); 
        //将excel写入到输出流中
        workbook.write(os);
        os.flush();
        os.close();
	}

	@Override
	public List<CwmDataModule> selectByCondition(String moduleId, String dataType) {
		// TODO Auto-generated method stub
		return cwmDataModuleMapper.selectByCondition(moduleId, dataType);
	}

}

