package com.boco.eoms.base.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

//import com.boco.eoms.base.tool.LogOutTool;


/**
 * @author xqz
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class XmlManage {
	  /**
	   * XML properties to actually get and set the BOCO properties.
	   */
	  private static XmlManage xManage = null;

	  /*
	   * 加载一个xml文件
	   * @param /filePath xml文件的相对路径，"/"代表web-inf/classess
	   */
	  public static XMLProperties getFile(String filePath) {
	  		xManage = new XmlManage();
//	  	String path = xManage.getClass().getResource(filePath).getPath();
	  	XMLProperties properties = new XMLProperties();
	  	//修改为读流创建xml对象 main方法无效
	  	InputStream is=XmlManage.class.getClassLoader().getResourceAsStream(filePath);
	  	if(is == null){
	  		is=XmlManage.class.getResourceAsStream(filePath);
	  	}
	  	properties.loadFile(is);
	    return properties;
	  }
	  
	  /*
	   * 解析schema
	   */
	  public static XMLProperties getSchema(String xmlSchema) {
	  		xManage = new XmlManage();
	  	XMLProperties properties = new XMLProperties();
	  	properties.loadSchema(xmlSchema);
	    return properties;
	  }

	  private XmlManage() {

	  }
	  
	  /**
	    * 读取一个节点下面所有子节点中的属性值
	    * @param fileXML文件名
	    * @param path一个以分号分割的字符串
	    * @param name一个以分号分割的字符串
	    * @return
	    */
	   public static List getProperty(String fileName,String path,String name){
		   //String filePath = XmlManage.class.getResource(fileName).getPath();
		   String[] xPath = parshString(path);
		   String[] keys = parshString(name);
			List list = new ArrayList(); 
			SAXBuilder builder=new SAXBuilder(false);
				try {
					InputStream is = XmlManage.class.getResourceAsStream(fileName);
					Document doc= builder.build(is);
					Element element=doc.getRootElement();
					list = getProperty(element,xPath,xPath.length,keys,keys.length);
				} catch (JDOMParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			return list;
	   }
	   
		/**
		 * 解析字符串的方法,以分号分割
		 * @param str
		 * @return
		 */
	   public static String[] parshString(String str){
			if(str!=null&&!str.equals("")){
				String[] result = str.split(";");
				return result;
			}else
				return null;
		}
	   
	   
	   /**
	    * 取指定节点下面所有节点的属性的方法
	    * @param ParentElement 根节点
	    * @param xPath 所要取的路径的长度
	    * @param flag 路径字符串的长度
	    * @param keys 所要取的属性值的字符串
	    * @param keyLength 属性值的长度
	    * @return
	    */
	   public static List getProperty(Element ParentElement,String[] xPath,int flag,String[] keys,int keyLength){
			String result = "";
			List list = new ArrayList();
			int index = xPath.length-flag;
			List parentList=ParentElement.getChildren(xPath[index]);
			for (Iterator iter = parentList.iterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				if(flag!=1){
					flag--;
					list = getProperty(element,xPath,flag,keys,keyLength);
				}else{
					Map map = new HashMap();
					for(int i=0;i<keyLength;i++){
						result = "";
						result=element.getAttributeValue(keys[i]);
						map.put(keys[i], result);
					}
					list.add(map);
				}
				
			}
			return list;
		}
	   
	   /**
	    * 根据id属性获取tableName
	    * @param filePath 表配置文件路径 
	    * @param modelName 实体类的名称
	    * @return String model 对应的tablename
	    *
	    */
	   public static String getTableName(String filePath,String modelName){
		   String tableName = "";
		   List propertyList = getProperty(filePath, "tableproperties;item", "id;tableName;tablePK");
		   if(propertyList!=null && propertyList.size()>0){
			   for(int i=0;i<propertyList.size();i++){
				   Map propertyMap = (Map)propertyList.get(i);
				   String id = StaticMethod.nullObject2String(propertyMap.get("id"));
				   if(modelName.equals(id)){
					   tableName = StaticMethod.nullObject2String(propertyMap.get("tableName"));
				   }
			   }
		   }else{
//			   LogOutTool.getLogger(XmlManage.class).info( "表属性配置文件为空，请配置！");
		   }
		   return tableName;
	   }
	   
	   /**
		    * 根据id属性获取table主键
		     * @param filePath 表配置文件路径 
		     * @param modelName 实体类的名称
		    * @return String model 对应的table 主键
		    *
		    */
	   public static String getTablePKName(String filePath,String modelName){
		   String pkName = "";
		   List propertyList = getProperty(filePath, "tableproperties;item", "id;tableName;tablePK");
		   if(propertyList!=null && propertyList.size()>0){
			   for(int i=0;i<propertyList.size();i++){
				   Map propertyMap = (Map)propertyList.get(i);
				   String id = StaticMethod.nullObject2String(propertyMap.get("id"));
				   if(modelName.equals(id)){
					   pkName = StaticMethod.nullObject2String(propertyMap.get("tablePK"));
				   }
			   }
		   }else{
//			   LogOutTool.getLogger(XmlManage.class).info( "表属性配置文件为空，请配置！");
		   }
		   return pkName;
	   }
	   
	   /**
		 * xml文件的写入
		 * @param doc
		 */
		public static void saveXML(Document doc,String writerFile) {
		      // 将doc对象输出到文件
			 FileOutputStream writer = null;
		      try {
		          // 创建xml文件输出流
		          XMLOutputter xmlopt = new XMLOutputter();
		          // 创建文件输出流
//		          FileWriter writer = new FileWriter(writerFile);
		          writer=new FileOutputStream(writerFile); 
		          // 指定文档格式
		          Format fm = Format.getPrettyFormat();
//		          fm.setEncoding("GBK"); //由于默认的编码是utf-8,中文将显示为乱码，所以设为gbk  
		          xmlopt.setFormat(fm); 
		          // 将doc写入到指定的文件中
		          xmlopt.output(doc, writer);
		      } catch (Exception e) {
		          e.printStackTrace();
		      } finally{
		    	  	try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		      }
		  }
		
	public static void main(String[] args) {
		String ip = StaticMethod.nullObject2String(XmlManage.getFile("/com/boco/eoms/sheet/activiti/config/activiti-config.xml").getProperty("ip"));
	}
}
