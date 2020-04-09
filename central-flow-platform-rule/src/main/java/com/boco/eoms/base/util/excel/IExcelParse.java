/*
 * IExcelParse.java
 * 
 * 2016-1-6 下午4:45:53
 * 
 * RecluseKapoor
 *  
 * Copyright © 2016, RecluseKapoor. All rights reserved.
 * 
 */
package com.boco.eoms.base.util.excel;

/**
 * @Title: recluse-Excel文件解析接口
 * 
 * @Description:Excel文件解析接口，所有版本的Excel解析类都要实现该接口
 * 
 * @Company: 卡普工作室
 * 
 * @Website: http://www.cnblogs.com/reclusekapoor/
 * 
 * @author: RecluseKapoor
 * 
 * @CreateDate：2018-3-21
 * 
 * @version: 1.0
 * 
 * @lastModify:
 * 
 */
public interface IExcelParse {
    public void loadExcel(String path) throws Exception;

    public String getSheetName(int sheetNo);

    public int getSheetCount() throws Exception;

    public int getRowCount(int sheetNo);

    public int getRealRowCount(int sheetNo);

    public String readExcelByRowAndCell(int sheetNo, int rowNo, int cellNo)
            throws Exception;

    public String[] readExcelByRow(int sheetNo, int rowNo) throws Exception;

    public String[] readExcelByCell(int sheetNo, int cellNo) throws Exception;

    public void close();
}