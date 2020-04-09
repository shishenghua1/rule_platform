package com.boco.eoms.base.util;

/**
 * 接口状态枚举类
 * @author yueyouqiang
 *
 */
public enum ConstantsEnum {
	QUERY_SUCCESS("QUERY_SUCCESS","200"),//查询成功
	CHECK_SUCCESS("CHECK_SUCCESS","201"),//操作成功
    ERRONEOUS_REQUEST("ERRONEOUS_REQUEST","400"),//调用流程引擎接口报错
    NO_PERMISSIONS("NO_PERMISSIONS","403"),//无调用平台接口的权限
    NOT_FOUND("NOT_FOUND","404"),
    INTERNAL_ERROR("INTERNAL_ERROR","500");//程序内部报错
	
    private String mKey;
    private String mValue;
    
    ConstantsEnum(String key,String value){
    	this.mKey = key;
    	this.mValue = value;
    }
    
    public String getmKey() {
		return mKey;
	}
    
	public String getmValue() {
		return mValue;
	}

	public static String getConstantsEnum(String key){
    	for(ConstantsEnum ce : values()){
    		if((ce.mKey).equals(key) ){
    			return ce.getmValue();
    		}
    	}
		return null;
    	
    }
    
}
