package com.boco.eoms.base.excel;

import com.boco.eoms.base.util.StaticMethod;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author sizhongyuan
 * @Description excel导入工具类，用于规范导入校验返回信息
 * @Date 2019-08-23
 */
public class ExcelImportCheckUtil {
	
	/**
	 * 返回校验必填项参数
	 * @param coulmn 校验字段名称
	 * @param cnName 校验字段中文名称
	 * @param value 校验字段值
	 * @param message 校验失败返回信息 有默认值 可为空
	 * @return Map<String,Object> map
	 * */
	public Map<String,Object> checkRulesIsNotNullMap(String coulmn,String cnName,Object value,String message) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("coulmn", coulmn);
		map.put("cnName", cnName);
		map.put("value", value);
		map.put("type", "isNotNull");
		map.put("message", "".equals(message)?"不能为空":message);
		return map;
	}
	/**
	 * 返回校验时间参数
	 * @param coulmn 校验字段名称
	 * @param cnName 校验字段中文名称
	 * @param value 校验字段值
	 * @param message 校验失败返回信息 有默认值 可为空
	 * @return Map<String,Object> map
	 * */
	public Map<String,Object> checkRulesDateMap(String coulmn,String cnName,Date value,String message) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("coulmn", coulmn);
		map.put("cnName", cnName);
		map.put("value", value);
		map.put("type", "date");
		map.put("message", "".equals(message)?"时间格式不正确":message);
		return map;
	}
	
	/**
	 * 返回校验字典参数
	 * @param coulmn 校验字段名称
	 * @param cnName 校验字段中文名称
	 * @param value 校验字段值
	 * @param dictVaules 字典值数组
	 * @param message 校验失败返回信息 有默认值 可为空
	 * @return Map<String,Object> map
	 * */
	public Map<String,Object> checkRulesDictMap(String coulmn,String cnName,Object value,String[] dictVaules,String message) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("coulmn", coulmn);
		map.put("cnName", cnName);
		map.put("value", value);
		map.put("type", "dict");
		map.put("dictVaules", dictVaules);
		map.put("message", "".equals(message)?"不符合字典规范":message);
		return map;
	}
	/**
	 * 校验规则执行方法
	 * @param checkRulesList 规则集合
	 * @return errorMsg 返回错误信息，若没错误 则为空
	 * */
	public String checkRules(List<Map<String,Object>> checkRulesList) {
		StringBuffer errorMsg = new StringBuffer();
		
		for(Map<String,Object> obj : checkRulesList) {
			String type = StaticMethod.nullObject2String(obj.get("type"));
			if("isNotNull".equals(type)) {
				if(obj.get("value") == null || "".equals(obj.get("value"))) {
					errorMsg.append(obj.get("cnName")).append(obj.get("message")).append(",");
				}
			}else if("dict".equals(type)) {//字典类型，默认要判空
				String[] dictVaules = (String[])obj.get("dictVaules");
				if(!Arrays.asList(dictVaules).contains(StaticMethod.nullObject2String(obj.get("value")))) {
					errorMsg.append(obj.get("cnName")).append(obj.get("message")).append(",");
				}
			}else if("date".equals(type)){
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				try {
					formatter.format(obj.get("value"));
				} catch (Exception e) {
					errorMsg.append(obj.get("cnName")).append(obj.get("message")).append(",");
				}
			}else {
				errorMsg.append(obj.get("cnName")).append("无效的校验类型").append(",");
			}
		}
		
		if(!"".equals(errorMsg.toString())) {
			errorMsg.delete(errorMsg.length()-1,errorMsg.length());
		}
		return errorMsg.toString();
	}
	
	public static void main(String[] args) {
		//例子 但是跑不了 只能看
		ExcelImportCheckUtil checkUtil = new ExcelImportCheckUtil();
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("theme", "测试主题111");
		model.put("startDate", new Date());
		model.put("endDate", new Date());
		model.put("keyGuaranteeFlagName", "测试主题111");
		model.put("keyGuaranteeTypeName", "测试主题111");
		model.put("keyGuaranteeLevelName", "测试主题111");
		model.put("urgencyLevelName", "测试主题111");
		model.put("theme", "测试主题111");
		
		String[] keyGuaranteeFlagNameValues = {"业务重保","网络重保"};
		String[] keyGuaranteeTypeNameValues = {"省际","省内","地市"};
		String[] keyGuaranteeLevelNameValues = {"A级","B级"};
		String[] urgencyLevelNameValues = {"一般","特急","紧急"};
		
		List<Map<String,Object>> checkRulesList = new ArrayList<Map<String,Object>>();
		checkRulesList.add(checkUtil.checkRulesIsNotNullMap("theme","主题",model.get("theme"),"不能为空"));
		checkRulesList.add(checkUtil.checkRulesIsNotNullMap("startDate","重保开始时间",(Date)model.get("startDate"),"不能为空"));
		checkRulesList.add(checkUtil.checkRulesIsNotNullMap("endDate","重保结束时间",(Date)model.get("endDate"),"不能为空"));
		checkRulesList.add(checkUtil.checkRulesIsNotNullMap("keyGuaranteeFlagName","重保标识",model.get("keyGuaranteeFlagName"),"不能为空"));
		checkRulesList.add(checkUtil.checkRulesIsNotNullMap("keyGuaranteeTypeName","重保类型",model.get("keyGuaranteeTypeName"),"不能为空"));
		checkRulesList.add(checkUtil.checkRulesIsNotNullMap("keyGuaranteeLevelName","重保级别",model.get("keyGuaranteeLevelName"),"不能为空"));
		checkRulesList.add(checkUtil.checkRulesIsNotNullMap("urgencyLevelName","紧急程度",model.get("urgencyLevelName"),"不能为空"));
		
		checkRulesList.add(checkUtil.checkRulesDateMap("startDate","重保开始时间",(Date)model.get("startDate"),"时间格式不正确"));
		checkRulesList.add(checkUtil.checkRulesDateMap("endDate","重保结束时间",(Date)model.get("endDate"),"时间格式不正确"));
		
		checkRulesList.add(checkUtil.checkRulesDictMap("keyGuaranteeFlagName","重保标识",model.get("keyGuaranteeFlagName"),keyGuaranteeFlagNameValues,"不符合字典规范"));
		checkRulesList.add(checkUtil.checkRulesDictMap("keyGuaranteeTypeName","重保类型",model.get("keyGuaranteeTypeName"),keyGuaranteeTypeNameValues,"不符合字典规范"));
		checkRulesList.add(checkUtil.checkRulesDictMap("keyGuaranteeLevelName","重保级别",model.get("keyGuaranteeLevelName"),keyGuaranteeLevelNameValues,"不符合字典规范"));
		checkRulesList.add(checkUtil.checkRulesDictMap("urgencyLevelName","紧急程度",model.get("urgencyLevelName"),urgencyLevelNameValues,"不符合字典规范"));
		//根据上述规则检查是否为空或字典值是否符合规范
		String msg = checkUtil.checkRules(checkRulesList);
		System.out.println(msg);
		
	}


	/**
	 * excel读取除字符串类型外的数据设置为空的处理
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getCellValue(Cell cell) {
		String strCell = "";
		if (cell != null) {
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING://字符串类型
					strCell = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC://数字类型
					if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
						strCell = "";
					} else {
						strCell = "";
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN://boolean类型
					strCell = "";
					break;
				case Cell.CELL_TYPE_BLANK:
					strCell = "";
					break;
				default:
					strCell = "";
					break;
			}
		}
		if (strCell == null) {
			strCell = "";
		}
		return strCell.trim();
	}

	/**
	 * 检测字符串的第一个字符是否为字母
	 * @param str
	 * @return
	 */
	public static boolean checkStr(String str)
	{
		char c = str.charAt(0);
		int i =(int)c;
		if((i>=65&&i<=90)||(i>=97&&i<=122))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
