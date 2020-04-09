package com.boco.eoms.base.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.alibaba.fastjson.JSONObject;
//import com.boco.eoms.base.tool.LogOutTool;


/** 
 * Description: 静态方法转换类
 * Copyright:   Copyright (c)2017
 * Company:     BOCO 
 * @author:     szy
 * @version:    1.0 
 * Create at:   2017-04-10
 */
public class StaticMethod {
	
	/**
	 * classpath标识
	 */
	public final static String CLASSPATH_FLAG = "classpath:";
	
	/**
	 * 字符转换函数
	 * @param s 需要转换的对象
	 * @return str:如果字符串为null,返回为空,否则返回该字符串
	 */
	public static String nullObject2String(Object s) {
		String str = "";
		try {
			str = s.toString();
		} catch (Exception e) {
			str = "";
		}
		return str;
	}
	
	/**
	 * 将一个对象转换为String
	 * 
	 * @param s
	 * @param chr
	 * @return
	 */
	public static String nullObject2String(Object s, String chr) {
		String str = chr;
		try {
			str = s.toString();
		} catch (Exception e) {
			str = chr;
		}
		return str;
	}
	
	/**
	 * 将一个对象转换为整形
	 * 
	 * @param s
	 * @return
	 */
	public static int nullObject2int(Object s) {
		String str = "";
		int i = 0;
		try {
			str = s.toString();
			i = Integer.parseInt(str);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}
	
	/**
	 * 将对象转换为整形
	 * 
	 * @param s
	 * @param in
	 * @return
	 */
	public static int nullObject2int(Object s, int in) {
		String str = "";
		int i = in;
		try {
			str = s.toString();
			i = Integer.parseInt(str);
		} catch (Exception e) {
			i = in;
		}
		return i;
	}
	
	/**
	 * 对象转换为long型
	 * 
	 * @param s
	 * @return
	 */
	public static long nullObject2Long(Object s) {
		long i = 0;

		String str = "";
		try {
			str = s.toString();
			i = Long.parseLong(str);
		} catch (Exception e) {
			i = 0;
		}

		return i;
	}

	/**
	 * 将对象转换为long,如果无法转换则返回temp
	 * 
	 * @param s
	 * @param temp
	 * @return
	 */
	public static long nullObject2Long(Object s, long temp) {
		long i = temp;

		String str = "";
		try {
			str = s.toString();
			i = Long.parseLong(str);
		} catch (Exception e) {
			i = temp;
		}

		return i;
	}
	
	/**
	 * 
	 * 时间转换方法：根据输入的格式(String _dtFormat)得到当前时间格式得到当前的系统时间 Add By ChengJiWu
	 * 
	 * @param _dtFormat
	 * @return
	 */
	public static String getCurrentDateTime(String _dtFormat) {
		String currentdatetime = "";
		try {
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat dtFormat = new SimpleDateFormat(_dtFormat);
			currentdatetime = dtFormat.format(date);
		} catch (Exception e) {
//			LogOutTool.getLogger(StaticMethod.class).info("时间格式不正确");
			e.printStackTrace();
		}
		return currentdatetime;
	}

	/**
	 * 时间转换方法：得到默认的时间格式为("yyyy-MM-dd HH:mm:ss")的当前时间
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		return getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @param strDate
	 *            时间型的字符串
	 * @param _dtFormat
	 *            形如"yyyy-MM-dd HH:mm:ss"的字符串 把 strDate 时间字符串 转换为 _dtFormat 格式
	 * @return
	 */
	public static String getCurrentDateTime(String strDate, String _dtFormat) {
		String strDateTime;
		Date tDate = null;
		if (null == strDate) {
			return getCurrentDateTime();
		}
		SimpleDateFormat smpDateFormat = new SimpleDateFormat(_dtFormat);
		ParsePosition pos = new ParsePosition(0);
		tDate = smpDateFormat.parse(strDate, pos); // 标准格式的date类型时间
		strDateTime = smpDateFormat.format(tDate); // 标准格式的String 类型时间
		return strDateTime;
	}
	
	/** 
	 	* 时间处理格式转换
		* @author chenjianghe
		* @version 版本1.0 
		* @param date 转换的时间
		* @return String 转换后的时间字符串 
	    * @throws 无
	    */
	public static String date2Str(Date date) {
		String ret = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			ret = dateFormat.format(date);
		} catch (Exception e) {
			try{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				ret = dateFormat.format(date);
			} catch (Exception ex) {
			}
			return ret;
		}
		return ret;
	}
	
	/** 
	 	* 时间处理格式转换
		* @author chenjianghe
		* @version 版本1.0 
		* @param date 转换的时间
		* @param format 转换的格式
		* @return String 转换后的时间字符串 
	    * @throws 无
	    */
	public static String date2Str(Date date,String format) {
		String ret = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					format);
			ret = dateFormat.format(date);
		} catch (Exception e) {
			try{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				ret = dateFormat.format(date);
			} catch (Exception ex) {
			}
			return ret;
		}
		return ret;
	}
	
	/**
		 * 将对象转换为float,如果无法转换则返回temp
		 * @param s
		 * @param temp
		 * @return
		 */
	public static float nullObject2Float(Object s, float temp) {
		float i = temp;
		String str = "";
		try {
			str = s.toString();
			i = Float.parseFloat(str);
		} catch (Exception e) {
			i = temp;
		}
		return i;
	}
	
	/**
	 * @see 字符处理方法：将首字符转换为大写
	 * @param string
	 * @return
	 */
	public static String firstToUpperCase(String string) {
		String post = string.substring(1, string.length());
		String first = ("" + string.charAt(0)).toUpperCase();
		return first + post;
	}
	
	/**
	 * @see 转换时间方法：转换格式，如时间“2002-1-12”转换成字符串“020112”
	 * @param DateStr
	 *            “2002-1-12”
	 * @return “020112”
	 */
	public static String getYYMMDD() {
		return getYYMMDD(getCurrentDateTime());
	}

	public static String getYYMMDD(String DateStr) {
		String YY, MM, DD;
		String ReturnDateStr;

		int s = DateStr.indexOf(" ");
		ReturnDateStr = DateStr.substring(0, s);

		Vector ss = getVector(ReturnDateStr, "-");
		YY = ss.elementAt(0).toString();
		YY = YY.substring(2, 4);
		MM = ss.elementAt(1).toString();
		if (Integer.valueOf(MM).intValue() < 10) {
			MM = "0" + Integer.valueOf(MM).intValue();
		}

		DD = ss.elementAt(2).toString();
		if (Integer.valueOf(DD).intValue() < 10) {
			DD = "0" + Integer.valueOf(DD).intValue();
		}
		ReturnDateStr = YY + MM + DD;

		return ReturnDateStr;
	}
	
	/**
	 * @see 得到一指定分隔符号分隔的vector 如：Vector nn =
	 *      StaticMethod.getVector("2003-4-5","-");
	 */
	public static Vector getVector(String string, String tchar) {
		StringTokenizer token = new StringTokenizer(string, tchar);
		Vector vector = new Vector();
		if (!string.trim().equals("")) {
			try {
				while (token.hasMoreElements()) {
					vector.add(token.nextElement().toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vector;
	}
	
	/**
	 * 取filePath的InputStream
	 * 
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 */
	public static InputStream getFileInputStream(String filePath)
			throws FileNotFoundException {
		InputStream inputStream = null;
		if (filePath != null) {
			if (filePath.length() > CLASSPATH_FLAG.length()) {
				if (CLASSPATH_FLAG.equals(filePath.substring(0, CLASSPATH_FLAG
						.length()))) {
					try {
						// inputStream = loader.getResourceAsStream(filePath
						// .substring(CLASSPATH_FLAG.length()));
						// inputStream = new FileInputStream(
						// getFilePathForUrl(filePath));
						inputStream = ClassLoaderUtil
								.getStream(getFileUrl(filePath));
					} catch (IOException e) {
						throw new FileNotFoundException(filePath
								+ " is not found!!!");
					}

				} else {

					inputStream = new FileInputStream(filePath);

				}
			}
		}
		return inputStream;
	}
	
	/**
	 * 获取filePath的url
	 * 
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 *             创建url失败将抛出MalformedURLException
	 */
	public static URL getFileUrl(String filePath) throws FileNotFoundException {
		URL url = null;
		if (filePath != null) {
			if (filePath.length() > CLASSPATH_FLAG.length()) {
				if (CLASSPATH_FLAG.equals(filePath.substring(0, CLASSPATH_FLAG
						.length()))) {
					try {
						url = StaticMethod.class.getClassLoader().getResource(
								getPathButClasspath(filePath));
						if(url == null ){
							url = StaticMethod.class.getResource(getPathButClasspath(filePath));
						}
						if(url == null){
							url = Thread.currentThread().getContextClassLoader().getResource(
									getPathButClasspath(filePath));
						}
						URL url1 = StaticMethod.class
								.getResource(getPathButClasspath(filePath));
						URL url2 = Thread.currentThread()
								.getContextClassLoader().getResource(
										getPathButClasspath(filePath));
//						LogOutTool.getLogger(StaticMethod.class).info("url===="+url.getFile());
					} catch (Exception e) {
						throw new FileNotFoundException(filePath
								+ "is not found.");
					}

				} else {
					// TODO 有问题，需修改
				}
			}
		}
		return url;
	}
	
	/**
	 * 去掉classpath
	 * 
	 * @param path
	 * @return
	 */
	private static String getPathButClasspath(String path) {
		return path.substring(CLASSPATH_FLAG.length());
	}
	
	/**
	 * 时间处理方法：
	 * 
	 * @param day
	 * @return
	 */
	public static String getLocalString(int day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, day);

		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(c.getTime());

		return date;
	}
	
	/**
	 * @see 得到当前时刻的时间字符串
	 * @return String para的标准时间格式的串,例如：返回'2003-08-09 16:00:00'
	 */
	public static String getLocalString() {
		java.util.Date currentDate = new java.util.Date();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(currentDate);

		return date;
	}
	
	/**
	 * @see 获取当前时间（Date类型）
	 * @author qinmin
	 * @return
	 */
	public static Date getLocalTime(){
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date1 = dateFormat.format(date);
		try {
			date = dateFormat.parse(date1);
		} catch (Exception e) {
//			LogOutTool.getLogger(StaticMethod.class).info( "获取当前时间报错!");
			e.printStackTrace();
		}
		return date;

	}
	
	/** 
	 	* 获取对象的属性已经属性值字符串 mybatis专用
		* @author chenjianghe
		* @version 版本1.0 
		* @param object 
		* @return map  key=column 是属性字符串 格式为 属性1，属性2;
		* 						 key=columnValue 是属性的value 格式为 #{属性1}，#{属性2}
		* 						 key=columnAndValue 是属性和value  格式为 属性1=#{属性1}，属性2=#{属性2}
	    * @throws 无
	    */
    public static Map<String,String> getObjColumnAndValue(Object obj){
    			//封装mybatis sql语句参数map
    			Map map = new HashMap();
    			//model转Map
    			Map beanMap = bean2Map(obj);
    			Set<String> keys = beanMap.keySet();
    			String column = "";
    			String columnValue = "";
    			String columnAndValue = "";
    			if(keys!=null && keys.size()>0){
    				for(String key:keys){
        				//LogOutTool.getLogger(StaticMethod.class).info( "key=="+key);
        				if("".equals(columnValue)){
        					column = key;
        					columnValue = "#{"+key+"}";
        					columnAndValue = key + "=" + "#{"+key+"}";
        				}else{
        					column = column + "," + key;
        					columnValue = columnValue + "," + "#{"+key+"}";
        					columnAndValue = columnAndValue + "," + key + "=" + "#{"+key+"}";
        				}
        			}
    			}else{
//    				LogOutTool.getLogger(StaticMethod.class).info( "未获取到任何属性");
    			}
    			map.put("column", column);
				map.put("columnValue", columnValue);
				map.put("columnAndValue", columnAndValue);
				return map;
    }
    
	/** 
	 	* bean 转map
		* @author chenjianghe
		* @version 版本1.0 
		* @param object 转换的bean
		* @return map 转换后的bean 的map
	    * @throws 无
	    */
	public static Map<String,Object> bean2Map(Object object){
		if(object == null){
			return null;
		}
		Map<String,Object>  map = new HashMap<String,Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor property:propertyDescriptors){
				String key = property.getName();
				// 过滤class属性 
				if(!key.equals("class")){
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(object);
					if(value == null){
						value = "";
					}
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return map;
	}


	/** 
	 	* 获取 bean 属性字符串
		* @author chenjianghe
		* @version 版本1.0 
		* @param obj bean 对象
		* @return String 逗号隔开的bean属性字符串
	    * @throws 无
	    */
	public static String getBeanFieldsArr(Object obj){
		
		Field superField[] = obj.getClass().getSuperclass().getDeclaredFields();
		Field field[] = obj.getClass().getDeclaredFields();
		String fieldStr = "";
		if(superField!=null && superField.length>0){
			for(int i=0;i<superField.length;i++){
				if("".equals(fieldStr)){
					fieldStr = superField[i].getName();
				}else{
					fieldStr = fieldStr + "," + superField[i].getName();
				}
			}
		}
		if(field!=null && field.length>0){
			for(int i=0;i<field.length;i++){
				if("".equals(fieldStr)){
					fieldStr = field[i].getName();
				}else{
					fieldStr = fieldStr + "," + field[i].getName();
				}
			}
		}
		return fieldStr;
	}
	
	/** 
	 	* map 转 bean （查询返回的map用，查询返回的map key都是大写，做了key的转换）
		* @author chenjianghe
		* @version 版本1.0 
		* @param obj bean 对象
		* @param map  查询的结果集map
		* @return String 逗号隔开的bean属性字符串
	    * @throws 无
	    */
	public static void map2Bean(Object bean, Map map) {
		if (bean == null || map == null)
			return;
		try {
			String beanPropertiesStr = getBeanFieldsArr(bean);
			String beanPropertiesArr[] = beanPropertiesStr.split(",");
			if(beanPropertiesArr!=null && beanPropertiesArr.length>0){
				for(String property:beanPropertiesArr){
					String upKey = property.toUpperCase();
					map.put(property, map.get(upKey));
					map.remove(upKey);
				}
			}
			ConvertUtils.register(new DateConverter(null), java.util.Date.class);
			BeanUtils.copyProperties(bean, map);
		} catch (Exception e) {
//			LogOutTool.getLogger(StaticMethod.class).info( "未获取到任何属性");
			e.printStackTrace();
		} 
	}
	/** 
 	* map 转 bean （查询返回的map用，查询返回的map key都是大写，做了key的转换）
	* @author chenjianghe
	* @version 版本1.0 
	* @param obj bean 对象
	* @param map  查询的结果集map
	* @param flag 是否需要删除转换前的数据
	* @return String 逗号隔开的bean属性字符串
    * @throws 无
    */
	public static void map2Bean(Object bean, Map map,boolean flag) {
		if (bean == null || map == null)
			return;
		try {
			String beanPropertiesStr = getBeanFieldsArr(bean);
			String beanPropertiesArr[] = beanPropertiesStr.split(",");
			if(beanPropertiesArr!=null && beanPropertiesArr.length>0){
				for(String property:beanPropertiesArr){
					String upKey = property.toUpperCase();
					map.put(property, map.get(upKey));
					if(flag){
						map.remove(upKey);
					}
				}
			}
			ConvertUtils.register(new DateConverter(null), java.util.Date.class);
			BeanUtils.copyProperties(bean, map);
		} catch (Exception e) {
//			LogOutTool.getLogger(StaticMethod.class).info( "未获取到任何属性");
			e.printStackTrace();
		} 
	}
	/** 
	 	* map 转 bean （普通的map 转bean  map的key 和字段的属性名相同）
		* @author chenjianghe
		* @version 版本1.0 
		* @param obj bean 对象
		* @param map  查询的结果集map
		* @return String 逗号隔开的bean属性字符串
	    * @throws 无
	    */
	public static void map2BeanNormal(Object bean, Map map) {
		if (bean == null || map == null)
			return;
		try {
			ConvertUtils.register(new DateConverter(null), java.util.Date.class);
			BeanUtils.copyProperties(bean, map);
		} catch (Exception e) {
//			LogOutTool.getLogger(StaticMethod.class).info( " Map 转换 Bean失败：");
			e.printStackTrace();
		} 
	}
	
	/** 
	 	* String 2 Date
		* @author chenjianghe
		* @version 版本1.0 
		* @param timeStr 时间字符串
		* @return Date
	    * @throws 无
	    */
	public static Date str2Date(String timeStr){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(timeStr);
			return date;
		} catch (Exception e) {
			// TODO: handle exception
			SimpleDateFormat sdfE = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				Date dateE = sdfE.parse(timeStr);
				return dateE;
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return new Date(timeStr);
			}
		}
	}
	
	/** 
	 	* jsonObject 合并
		* @author chenjianghe
		* @version 版本1.0 
		* @param jsonObjectList 需要合并的jsonObject集合
		* @return JSONObject
	    * @throws 无
	    */
     public static JSONObject jsonObjectMerge(List jsonObjectList){
    	 if(jsonObjectList!=null && jsonObjectList.size()>0){
    		 JSONObject jsonMain = (JSONObject)jsonObjectList.get(0);
    		 for(int i=1;i<jsonObjectList.size();i++){
    			 JSONObject jsonSub = (JSONObject)jsonObjectList.get(i);
    			 for(String key : jsonSub.keySet()){
    				 jsonMain.put(key, jsonSub.get(key));
    			 }
    		 }
    		 return jsonMain;
    	 }
    	 return null;
     }
     
     /**
 	 * 读java包时返回的路径
 	 * 
 	 * @param filePath
 	 *            文件路径
 	 * @return
 	 * @throws FileNotFoundException
 	 */
 	public static String getFilePathForUrl(String filePath)
 			throws FileNotFoundException {
 		URL url = getFileUrl(filePath);
 		return url.getFile();
 	}
 	
 	/**
     * 通过配置文件获取在项目下的路径
     * 
     * @param pathName
     *            配置文件标签名
     * @param xmlPath
     * 			  配置文件的路径
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static String getPath(String xmlPath,String pathName) {
    	//获取Tomcat根路径
    	//String rootPath = fixPath(System.getProperty("catalina.home"));
    	//String projectPath = fixPath(XmlManage.getFile(xmlPath).getProperty("fileUrl.projectPath"));
    	//获取文件配置路径
    	String localPath = fixPath(XmlManage.getFile(xmlPath).getProperty(pathName));
    	
    	String path = /*rootPath + projectPath +*/ localPath;

    	return path;
    }
    
    /**
     * 通过配置文件获取附件保存路径
     * 
     * @param pathName
     *            配置文件标签名
     * @param xmlPath
     * 			  配置文件的路径
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static String getUploadFilePath(String xmlPath,String pathName) {
    	//获取附件上传根路径
    	//String rootPath = fixPath(XmlManage.getFile(xmlPath).getProperty("fileUrl.fileRootPath"));
    	//获取文件配置路径
    	String localPath = fixPath(XmlManage.getFile(xmlPath).getProperty(pathName));
    	//String path = rootPath + localPath;
    	String path = localPath;

    	return path;
    }
    
    
    /**
     * 判断结尾是否有“/”，没有就加上
     * 
     * @param path
     *            路径
     * @return 返回修正后的path
     */
    public static String fixPath(String path) {
    	//判断结尾是否有“/”，没有就加上
    	if(!"".equals(path)&&!path.endsWith("/")){
    		path += "/";
    	}
    	return path;
    }
    /**
     * 获取4位随机数
     * 
     * */
    public static String getFourRandomNum(){
    	double a = Math.random()*9000+1000;
    	String num = Double.toString(a);
    	num = num.substring(0,num.indexOf("."));
    	return num;
    }
    
    /**
     * 获取小数点后2位小数
     * @return
     */
    public static String getPointTowRandomNum() {
    		DecimalFormat df = new DecimalFormat( "0.00" );
		double number = (float)Math.random();
		return df.format(number);
    }
    
    /**
     * 判断字符串是否为时间格式
     * @param str
     * @return
     */
    public static boolean isDate(String str) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			sdf.parse(str);
			return true;
		} catch (Exception e) {
			SimpleDateFormat sdfE = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				sdfE.parse(str);
				return true;
			} catch (ParseException e1) {
				return false;
			}
		}
    }

	/**
	 * 判断字符串是否在集合里
	 * @param list
	 * @param str
	 * @return
	 */
	public static boolean ifInclude(List<String> list, String str){
		for(int i=0;i<list.size();i++){
			if (list.get(i)!=null&&list.get(i).indexOf(str)!=-1) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Unicode转中文
	 * @param unicode
	 * @return
	 */
	public static String unicodeToCn(String unicode) {
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
	}
}
