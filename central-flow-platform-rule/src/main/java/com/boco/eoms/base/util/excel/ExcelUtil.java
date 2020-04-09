package com.boco.eoms.base.util.excel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**

* 创建时间：2019年7月2日 下午4:36:12

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
public class ExcelUtil {
	 /***
     * 设置表头
     * @param workbook
     * @param sheet
     * @param titleList 表头集合
     */
    public static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet,List<String> titleList){
        HSSFRow row = sheet.createRow(0);
        if(titleList!=null&&titleList.size()>0) {
        	//设置为居中加粗
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
        	//设置列宽和标题，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
            HSSFCell cell;
        	for(int i=0;i<titleList.size();i++) {
        		sheet.setColumnWidth(i, 10*256);
        		cell = row.createCell(i);
                cell.setCellValue(titleList.get(i));
                cell.setCellStyle(style);
        	}
        }
    }
}

