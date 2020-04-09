package com.boco.eoms.rule.cwmsysdatadict.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.ReturnJsonUtil;
import com.boco.eoms.rule.cwmsysdatadetail.model.CwmDataDetail;
import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.mapper.CwmRuleGroupLibraryRelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.rule.cwmsysdatadict.mapper.CwmDataDictMapper;
import com.boco.eoms.rule.cwmsysdatadict.model.CwmDataDict;
import com.boco.eoms.rule.cwmsysdatadict.service.ICwmDataDictService;

/**

* 创建时间：2019年7月17日 下午3:24:14

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
@Service("cwmDataDictService")
public class CwmDataDictServiceImpl implements ICwmDataDictService{
	
	@Autowired
	private CwmDataDictMapper cwmDataDictMapper;
	@Autowired
	private CwmRuleGroupLibraryRelMapper cwmRuleGroupLibraryRelMapper;

	/**
	 * 批量操作数据详情信息
	 * @param cwmDataDictList
	 * @return
	 */
	@Transactional
	public JSONObject saveOperate(List<CwmDataDict> cwmDataDictList){
		JSONObject returnInfo = new JSONObject();
		if(cwmDataDictList!=null&&cwmDataDictList.size()>0){
			//新增的list
			List<CwmDataDict> newList = new ArrayList<>();
			//更改的list
			List<CwmDataDict> updateList = new ArrayList<>();
			for (int i = 0; i < cwmDataDictList.size(); i++) {
				CwmDataDict cwmDataDict = cwmDataDictList.get(i);
				//集合里第一个元素的id
				String id = StaticMethod.nullObject2String(cwmDataDict.getId());
				if("".equals(id)){
					newList.add(cwmDataDict);
				}else{
					updateList.add(cwmDataDict);
				}
			}
			//批量插入的校验功能前端完成
			if(!newList.isEmpty()){
				returnInfo= ReturnJsonUtil.returnSuccessList("保存成功");
				cwmDataDictMapper.batchInsert(newList);
			}
			if(!updateList.isEmpty()){
				StringBuilder updateInfo = new StringBuilder();
				StringBuilder fieldEnInfo = new StringBuilder();
				updateInfo.append("更改失败,字段");
				for(int i=0;i<updateList.size();i++){
					CwmDataDict cwmDataDict = updateList.get(i);
					String dataModuleId = cwmDataDict.getDataModuleId();
					String dictEnName = cwmDataDict.getDictEnName();
					int dataModuleNum = cwmRuleGroupLibraryRelMapper.selectNumByDataModuleId(dataModuleId,dictEnName);
					if(dataModuleNum>0){
						updateInfo.append(cwmDataDict.getDictCnName()+",");
						fieldEnInfo.append(dictEnName+",");
					}
				}
				if(updateInfo.length()>7){
					fieldEnInfo.deleteCharAt(fieldEnInfo.length()-1);
					updateInfo.deleteCharAt(updateInfo.length()-1);
					updateInfo.append("已经使用");
					returnInfo.put("failField",fieldEnInfo);
					returnInfo= ReturnJsonUtil.returnFailList(returnInfo,updateInfo.toString());
				}else{
					returnInfo= ReturnJsonUtil.returnSuccessList("保存成功");
					cwmDataDictMapper.batchUpdate(updateList);
				}
			}
		}
		return returnInfo;
	}

	@Transactional
	public JSONObject deleteByCondition(String id, String dataModuleId, String dictEnName) {
		int dataModuleNum = cwmRuleGroupLibraryRelMapper.selectNumByDataModuleId(dataModuleId,dictEnName);
		if(dataModuleNum<=0){
			cwmDataDictMapper.deleteByPrimaryKey(id);
			return ReturnJsonUtil.returnSuccessList("删除成功");
		}else {
			return ReturnJsonUtil.returnFailList("删除失败,该字典值已经使用.");
		}
	}

	@Override
	@Transactional
	public int insert(CwmDataDict record) throws Exception {
		// TODO Auto-generated method stub
		String id = StaticMethod.nullObject2String(record.getId());
		if("".equals(id)) {
			record.setId(UUIDHexGenerator.getInstance().getID());
		}
		return cwmDataDictMapper.insert(record);
	}

	@Transactional
	public void batchInsert(List<CwmDataDict> cwmDataDictList){
		// TODO Auto-generated method stub
		cwmDataDictMapper.batchInsert(cwmDataDictList);
	}

	@Override
	public CwmDataDict selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmDataDictMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int updateByPrimaryKey(CwmDataDict record) {
		// TODO Auto-generated method stub
		return cwmDataDictMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CwmDataDict> selectByCondition(String dictCnName, String parentDictId) {
		// TODO Auto-generated method stub
		return cwmDataDictMapper.selectByCondition(dictCnName, parentDictId);
	}

}

