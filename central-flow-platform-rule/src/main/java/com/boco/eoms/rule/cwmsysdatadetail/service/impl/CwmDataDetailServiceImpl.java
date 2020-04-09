package com.boco.eoms.rule.cwmsysdatadetail.service.impl;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import com.boco.eoms.base.excel.ExcelImportCheckUtil;
import com.boco.eoms.base.util.ReturnJsonUtil;
import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.mapper.CwmRuleGroupLibraryRelMapper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.base.util.excel.ExcelUtil;
import com.boco.eoms.rule.cwmsysdatadetail.mapper.CwmDataDetailMapper;
import com.boco.eoms.rule.cwmsysdatadetail.model.CwmDataDetail;
import com.boco.eoms.rule.cwmsysdatadetail.service.ICwmDataDetailService;

/**

* 创建时间：2019年7月2日 上午10:31:07

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
@Service("cwmDataDetailService")
public class CwmDataDetailServiceImpl implements ICwmDataDetailService{
	
	@Autowired
	private CwmDataDetailMapper cwmDataDetailMapper;
	@Autowired
    private CwmRuleGroupLibraryRelMapper cwmRuleGroupLibraryRelMapper;

    private Logger logger = LoggerFactory.getLogger(CwmDataDetailServiceImpl.class);

	@Transactional
	public JSONObject deleteByCondition(String id,String dataModuleId,String fieldEnName){
        //查询计算变量的表达式
        List<String> fieldCalculateStr = cwmDataDetailMapper.selectByModuleId(dataModuleId);
        //判断字段是否已在计算变量里
        boolean isUsed = StaticMethod.ifInclude(fieldCalculateStr,fieldEnName);
        //失败信息
        String failInfo = "删除失败,该字段在";
        if(isUsed){
            failInfo+="计算变量,";
        }
        //查询在规则库关联里的使用情况
        int dataModuleNum = cwmRuleGroupLibraryRelMapper.selectNumByDataModuleId(dataModuleId,fieldEnName);
        if(!isUsed&&dataModuleNum<=0){
            cwmDataDetailMapper.deleteByPrimaryKey(id);
            return ReturnJsonUtil.returnSuccessList("删除成功");
        }else{
            if(dataModuleNum>0){
                failInfo+="规则库";
            }
            failInfo+="里已使用";
            return ReturnJsonUtil.returnFailList(failInfo);
        }
	}

    /**
     * 批量操作数据详情信息
     * @param cwmDataDetailList
     * @return
     */
    @Transactional
    public JSONObject saveOperate(List<CwmDataDetail> cwmDataDetailList){
        JSONObject returnInfo = new JSONObject();
        if(cwmDataDetailList!=null&&cwmDataDetailList.size()>0){
            //新增的list
            List<CwmDataDetail> newList = new ArrayList<>();
            //更改的list
            List<CwmDataDetail> updateList = new ArrayList<>();
            for (int i = 0; i < cwmDataDetailList.size(); i++) {
                CwmDataDetail cwmDataDetail = cwmDataDetailList.get(i);
                //集合里第一个元素的id
                String id = StaticMethod.nullObject2String(cwmDataDetail.getId());
                if("".equals(id)){
                    newList.add(cwmDataDetail);
                }else{
                    updateList.add(cwmDataDetail);
                }
            }
            //批量插入的校验功能前端完成
            if(!newList.isEmpty()){
                returnInfo= ReturnJsonUtil.returnSuccessList("保存成功");
                cwmDataDetailMapper.batchInsert(newList);
            }
            if(!updateList.isEmpty()){
                //数据模块的id
                String dataModuleId = StaticMethod.nullObject2String(cwmDataDetailList.get(0).getDataModuleId());
                //查询计算变量的表达式
                List<String> fieldCalculateStr = cwmDataDetailMapper.selectByModuleId(dataModuleId);
                //失败内容
                StringBuilder failInfo = new StringBuilder();
                //规则集合关联库的失败信息
                StringBuilder groupRelInfo = new StringBuilder();
                //更改失败的字段
                StringBuilder fieldEnInfo = new StringBuilder();
                //计算变量相关的失败信息
                StringBuilder fieldCalculateInfo = new StringBuilder();
                groupRelInfo.append("字段");
                fieldCalculateInfo.append("字段");
                for(int i=0;i<updateList.size();i++){
                    CwmDataDetail cwmDataDetail = updateList.get(i);
                    String fieldEnName = cwmDataDetail.getFieldEnName();
                    String fieldCnName = cwmDataDetail.getFieldCnName();
                    String fieldType = cwmDataDetail.getFieldType();
                    //查询字段是否已经使用
                    int dataModuleNum = cwmRuleGroupLibraryRelMapper.selectNumByDataModuleId(dataModuleId,fieldEnName);
                    boolean isUsed = false;
                    if(fieldType.contains("变量")){
                        isUsed = StaticMethod.ifInclude(fieldCalculateStr,fieldEnName);
                        if(isUsed){
                            fieldCalculateInfo.append(fieldCnName+",");
                        }
                    }
                    if(dataModuleNum>0){
                        groupRelInfo.append(fieldCnName+",");
                    }
                    if(isUsed||dataModuleNum>0){
                        fieldEnInfo.append(fieldEnName+",");
                    }
                }
                //设置失败原因
                if(groupRelInfo.length()>2||fieldCalculateInfo.length()>2){
                    fieldEnInfo.deleteCharAt(fieldEnInfo.length()-1);
                    failInfo.append("更改失败,");
                    if(groupRelInfo.length()>2){
                        groupRelInfo.deleteCharAt(groupRelInfo.length()-1);
                        groupRelInfo.append("存在集合配置里;");
                        failInfo.append(groupRelInfo);
                    }
                    if(fieldCalculateInfo.length()>2){
                        fieldCalculateInfo.deleteCharAt(fieldCalculateInfo.length()-1);
                        fieldCalculateInfo.append("存在计算公式里");
                        failInfo.append(fieldCalculateInfo);
                    }

                    returnInfo.put("failField",fieldEnInfo);
                    returnInfo= ReturnJsonUtil.returnFailList(returnInfo,failInfo.toString());
                }else{
                    returnInfo= ReturnJsonUtil.returnSuccessList("保存成功");
                    cwmDataDetailMapper.batchUpdate(updateList);
                }
            }
        }
        return returnInfo;
    }

    @Transactional
    public void batchInsert(List<CwmDataDetail> cwmDataDetailList){
        cwmDataDetailMapper.batchInsert(cwmDataDetailList);
    }

	@Override
	@Transactional
	public int insert(CwmDataDetail record) throws Exception {
		// TODO Auto-generated method stub
		String id = StaticMethod.nullObject2String(record.getId());
		if("".equals(id)) {
			record.setId(UUIDHexGenerator.getInstance().getID());
		}
		return cwmDataDetailMapper.insert(record);
	}

	@Override
	public CwmDataDetail selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmDataDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int updateByPrimaryKey(CwmDataDetail record) {
		// TODO Auto-generated method stub
		return cwmDataDetailMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CwmDataDetail> selectByFieldCnName(String fieldCnName,String dataModuleId) {
		// TODO Auto-generated method stub
		return cwmDataDetailMapper.selectByFieldCnName(fieldCnName,dataModuleId);
	}

	@Override
	@Transactional
	public JSONObject upload(MultipartFile file, String dataModuleId) throws Exception{
        StringBuilder errorMsg = new StringBuilder();
        List<CwmDataDetail> cwmDataDetailList = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        //获取第一个sheet数据
        Sheet sheet = workbook.getSheetAt(0);
        //获取多少行
        int rows = sheet.getPhysicalNumberOfRows();
        //导入校验逻辑
        ExcelImportCheckUtil checkUtil = new ExcelImportCheckUtil();
        CwmDataDetail cwmDataDetail = null;

        //字段类型的字典值
        String[] fieldTypeValues = {"字符变量","时间变量","数字变量","计算变量"};

        //遍历每一行，注意：第 0 行为标题
        for (int j = 1; j < rows; j++) {
        	cwmDataDetail = new CwmDataDetail();
             //获得第 j 行
             Row row = sheet.getRow(j);
            //字段中文名的cell
            Cell fieldCnNameCell = row.getCell(0);
            //字段英文名的cell
            Cell fieldEnNameCell = row.getCell(1);
            //字段类型的cell
            Cell fieldTypeCell = row.getCell(2);
            //字段中文名
            String fieldCnName =  "";
            if(fieldCnNameCell!=null){
                fieldCnName=ExcelImportCheckUtil.getCellValue(fieldCnNameCell);
            }
            //字段英文名
            String fieldEnName =  "";
            if(fieldEnNameCell!=null){
                fieldEnName=ExcelImportCheckUtil.getCellValue(fieldEnNameCell);
                //判断字段英文名首字符是否为字母
                if(!"".equals(fieldEnName)&&!ExcelImportCheckUtil.checkStr(fieldEnName)){
                    fieldEnName="";
                }
            }
            //类型
            String fieldType = "";
            if(fieldTypeCell!=null){
                fieldType=ExcelImportCheckUtil.getCellValue(fieldTypeCell);
            }
            List<Map<String,Object>> checkRulesList = new ArrayList<Map<String,Object>>();
            //设置错误数据规则集
            checkRulesList.add(checkUtil.checkRulesIsNotNullMap("fieldCnName","字段中文名",fieldCnName,"不能为空，纯数字,日期"));
            checkRulesList.add(checkUtil.checkRulesIsNotNullMap("fieldEnName","字段英文名",fieldEnName,"不能为空，纯数字,日期，且开头只能为字母"));
            checkRulesList.add(checkUtil.checkRulesIsNotNullMap("fieldType","字段类型",fieldType,"不能为空"));
            //设置字典校验的规则集
            checkRulesList.add(checkUtil.checkRulesDictMap("fieldType","字段类型",fieldType,fieldTypeValues,"不符合字典规范"));

            //根据上述规则检查是否为空或字典值是否符合规范
            String msg = checkUtil.checkRules(checkRulesList);
            List<CwmDataDetail> list = cwmDataDetailMapper.selectByFieldCnName(fieldCnName,dataModuleId);
            if(!"".equals(msg)||(list!=null&&list.size()>0)) {
                if(list!=null&&list.size()>0){
                    errorMsg.append("第"+(j+1)+"行错误信息:"+ fieldCnName+"字段已经存在;");
                }
                if(!"".equals(msg)){
                    errorMsg.append(msg);
                }
            }
             cwmDataDetail.setId(UUIDHexGenerator.getInstance().getID());
             cwmDataDetail.setDataModuleId(dataModuleId);
             cwmDataDetail.setFieldCnName(fieldCnName);
             cwmDataDetail.setFieldEnName(fieldEnName);
             cwmDataDetail.setFieldType(fieldType);
             cwmDataDetailList.add(cwmDataDetail);
        }
        //只有上面校验不报错的情况下，才可以保存数据
        logger.info("数据详情导入校验errorMsg="+errorMsg);
        //如果append(null)，则它们的“底层源代码”会自动把null转换成字符串的"null"
        if(errorMsg.length()>0 && !"null".equals(errorMsg.toString())
                && !"".equals(errorMsg.toString())) {
            return ReturnJsonUtil.returnFailList(errorMsg.toString());
        }else {
            //批量插入数据
            cwmDataDetailMapper.batchInsert(cwmDataDetailList);
            return ReturnJsonUtil.returnSuccessList("插入成功");
        }
    }

	@Override
	public void download(String dataModuleId,HttpServletResponse response) throws Exception{
		HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个Excel表单,参数为sheet的名字
        HSSFSheet sheet = workbook.createSheet("数据详情");
        //初始化集合
        List<String> titleList = Stream.of("中文名", "英文名", "类型").collect(Collectors.toList());
        //创建表头
        ExcelUtil.setTitle(workbook, sheet,titleList);
        List<CwmDataDetail> cwmDataDetailList = cwmDataDetailMapper.getDataDetailByDataModuleId(dataModuleId);

        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for (CwmDataDetail cwmDataDetail:cwmDataDetailList) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(cwmDataDetail.getFieldCnName());
            row.createCell(1).setCellValue(cwmDataDetail.getFieldEnName());
            row.createCell(2).setCellValue(cwmDataDetail.getFieldType());
            rowNum++;
        }
        String fileName = "dataDetail.xls";
        //清空response
        response.reset();
        //设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename="+ fileName);
        response.setHeader("Access-Control-Allow-Origin", "*");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        //将excel写入到输出流中
        workbook.write(os);
        os.flush();
        os.close();
	}

	/**
     * 模版下载
     */
	@Override
	public void downloadTemplate(HttpServletResponse response) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个Excel表单,参数为sheet的名字
        HSSFSheet sheet = workbook.createSheet("导入模版");
        //初始化集合
        List<String> titleList = Stream.of("中文名(中文名不可重复)", "英文名(英文名不可重复)", "类型(类型包括字符变量、时间变量、数字变量、计算变量)").collect(Collectors.toList());
        //创建表头
        ExcelUtil.setTitle(workbook, sheet,titleList);

        String fileName = "dataTemplate.xlsx";
        //清空response
        response.reset();
        //设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename="+ fileName);
        response.setHeader("Access-Control-Allow-Origin", "*");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        //将excel写入到输出流中
        workbook.write(os);
        os.flush();
        os.close();
	}
}

