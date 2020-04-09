package com.boco.eoms.rule.cwmsysrulerel.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.ReturnJsonUtil;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.cwmsysdatadetail.mapper.CwmDataDetailMapper;
import com.boco.eoms.rule.cwmsysdatadetail.model.CwmDataDetail;
import com.boco.eoms.rule.cwmsysdatamodule.mapper.CwmDataModuleMapper;
import com.boco.eoms.rule.cwmsysdatamodule.model.CwmDataModule;
import com.boco.eoms.rule.cwmsysruleapps.mapper.CwmRuleAppsMapper;
import com.boco.eoms.rule.cwmsysruleapps.model.CwmRuleApps;
import com.boco.eoms.rule.cwmsysruleatoms.mapper.CwmRuleAtomsMapper;
import com.boco.eoms.rule.cwmsysruleatoms.model.CwmRuleAtoms;
import com.boco.eoms.rule.cwmsysruleconfig.drools.RuleLoaderNew;
import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.mapper.CwmRuleGroupLibraryRelMapper;
import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.model.CwmRuleGroupLibraryRel;
import com.boco.eoms.rule.cwmsysrulegroupoutput.mapper.CwmRuleGroupOutputMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutput.model.CwmRuleGroupOutput;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.mapper.CwmRuleGroupOutputParamsMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.model.CwmRuleGroupOutputParams;
import com.boco.eoms.rule.cwmsysrulegroupoutputrel.mapper.CwmRuleGroupOutputRelMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutputrel.model.CwmRuleGroupOutputRel;
import com.boco.eoms.rule.cwmsysrulegrouprel.mapper.CwmRuleGroupRelMapper;
import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;
import com.boco.eoms.rule.cwmsysrulegroups.mapper.CwmRuleGroupsMapper;
import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;
import com.boco.eoms.rule.cwmsysrulemodules.mapper.CwmRuleModulesMapper;
import com.boco.eoms.rule.cwmsysrulemodules.model.CwmRuleModules;
import com.boco.eoms.rule.cwmsysrulerel.model.CwmRuleGroupConfig;
import com.boco.eoms.rule.cwmsysrulerel.model.OutPutParamDetail;
import com.boco.eoms.rule.cwmsysrulerel.model.OutputParamInfo;
import com.boco.eoms.rule.cwmsysrulerel.model.ParamInfo;
import com.boco.eoms.rule.cwmsysrulerel.model.ParamInterName;
import com.boco.eoms.rule.cwmsysrulerel.model.RuleContent;
import com.boco.eoms.rule.cwmsysrulerel.model.RuleGroupInfo;
import com.boco.eoms.rule.cwmsysrulerel.model.RuleGroupInputInfo;
import com.boco.eoms.rule.cwmsysrulerel.model.RuleGroupOutputInfo;
import com.boco.eoms.rule.cwmsysrulerel.service.ICwmRuleRelService;
import com.boco.eoms.rule.cwmsysruleuser.mapper.CwmRuleUserMapper;
import com.boco.eoms.rule.cwmsysruleuser.model.CwmRuleUser;
import com.boco.eoms.rule.cwmsysruleuserroles.mapper.CwmRuleUserRolesMapper;

/**

* 创建时间：2019年7月3日 下午2:58:22

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
@Service
public class CwmRuleRelServiceImpl implements ICwmRuleRelService{

	//日志信息
	private Logger logger = LoggerFactory.getLogger(CwmRuleRelServiceImpl.class);

	@Autowired
	private CwmRuleGroupRelMapper cwmRuleGroupRelMapper;
	@Autowired
	private CwmRuleAtomsMapper cwmRuleAtomsMapper;
	@Autowired
	private CwmRuleGroupOutputMapper cwmRuleGroupOutputMapper;
	@Autowired
	private CwmRuleGroupOutputParamsMapper cwmRuleGroupOutputParamsMapper;
	@Autowired
	private CwmRuleGroupsMapper cwmRuleGroupsMapper;
	@Autowired
	private CwmRuleAppsMapper cwmRuleAppsMapper;
	@Autowired
	private CwmRuleModulesMapper cwmRuleModulesMapper;
	@Autowired
	private CwmDataModuleMapper cwmDataModuleMapper;
	@Autowired
	private CwmRuleGroupOutputRelMapper cwmRuleGroupOutputRelMapper;
	@Autowired
	private CwmRuleGroupLibraryRelMapper cwmRuleGroupLibraryRelMapper;
	@Autowired
	private CwmDataDetailMapper cwmDataDetailMapper;
	@Autowired
    private CwmRuleUserMapper  cwmSysRuleUserMapper;
	@Autowired
	private CwmRuleUserRolesMapper cwmRuleUserRolesMapper;

	@Autowired
	private Environment env;

	/*@Autowired
	private CwmRuleAtomGroupMapper cwmRuleAtomGroupMapper;
	@Autowired
	private CwmRuleAtomInputMapper cwmRuleAtomInputMapper;
	@Autowired
	private CwmRuleAtomOutputMapper cwmRuleAtomOutputMapper;
	@Autowired
	private CwmRuleAtomGroupRelMapper cwmRuleAtomGroupRelMapper;
	@Autowired
	private CwmRuleAtomGroupOutputMapper cwmRuleAtomGroupOutputMapper;
	@Autowired
	private CwmRuleAtomGroupOutputRelMapper cwmRuleAtomGroupOutputRelMapper;
	*/

	@Autowired
	private RuleLoaderNew ruleLoader;

	@Override
	@Transactional
	public JSONObject saveCwmRuleGroupConfig(CwmRuleGroupConfig cwmRuleGroupConfig) throws Exception{
		//获取草稿标识
		CwmRuleGroups cwmRuleGroups = cwmRuleGroupConfig.getCwmRuleGroups();
		String draftFlag = StaticMethod.nullObject2String(cwmRuleGroups.getDraftFlag());
		if("1".equals(draftFlag)) {
			//不为草稿进行校验
			//规则配置保存校验
			String checkResult = checkRuleGroupConfig(cwmRuleGroupConfig,"new");
			if(!"".equals(checkResult)){
				return ReturnJsonUtil.returnFailList(checkResult);
			}
		}
		//规则集合的更改
		if(cwmRuleGroups!=null&&null!=cwmRuleGroups.getId()) {
			cwmRuleGroupsMapper.updateByPrimaryKey(cwmRuleGroups);
		}
		//原子规则相关数据
		List<CwmRuleAtoms> cwmRuleAtomsList = cwmRuleGroupConfig.getCwmRuleAtomsList();
		if(cwmRuleAtomsList != null && cwmRuleAtomsList.size() > 0) {
			for (int i = 0; i < cwmRuleAtomsList.size(); i++) {
				String operator2 = StaticMethod.nullObject2String(cwmRuleAtomsList.get(i).getOperator2());
				String atomName = StaticMethod.nullObject2String(cwmRuleAtomsList.get(i).getAtomName());
				String atomDescription = StaticMethod.nullObject2String(cwmRuleAtomsList.get(i).getAtomDescription());
				//因为描述和名称一致，所以只要判断名称即可
				if(atomName.contains("请选择")){
					cwmRuleAtomsList.get(i).setAtomName(atomName.substring(0,atomName.indexOf("请选择")));
					cwmRuleAtomsList.get(i).setAtomDescription(atomDescription.substring(0,atomDescription.indexOf("请选择")));
				}
				if(operator2.contains("请选择")){
					cwmRuleAtomsList.get(i).setOperator2("");
					cwmRuleAtomsList.get(i).setInputCnParam3("");
					cwmRuleAtomsList.get(i).setInputEnParam3("");
					cwmRuleAtomsList.get(i).setInputParamType3("");
				}
			}
			cwmRuleAtomsMapper.batchInsert(cwmRuleAtomsList);
		}
		//规则输出的保存,该集合的id要求前端生成，规则集合输出关联会用到
		List<CwmRuleGroupOutput> ruleGroupOutputList = cwmRuleGroupConfig.getRuleGroupOutputList();
		if(ruleGroupOutputList!=null&&ruleGroupOutputList.size()>0) {
			cwmRuleGroupOutputMapper.batchInsert(ruleGroupOutputList);
		}
		//规则集合输出默认关联集合保存
		List<CwmRuleGroupOutputRel> cwmRuleGroupOutputRelList= new ArrayList<>();
		//规则集合关联
		List<CwmRuleGroupRel> cwmRuleGroupRelList= cwmRuleGroupConfig.getCwmRuleGroupRelList();
		String groupId = "";
		if(cwmRuleGroupRelList != null && cwmRuleGroupRelList.size() > 0) {
			groupId = cwmRuleGroupRelList.get(0).getParentNodeId();
			//规则集合关联的保存
			cwmRuleGroupRelMapper.batchInsert(cwmRuleGroupRelList);
			//规则集合输出关联其他规则集合的设置
			for(int i=0;i<cwmRuleGroupRelList.size();i++){
                String nodeType = cwmRuleGroupRelList.get(i).getNodeType();
                if("ruleGroup".equals(nodeType)){
                    String thisGroupId =  cwmRuleGroupRelList.get(i).getRuleIdRel();
                    CwmRuleGroupOutputRel cwmRuleGroupOutputRel = new CwmRuleGroupOutputRel();
                    if(ruleGroupOutputList!=null&&ruleGroupOutputList.size()>0){
                        for(int j=0;j<ruleGroupOutputList.size();j++){
                            String groupOutputId = ruleGroupOutputList.get(j).getId();
                            String groupOutputResult = ruleGroupOutputList.get(j).getGroupOutputResult();
                            //获取关联的规则集合输出信息
                            CwmRuleGroupOutput cwmRuleGroupOutput =
                                    cwmRuleGroupOutputMapper.selectByGroupIdAndResult(thisGroupId,groupOutputResult);
                            cwmRuleGroupOutputRel.setGroupIdRel(thisGroupId);
							if(cwmRuleGroupOutput==null){
								logger.error("关联的规则集合"+thisGroupId+"输出内容为空.");
							}
                            cwmRuleGroupOutputRel.setGroupOutputExplainRel(cwmRuleGroupOutput.getGroupOutputExplain());
                            cwmRuleGroupOutputRel.setGroupOutputId(groupOutputId);
                            cwmRuleGroupOutputRel.setGroupOutputResultRel(groupOutputResult);
							cwmRuleGroupOutputRel.setCreateUserId("admin");
                            cwmRuleGroupOutputRelList.add(cwmRuleGroupOutputRel);
                        }
                    }
                }
			}
            if(cwmRuleGroupOutputRelList!=null&&!cwmRuleGroupOutputRelList.isEmpty()) {
                cwmRuleGroupOutputRelMapper.batchInsert(cwmRuleGroupOutputRelList);
            }
		}

		//规则输出参数的配置
		List<CwmRuleGroupOutputParams> ruleGroupOutputParamsList=cwmRuleGroupConfig.getRuleGroupOutputParamsList();
		if(ruleGroupOutputParamsList!=null&&!ruleGroupOutputParamsList.isEmpty()) {
			for (int i = 0; i < ruleGroupOutputParamsList.size(); i++) {
				String operator2 = StaticMethod.nullObject2String(ruleGroupOutputParamsList.get(i).getOperator2());
				if(operator2.contains("请选择")){
					ruleGroupOutputParamsList.get(i).setOperator2("");
					ruleGroupOutputParamsList.get(i).setOutputCnParam3("");
					ruleGroupOutputParamsList.get(i).setOutputEnParam3("");
					ruleGroupOutputParamsList.get(i).setOutputParamType3("");
				}
			}
			cwmRuleGroupOutputParamsMapper.batchInsert(ruleGroupOutputParamsList);
		}

		//规则集合库关联保存
		List<CwmRuleGroupLibraryRel> cwmRuleGroupLibraryRelList=cwmRuleGroupConfig.getCwmRuleGroupLibraryRelList();
		if(cwmRuleGroupLibraryRelList!=null&&!cwmRuleGroupLibraryRelList.isEmpty()) {
			List<CwmRuleGroupLibraryRel> saveCwmRuleGroupLibraryRelList = new ArrayList<CwmRuleGroupLibraryRel>();
			for(CwmRuleGroupLibraryRel rel : cwmRuleGroupLibraryRelList) {
				if(!"".equals(StaticMethod.nullObject2String(rel.getId()))) {
					saveCwmRuleGroupLibraryRelList.add(rel);
				}
			}
			if(saveCwmRuleGroupLibraryRelList != null && saveCwmRuleGroupLibraryRelList.size() > 0) {
				cwmRuleGroupLibraryRelMapper.batchInsert(saveCwmRuleGroupLibraryRelList);
			}
		}
		if("1".equals(draftFlag)) {
			//非草稿，规则保存成功 重新加载规则到内存
			ruleLoader.reload(groupId);
		}
		//规则保存成功
		return ReturnJsonUtil.returnSuccessList("保存成功");
	}

	/**
	 * 规则集合配置查询
	 * @param groupId
	 * @param ruleType 规则集合类型
	 * @return
	 */
	@Override
	public CwmRuleGroupConfig queryCwmRuleGroupConfig(String groupId,String ruleType) throws Exception{
		CwmRuleGroupConfig cwmRuleGroupConfig = new CwmRuleGroupConfig();
		//规则集合信息的查询
		CwmRuleGroups cwmRuleGroups =cwmRuleGroupsMapper.selectByPrimaryKey(groupId);
		if(cwmRuleGroups!=null) {
			cwmRuleGroupConfig.setCwmRuleGroups(cwmRuleGroups);
		}
		
		//规则集合输出查询
		List<CwmRuleGroupOutput> ruleGroupOutputList =cwmRuleGroupOutputMapper.selectByGroupId(groupId);
		//规则集合输出id
		String outputId = "";
		if(ruleGroupOutputList!=null&&!ruleGroupOutputList.isEmpty()) {
			cwmRuleGroupConfig.setRuleGroupOutputList(ruleGroupOutputList);
			//规则集合输出参数的查询
			//输出参数集合id
			String[] outputParamsIds = new String[ruleGroupOutputList.size()];
			for(int i=0;i<ruleGroupOutputList.size();i++){
				outputParamsIds[i] = ruleGroupOutputList.get(i).getGroupOutputParamsId();
			}

			List<CwmRuleGroupOutputParams> ruleGroupOutputParamsList = cwmRuleGroupOutputParamsMapper.selectByParamsIds(outputParamsIds);
			cwmRuleGroupConfig.setRuleGroupOutputParamsList(ruleGroupOutputParamsList);
		}
		
		//规则集合关联查询
		List<CwmRuleGroupRel> cwmRuleGroupRels = new ArrayList<>();
		if("ruleGroup".equals(ruleType)){
			cwmRuleGroupRels = cwmRuleGroupRelMapper.selectRecursionByGroupId(groupId);
		}else{
			getRuleGroupRels(cwmRuleGroupRels,groupId,ruleType);
		}
		cwmRuleGroupConfig.setCwmRuleGroupRelList(cwmRuleGroupRels);

		//判断为规则集合,获取原子规则id集合
		if("ruleGroup".equals(ruleType)){
			List<String> atomIds = new ArrayList<>();
			if(cwmRuleGroupRels!=null&&!cwmRuleGroupRels.isEmpty()) {
				cwmRuleGroupConfig.setCwmRuleGroupRelList(cwmRuleGroupRels);
				for(int i=0;i<cwmRuleGroupRels.size();i++) {
					CwmRuleGroupRel cwmRuleGroupRel = cwmRuleGroupRels.get(i);
					if("atomRule".equals(cwmRuleGroupRel.getNodeType())) {
						atomIds.add(cwmRuleGroupRel.getRuleIdRel());
					}
				}
			}
			if(!atomIds.isEmpty()) {
				List<CwmRuleAtoms> cwmRuleAtoms=cwmRuleAtomsMapper.selectByAtomIds(atomIds);
				if(cwmRuleAtoms!=null&&!cwmRuleAtoms.isEmpty()) {
					cwmRuleGroupConfig.setCwmRuleAtomsList(cwmRuleAtoms);
				}
			}
		}
		//规则集合输出关联查询,不需要
		/*List<CwmRuleGroupOutputRel>  cwmRuleGroupOutputRelList =
					cwmRuleGroupOutputRelMapper.selectByOutputId(outputId);
		cwmRuleGroupConfig.setCwmRuleGroupOutputRelList(cwmRuleGroupOutputRelList);*/

		//规则集合库关联查询
		List<CwmRuleGroupLibraryRel>  cwmRuleGroupLibraryRelList =
				cwmRuleGroupLibraryRelMapper.selectByGroupId(groupId);
		cwmRuleGroupConfig.setCwmRuleGroupLibraryRelList(cwmRuleGroupLibraryRelList);
		return cwmRuleGroupConfig;
	}

	/**
	 * 通过递归查询规则集合关联数据
	 * @param parentNodeId
	 * @param ruleType 规则类型
	 */
	public void getRuleGroupRels(List<CwmRuleGroupRel> cwmRuleGroupRelList,String parentNodeId,String ruleType){
		//查询指定节点的叶子节点
		List<CwmRuleGroupRel> list = cwmRuleGroupRelMapper.selectRelByParentNodeId(parentNodeId);
		if(list!=null&&!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				CwmRuleGroupRel cwmRuleGroupRel = list.get(i);
				parentNodeId = cwmRuleGroupRel.getRuleIdRel();
				ruleType = cwmRuleGroupRel.getNodeType();
				cwmRuleGroupRelList.add(cwmRuleGroupRel);
				//如果为规则集合类型,则一直遍历完全到结束;如果为装配类型,规则集合为结束点
				if(!"ruleGroup".equals(ruleType)){
					getRuleGroupRels(cwmRuleGroupRelList,parentNodeId,ruleType);
				}
			}
		}
	}

	/**
	 * 修改传递的是全量数据
	 * @param cwmRuleGroupConfig
	 * @throws Exception
	 */
	@Override
	@Transactional
	public JSONObject updateCwmRuleGroupConfig(CwmRuleGroupConfig cwmRuleGroupConfig) throws Exception{
		//获取草稿标识
		CwmRuleGroups cwmRuleGroups = cwmRuleGroupConfig.getCwmRuleGroups();
		String draftFlag = StaticMethod.nullObject2String(cwmRuleGroups.getDraftFlag());
		if("1".equals(draftFlag)) {
			//不为保存草稿进行校验
			//规则配置保存校验
			String checkResult = checkRuleGroupConfig(cwmRuleGroupConfig,"new");
			if(!"".equals(checkResult)){
				return ReturnJsonUtil.returnFailList(checkResult);
			}
		}
		//规则集合的更改
		//全量数据，规则集合数据肯定存在
		String groupType = "";
		if(cwmRuleGroups!=null) {
			groupType = StaticMethod.nullObject2String(cwmRuleGroups.getGroupType());
			cwmRuleGroupsMapper.updateByPrimaryKey(cwmRuleGroups);
		}
		//规则集合关联
		String groupId="";
		List<CwmRuleGroupRel> cwmRuleGroupRelList= cwmRuleGroupConfig.getCwmRuleGroupRelList();
		if(cwmRuleGroupRelList != null && cwmRuleGroupRelList.size() > 0) {
			groupId = cwmRuleGroupRelList.get(0).getParentNodeId();
			if("ruleGroup".equals(groupType)){
				cwmRuleGroupRelMapper.deleteByGroupId(groupId);
			}else{
				//规则集合关联查询
				List<CwmRuleGroupRel> cwmRuleGroupRels = new ArrayList<>();
				getRuleGroupRels(cwmRuleGroupRels,groupId,groupType);
				List<String> listId = new ArrayList<>();
				if(cwmRuleGroupRels!=null&&! cwmRuleGroupRels.isEmpty()){
					for (int i = 0; i < cwmRuleGroupRels.size(); i++) {
						listId.add(cwmRuleGroupRels.get(i).getId());
					}
				}
				cwmRuleGroupRelMapper.batchDeleteById(listId);
			}
			//规则集合的重新关联
			cwmRuleGroupRelMapper.batchInsert(cwmRuleGroupRelList);
		}

		//原子规则id集合的更改
		if("ruleGroup".equals(groupType)){
			//原子规则相关数据
			List<CwmRuleAtoms> cwmRuleAtomsList = cwmRuleGroupConfig.getCwmRuleAtomsList();
			if(cwmRuleAtomsList != null && cwmRuleAtomsList.size() > 0) {
                List<CwmRuleGroupRel> cwmRuleGroupRels = cwmRuleGroupRelMapper.selectRecursionByGroupId(groupId);
                //判断为规则集合,获取原子规则id集合
                List<String> atomIds_db = new ArrayList<>();
                if(cwmRuleGroupRels!=null&&!cwmRuleGroupRels.isEmpty()) {
                    cwmRuleGroupConfig.setCwmRuleGroupRelList(cwmRuleGroupRels);
                    for(int i=0;i<cwmRuleGroupRels.size();i++) {
                        CwmRuleGroupRel cwmRuleGroupRel = cwmRuleGroupRels.get(i);
                        if("atomRule".equals(cwmRuleGroupRel.getNodeType())) {
                            atomIds_db.add(cwmRuleGroupRel.getRuleIdRel());
                        }
                    }
                }
                if(!atomIds_db.isEmpty()) {
                    /**
                     * 查询数据库相关的原子规则，避免脏数据产生;
                     * 存在原子规则的新增和修改，采取删除原来数据重新保存方式,
                     * 只能物理删除，id会重复。
                     */
                    cwmRuleAtomsMapper.delByAtomIds(atomIds_db);
                }
				for (int i = 0; i < cwmRuleAtomsList.size(); i++) {
					String operator2 = StaticMethod.nullObject2String(cwmRuleAtomsList.get(i).getOperator2());
					String atomName = StaticMethod.nullObject2String(cwmRuleAtomsList.get(i).getAtomName());
					String atomDescription = StaticMethod.nullObject2String(cwmRuleAtomsList.get(i).getAtomDescription());
					//因为描述和名称一致，所以只要判断名称即可
					if(atomName.contains("请选择")){
						cwmRuleAtomsList.get(i).setAtomName(atomName.substring(0,atomName.indexOf("请选择")));
						cwmRuleAtomsList.get(i).setAtomDescription(atomDescription.substring(0,atomDescription.indexOf("请选择")));
					}
					if(operator2.contains("请选择")){
						cwmRuleAtomsList.get(i).setOperator2("");
						cwmRuleAtomsList.get(i).setInputCnParam3("");
						cwmRuleAtomsList.get(i).setInputEnParam3("");
						cwmRuleAtomsList.get(i).setInputParamType3("");
					}
				}
				cwmRuleAtomsMapper.batchInsert(cwmRuleAtomsList);
			}
		}

		//规则输出集合的保存
		List<CwmRuleGroupOutput> ruleGroupOutputList = cwmRuleGroupConfig.getRuleGroupOutputList();
		String[] outputId = null;
		String[] groupOutputParamsIds= null;
		if(ruleGroupOutputList!=null&&ruleGroupOutputList.size()>0) {
			outputId = new String[ruleGroupOutputList.size()];
			groupOutputParamsIds = new String[ruleGroupOutputList.size()];
			cwmRuleGroupOutputMapper.batchUpdate(ruleGroupOutputList);
			for (int i=0;i<ruleGroupOutputList.size();i++){
				groupOutputParamsIds[i]=ruleGroupOutputList.get(i).getGroupOutputParamsId();
				outputId[i] = ruleGroupOutputList.get(i).getId();
			}
		}
		//存在规则输出参数的新增和修改，采取删除原来数据重新保存方式
		cwmRuleGroupOutputParamsMapper.delByParamId(groupOutputParamsIds);
		//规则输出参数的配置
		List<CwmRuleGroupOutputParams> ruleGroupOutputParamsList=cwmRuleGroupConfig.getRuleGroupOutputParamsList();
		if(ruleGroupOutputParamsList!=null&&!ruleGroupOutputParamsList.isEmpty()) {
			for (int i = 0; i < ruleGroupOutputParamsList.size(); i++) {
				String operator2 = StaticMethod.nullObject2String(ruleGroupOutputParamsList.get(i).getOperator2());
				if(operator2.contains("请选择")){
					ruleGroupOutputParamsList.get(i).setOperator2("");
					ruleGroupOutputParamsList.get(i).setOutputCnParam3("");
					ruleGroupOutputParamsList.get(i).setOutputEnParam3("");
					ruleGroupOutputParamsList.get(i).setOutputParamType3("");
				}
			}
			cwmRuleGroupOutputParamsMapper.batchInsert(ruleGroupOutputParamsList);
		}
		//规则输出关联的修改,是默认值,目前没有修改的情况
		List<CwmRuleGroupOutputRel> cwmRuleGroupOutputRelList=cwmRuleGroupConfig.getCwmRuleGroupOutputRelList();
		if(null!=outputId&&cwmRuleGroupOutputRelList!=null&&!cwmRuleGroupOutputRelList.isEmpty()) {
			cwmRuleGroupOutputRelMapper.deleteByOutputIds(outputId);
			cwmRuleGroupOutputRelMapper.batchInsert(cwmRuleGroupOutputRelList);
		}
		//规则集合库关联的修改
		List<CwmRuleGroupLibraryRel>  cwmRuleGroupLibraryRelList =cwmRuleGroupConfig.getCwmRuleGroupLibraryRelList();
		if(null!=outputId&&cwmRuleGroupLibraryRelList!=null&&!cwmRuleGroupLibraryRelList.isEmpty()) {
			cwmRuleGroupLibraryRelMapper.deleteByGroupId(groupId);
			List<CwmRuleGroupLibraryRel> saveCwmRuleGroupLibraryRelList = new ArrayList<CwmRuleGroupLibraryRel>();
			for(CwmRuleGroupLibraryRel rel : cwmRuleGroupLibraryRelList) {
				if(!"".equals(StaticMethod.nullObject2String(rel.getId()))) {
					saveCwmRuleGroupLibraryRelList.add(rel);
				}
			}
			if(saveCwmRuleGroupLibraryRelList != null && saveCwmRuleGroupLibraryRelList.size() > 0) {
				cwmRuleGroupLibraryRelMapper.batchInsert(saveCwmRuleGroupLibraryRelList);
			}
		}
		if("1".equals(draftFlag)) {
			//非草稿，规则修改成功 重新加载规则到内存
			ruleLoader.reload(groupId);
		}
		//修改成功
		return ReturnJsonUtil.returnSuccessList("修改成功");
	}

	/**
	 * 规则集合配置校验功能：(注：如果字段类型包括计算属性,则对应表达式的其他字段只校验是否可计算)
	 * @param cwmRuleGroupConfig 规则集合配置内容
	 * @param saveType 保存的类型,new或者update
	 * @return
	 */
	public String  checkRuleGroupConfig(CwmRuleGroupConfig cwmRuleGroupConfig,String saveType){

		StringBuilder checkResult = new StringBuilder("");
        //规则集合信息
        CwmRuleGroups cwmRuleGroups = cwmRuleGroupConfig.getCwmRuleGroups();
        //规则集合类型
        String groupType = StaticMethod.nullObject2String(cwmRuleGroups.getGroupType());
		//规则集合关联
		List<CwmRuleGroupRel> cwmRuleGroupRelList= cwmRuleGroupConfig.getCwmRuleGroupRelList();
		//原子规则相关数据
		List<CwmRuleAtoms> cwmRuleAtomsList = cwmRuleGroupConfig.getCwmRuleAtomsList();
		//规则输出集合的保存
		List<CwmRuleGroupOutput> ruleGroupOutputList = cwmRuleGroupConfig.getRuleGroupOutputList();
		//规则输出参数的配置
		List<CwmRuleGroupOutputParams> ruleGroupOutputParamsList=cwmRuleGroupConfig.getRuleGroupOutputParamsList();
		//规则集合库的配置
		List<CwmRuleGroupLibraryRel> cwmRuleGroupLibraryRelList=cwmRuleGroupConfig.getCwmRuleGroupLibraryRelList();
		if("ruleGroup".equals(groupType)&&(cwmRuleGroupLibraryRelList==null||cwmRuleGroupLibraryRelList.isEmpty())) {
			checkResult.append("规则集合库数据缺失.");
		}
		//判断为新增或修改
		if("new".equals(saveType)){
			//规则配置的如果,那么,否则的必填性
			if(cwmRuleGroupRelList==null||cwmRuleGroupRelList.size() <= 0){
				checkResult.append("输入部分为必填部分,需要填写;");
			}
			if(ruleGroupOutputList==null||ruleGroupOutputList.size()<2) {
				checkResult.append("输出部分为必填部分,需要填写;");
			}
		}
		//运算符
		String countStr=">,>=,<,<=,+,-，*,/,";
		//计算运算符
		String baseCountStr="+,-，*,/,";

		//规则树图的正确性

		//获取数字类型的字典值
		String numStr = env.getProperty("fieldType.number");
		//获取字符类型字典值
		String characterStr = env.getProperty("fieldType.character");
		//获取时间类型字典值
		String timeStr = env.getProperty("fieldType.time");

		//原子规则正确性
		if(null!=cwmRuleAtomsList&&cwmRuleAtomsList.size()>0){
			for (int i = 0; i < cwmRuleAtomsList.size(); i++) {
				//默认表达式字段类型一致
				boolean expressionType = true;

				String inputParamType1 = StaticMethod.nullObject2String(
						cwmRuleAtomsList.get(i).getInputParamType1());
				String inputParamType2 = StaticMethod.nullObject2String(
						cwmRuleAtomsList.get(i).getInputParamType2());
				String inputParamType3 = StaticMethod.nullObject2String(
						cwmRuleAtomsList.get(i).getInputParamType3());
				if(!inputParamType1.equals("")){
					inputParamType1=getFieldType(numStr,characterStr,timeStr,inputParamType1);
				}
				if(!inputParamType2.equals("")){
					inputParamType2=getFieldType(numStr,characterStr,timeStr,inputParamType2);
				}
				if(!inputParamType3.equals("")){
					inputParamType3=getFieldType(numStr,characterStr,timeStr,inputParamType3);
				}
				String inputCnParam1 = StaticMethod.nullObject2String(
						cwmRuleAtomsList.get(i).getInputCnParam1());
				String inputCnParam2 = StaticMethod.nullObject2String(
						cwmRuleAtomsList.get(i).getInputCnParam2());
				String inputCnParam3 = StaticMethod.nullObject2String(
						cwmRuleAtomsList.get(i).getInputCnParam3());
				String operator1 = StaticMethod.nullObject2String(
						cwmRuleAtomsList.get(i).getOperator1());
				String operator2 = StaticMethod.nullObject2String(
						cwmRuleAtomsList.get(i).getOperator2());

				//原子规则参数类型校验
				String expressionStr = inputCnParam1+operator1+inputCnParam2+operator2+inputCnParam3;
				//参数类型的校验
				if(!operator2.contains("请选择")&&!"".equals(operator2)){
					expressionStr = inputCnParam1+operator1+inputCnParam2+operator2+inputCnParam3;
					if(!inputParamType1.equals(inputParamType2)||!inputParamType2.equals(inputParamType3)){
						expressionType = false;
					}
				}else{
					expressionStr = inputCnParam1+operator1+inputCnParam2;
					if(!inputParamType1.equals(inputParamType2)){
						expressionType = false;
					}
				}
				if(expressionType==false){
					checkResult.append("输入表达式:"+expressionStr+",字段类型要求一致;");
				}
				if((!operator1.equals("")&&countStr.contains(operator1+","))||(!operator2.equals("")&&countStr.contains(operator2+","))){
					if(!operator2.contains("请选择")&&!"".equals(operator2)){
						if((!inputParamType1.contains("数字")&&
								!inputParamType1.contains("时间"))||
								(!inputParamType2.contains("数字")&&
										!inputParamType2.contains("时间")) ||
								(!inputParamType3.contains("数字")&&
										!inputParamType3.contains("时间"))){
							checkResult.append("输入表达式:"+expressionStr+"，中字段类型存在错误，应为可参与运算的类型;");
						}
					}else{
						if((!inputParamType1.contains("数字")&&
								!inputParamType1.contains("时间"))||
								(!inputParamType2.contains("数字")&&
										!inputParamType2.contains("时间"))){
							checkResult.append("输入表达式:"+expressionStr+"，中字段类型存在错误，应为可参与运算的类型;");
						}
					}
				}

				//包含,不包含条件的校验
				if(!operator1.equals("")&&("contains".equals(operator1)||"not contains".equals(operator1))){
					if(!operator2.contains("请选择")&&!"".equals(operator2)){
						checkResult.append("输入表达式:"+expressionStr+"，要求最多2个参数.");
					}
					if(!inputParamType1.contains("字符")||!inputParamType2.contains("字符")){
						checkResult.append("输入表达式:"+expressionStr+"，要求参数类型为字符.");
					}
				}

				//表达式完整性校验
				if(operator1.contains("请选择")||operator1.equals("")){
					checkResult.append("输入表达式:"+expressionStr+"，不完整;");
				}
				if((!operator1.equals("")&&baseCountStr.contains(operator1+","))||(!operator2.equals("")&&baseCountStr.contains(operator2+","))){
					String intputEnParam1 = StaticMethod.nullObject2String(
							cwmRuleAtomsList.get(i).getInputEnParam1());
					String intputEnParam2 = StaticMethod.nullObject2String(
							cwmRuleAtomsList.get(i).getInputEnParam2());
					String intputEnParam3 = StaticMethod.nullObject2String(
							cwmRuleAtomsList.get(i).getInputEnParam3());
					if(intputEnParam1.contains("请选择")||"".equals(intputEnParam1)
							||intputEnParam2.contains("请选择")||"".equals(intputEnParam2)
							||intputEnParam3.contains("请选择")||"".equals(intputEnParam3)){
						checkResult.append("输入表达式:"+expressionStr+"，不完整;");
					}
				}
			}
		}else{
            if("ruleGroup".equals(groupType)){
                checkResult.append("原子规则缺失,数据错误.");
            }
		}

		//校验树图联合条件正确性
		if(cwmRuleGroupRelList!=null&&cwmRuleGroupRelList.size()>0){
			//装配里获取重复的集合元素
			if("assembling".equals(groupType)){
				String duplicateInfo = getDuplicateInfo(cwmRuleGroupRelList);
				if(!"".equals(duplicateInfo)){
					checkResult.append("规则集合元素重复，重复内容包括:"+duplicateInfo.substring(0,duplicateInfo.length()-1));
				}
			}
			//条件节点叶子数量默认配置正确
			//boolean chectNodeSonNum = true;
			//条件节点配置默认正确
			boolean chectNode = true;
			for (int i = 0; i < cwmRuleGroupRelList.size(); i++) {
				String nodeType = cwmRuleGroupRelList.get(i).getNodeType();
				String ruleIdRel = cwmRuleGroupRelList.get(i).getRuleIdRel();
				//判断是否存在子节点
				int sonNum = 0;
				if("condition".equals(nodeType)){
					for (int i1 = 0; i1 < cwmRuleGroupRelList.size(); i1++) {
						String parentNodeId = cwmRuleGroupRelList.get(i1).getParentNodeId();
						if(ruleIdRel.equals(parentNodeId)){
							sonNum++;
						}
					}
					if(sonNum<1&&chectNode){
						chectNode = false;
						checkResult.append("条件不能作为叶子节点;");
						logger.info("输入部分关联id为"+ruleIdRel+"的条件不能作为叶子节点");
					}

					/*else if(sonNum==1&&chectNodeSonNum){
						chectNodeSonNum = false;
						checkResult.append("条件下的叶子节点数量需要大于等于2;");
						logger.info("输入部分关联id为"+ruleIdRel+"的条件下的叶子节点数量需要大于等于2;");
					}*/
				}
			}
		}


		//那么否则部分的校验
		if(null!=ruleGroupOutputParamsList&&ruleGroupOutputParamsList.size()>0){
			//计算内容
			for(int i=0;i<ruleGroupOutputParamsList.size();i++){
				CwmRuleGroupOutputParams cwmRuleGroupOutputParams =ruleGroupOutputParamsList.get(i);
				//默认表达式字段类型一致
				boolean expressionType = true;

				//输出参数类型的判断,(如果类型存在即对应的参数肯定存在,都为空则该数据不存在)
				String outputParamType1 = StaticMethod.nullObject2String(
						cwmRuleGroupOutputParams.getOutputParamType1());
				String outputParamType2 = StaticMethod.nullObject2String(
						cwmRuleGroupOutputParams.getOutputParamType2());
				String outputParamType3 = StaticMethod.nullObject2String(
						cwmRuleGroupOutputParams.getOutputParamType3());
				if(!outputParamType1.equals("")){
					outputParamType1=getFieldType(numStr,characterStr,timeStr,outputParamType1);
				}
				if(!outputParamType2.equals("")){
					outputParamType2=getFieldType(numStr,characterStr,timeStr,outputParamType2);
				}
				if(!outputParamType3.equals("")){
					outputParamType3=getFieldType(numStr,characterStr,timeStr,outputParamType3);
				}
				String outputCnParam1 = StaticMethod.nullObject2String(
						cwmRuleGroupOutputParams.getOutputCnParam1());
				String outputCnParam2 = StaticMethod.nullObject2String(
						cwmRuleGroupOutputParams.getOutputCnParam2());
				String outputCnParam3 = StaticMethod.nullObject2String(
						cwmRuleGroupOutputParams.getOutputCnParam3());
				String operator1 = StaticMethod.nullObject2String(
						cwmRuleGroupOutputParams.getOperator1());
				String operator2 = StaticMethod.nullObject2String(
						cwmRuleGroupOutputParams.getOperator2());
				String outputEnParam1 = StaticMethod.nullObject2String(
						cwmRuleGroupOutputParams.getOutputEnParam1());
				String outputEnParam2 = StaticMethod.nullObject2String(
						cwmRuleGroupOutputParams.getOutputEnParam2());
				String outputEnParam3 = StaticMethod.nullObject2String(
						cwmRuleGroupOutputParams.getOutputEnParam3());
				String expressionStr = "";
				//参数类型的校验
				if(!operator2.contains("请选择")&&!"".equals(operator2)){
					expressionStr = outputCnParam1+operator1+outputCnParam2+operator2+outputCnParam3;
					if(!outputParamType1.equals(outputParamType2)||!outputParamType2.equals(outputParamType3)){
						expressionType = false;
					}
				}else{
					expressionStr = outputCnParam1+operator1+outputCnParam2;
					if(!outputParamType1.equals(outputParamType2)){
						expressionType = false;
					}
				}

				if(expressionType==false){
					checkResult.append("输出表达式:"+expressionStr+",字段类型要求一致;");
				}
				//输出的第一个运算符为=
				if(!operator2.equals("")&&countStr.contains(operator2+",")){
					if(!operator2.contains("请选择")&&!"".equals(operator2)){
						if((!outputParamType1.contains("数字")&&
								!outputParamType1.contains("时间"))||
								(!outputParamType2.contains("数字")&&
										!outputParamType2.contains("时间")) ||
								(!outputParamType3.contains("数字")&&
										!outputParamType3.contains("时间"))){
							checkResult.append("输出表达式:"+expressionStr+"，中字段类型存在错误，应为可计算类型;");
						}
					}else{
						if((!outputParamType1.contains("数字")&&
								!outputParamType1.contains("时间"))||
								(!outputParamType2.contains("数字")&&
										!outputParamType2.contains("时间"))){
							checkResult.append("输出表达式:"+expressionStr+"，中字段类型存在错误，应为可计算类型;");
						}
					}
				}
				//表达式完整性校验
				if(outputEnParam1.contains("请选择")||"".equals(outputEnParam1)
						||outputEnParam2.contains("请选择")||"".equals(outputEnParam2)){
					checkResult.append("输出表达式:"+expressionStr+"，不完整;");
				}else if(!operator2.equals("")&&countStr.contains(operator2+",")){
					if(outputEnParam1.contains("请选择")||"".equals(outputEnParam1)
							||outputEnParam2.contains("请选择")||"".equals(outputEnParam2)
							||outputEnParam3.contains("请选择")||"".equals(outputEnParam3)){
						checkResult.append("输出表达式:"+expressionStr+"，不完整;");
					}
				}
			}
		}

		return checkResult.toString();
	}


	/**
	 * 根据权限获取树图
	 */
	@Override
	public JSONArray getRulePlatformZtree() {
		//规则平台的菜单树
		JSONArray rulePlatformZtree  = new JSONArray();
		//项目根节点
		JSONObject projectNode=new JSONObject(true);
		projectNode.put("label", "项目列表");
		projectNode.put("id", "0");
		projectNode.put("type", "app");
		//应用系统app的json信息
		JSONArray appArray  = new JSONArray();
	    //根据权限展示
		//1代表超级管理员 2代码所属系统管理员 3代表所属模块管理员 4代表普通用户(目前是产品官网的用户)
		//获取当前用户
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		//查询当前用户信息
		CwmRuleUser cwmSysRuleUser = cwmSysRuleUserMapper.querysystemUserByUid(userId);
		String appId = "";
		String moduleId = "";
		if(cwmSysRuleUser != null) {
			appId = StaticMethod.nullObject2String(cwmSysRuleUser.getAppId());
			moduleId = StaticMethod.nullObject2String(cwmSysRuleUser.getModuleId());
		}
		
		//查询当前用户权限
		String permission = StaticMethod.nullObject2String(cwmRuleUserRolesMapper.selectByUserId(userId));
		List<CwmRuleApps> appList = new ArrayList<CwmRuleApps>();
		List<CwmRuleModules> cwmRuleModulesList = new ArrayList<CwmRuleModules>();
		
		if("1".equals(permission)) {
			//查询所有app
			appList = cwmRuleAppsMapper.selectByAppName("");
		}else if("2".equals(permission)){
			//查询当前用户app
			CwmRuleApps cwmRuleApps = cwmRuleAppsMapper.selectByPrimaryKey(appId);
			appList.add(cwmRuleApps);
		}else if("3".equals(permission)) {
			//查询当前用户app和module
			CwmRuleApps cwmRuleApps = cwmRuleAppsMapper.selectByPrimaryKey(appId);
			appList.add(cwmRuleApps);
			CwmRuleModules cwmRuleModules = cwmRuleModulesMapper.selectByPrimaryKey(moduleId);
			cwmRuleModulesList.add(cwmRuleModules);
		}else {
			//查询当前用户下app和module（产品展示网站的用户）
			appList = cwmRuleAppsMapper.selectByUserId(userId);
		}
		
		if(appList!=null&&!appList.isEmpty()) {
			for(int i=0;i<appList.size();i++) {
				CwmRuleApps app = appList.get(i);
				JSONObject appNode=new JSONObject(true);
				//规则模块的json信息
				JSONArray ruleModuleArray  = new JSONArray();
				appNode.put("label", app.getAppName());
				appNode.put("id", app.getId());
				appNode.put("type", "ruleModule");
				//查询app下的规则模块
				if(cwmRuleModulesList != null && cwmRuleModulesList.size() > 0) {
					
				}else {
					cwmRuleModulesList = cwmRuleModulesMapper.selectByAppId(app.getId());
				}
				setRuleModuleJson(cwmRuleModulesList, i, ruleModuleArray, appNode);
				appArray.add(appNode);
				cwmRuleModulesList.clear();
			}
			projectNode.put("children", appArray);
		}
		rulePlatformZtree.add(projectNode);
		return rulePlatformZtree;
	}
	/**
	 * 规则模块json数据设置
	 * @param cwmRuleModulesList 规则模块list集合
	 * @param i app集合的索引
	 * @param ruleModuleArray 规则模块json数组
	 * @param appNode app的json对象
	 */
	public void setRuleModuleJson(List<CwmRuleModules> cwmRuleModulesList,int i,
			JSONArray ruleModuleArray,JSONObject appNode) {
		for(int j=0;j<cwmRuleModulesList.size();j++) {
			CwmRuleModules cwmRuleModules = cwmRuleModulesList.get(j);
			JSONObject modulesNode=new JSONObject(true);
			//规则模块下的库和规则集
			JSONArray ruleModuleInfoArray  = new JSONArray();
			String moduleId = cwmRuleModules.getId();
			modulesNode.put("label", cwmRuleModules.getModuleName());
			modulesNode.put("id", cwmRuleModules.getId());
			modulesNode.put("type", "ruleModuleEle");
			//库节点
			JSONObject libraryNode=new JSONObject(true);
			//规则库array数据
			JSONArray libraryArray  = new JSONArray();
			libraryNode.put("label", "库");
			libraryNode.put("id", "library"+i);
			libraryNode.put("type", "dataModule");
			List<CwmDataModule> cwmDataModuleList = cwmDataModuleMapper.selectByModuleId(moduleId);
			if(cwmDataModuleList!=null&&!cwmDataModuleList.isEmpty()) {
				for(int k=0;k<cwmDataModuleList.size();k++) {
					CwmDataModule cwmDataModule = cwmDataModuleList.get(k);
					JSONObject dataModulesNode=new JSONObject(true);
					dataModulesNode.put("label", cwmDataModule.getDataModule());
					dataModulesNode.put("id", cwmDataModule.getId());
					dataModulesNode.put("type", "dataModule-"+cwmDataModule.getDataType());
					libraryArray.add(dataModulesNode);
				}
				libraryNode.put("children", libraryArray);
			}
			ruleModuleInfoArray.add(libraryNode);

			//规则集节点
			JSONObject ruleNode=new JSONObject(true);
			//规则集array数据
			JSONArray ruleArray  = new JSONArray();
			ruleNode.put("label", "规则集");
			ruleNode.put("id", "rule"+i);
			ruleNode.put("type", "ruleGroup");
			//草稿和非草稿节点
//			JSONArray ruleDraftArray = new JSONArray();
			//草稿节点
//			JSONObject ruleDraftNode = new JSONObject(true);
//			JSONArray ruleDraftNodeArray = new JSONArray();
//			ruleDraftNode.put("label", "草稿");
//			ruleDraftNode.put("id", "draft"+i);
//			ruleDraftNode.put("type", "ruleGroup");
//			//查询规则集合草稿
//			Map<String,Object> condition = new HashMap<String,Object>();
//			condition.put("moduleId", moduleId);
//			condition.put("groupType", "ruleGroup");
//			condition.put("draftFlag", "0");
//			List<CwmRuleGroups> cwmRuleDraftGroups = cwmRuleGroupsMapper.selectByCondition(condition);
//			if(cwmRuleDraftGroups != null && cwmRuleDraftGroups.size() >0) {
//				for(int k=0;k<cwmRuleDraftGroups.size();k++) {
//					CwmRuleGroups cwmRuleGroup = cwmRuleDraftGroups.get(k);
//					JSONObject ruleGroupNode=new JSONObject(true);
//					ruleGroupNode.put("label", cwmRuleGroup.getGroupName());
//					ruleGroupNode.put("id", cwmRuleGroup.getId());
//					ruleGroupNode.put("type", "ruleGroupEle");
//					ruleGroupNode.put("draftFlag", "0");
//					ruleDraftNodeArray.add(ruleGroupNode);
//				}
//				ruleDraftNode.put("children", ruleDraftNodeArray);
//			}
//			ruleDraftArray.add(ruleDraftNode);
//			//非草稿节点
//			JSONObject ruleNonDraftNode = new JSONObject(true);
//			JSONArray ruleNonDraftNodeArray = new JSONArray();
//			ruleNonDraftNode.put("label", "规则集");
//			ruleNonDraftNode.put("id", "nonDraft"+i);
//			ruleNonDraftNode.put("type", "ruleGroup");
//			condition.put("draftFlag", "1");
//			List<CwmRuleGroups> cwmRuleNonDraftGroups = cwmRuleGroupsMapper.selectByCondition(condition);
//			if(cwmRuleNonDraftGroups != null && cwmRuleNonDraftGroups.size() >0) {
//				for(int k=0;k<cwmRuleNonDraftGroups.size();k++) {
//					CwmRuleGroups cwmRuleGroup = cwmRuleNonDraftGroups.get(k);
//					JSONObject ruleGroupNode=new JSONObject(true);
//					ruleGroupNode.put("label", cwmRuleGroup.getGroupName());
//					ruleGroupNode.put("id", cwmRuleGroup.getId());
//					ruleGroupNode.put("type", "ruleGroupEle");
//					ruleGroupNode.put("draftFlag", "1");
//					ruleNonDraftNodeArray.add(ruleGroupNode);
//				}
//				ruleNonDraftNode.put("children", ruleNonDraftNodeArray);
//			}
//			ruleDraftArray.add(ruleNonDraftNode);
//			ruleNode.put("children", ruleDraftArray);
			
			List<CwmRuleGroups> cwmRuleGroups = cwmRuleGroupsMapper.selectByModuleId(moduleId,"ruleGroup");
			if(cwmRuleGroups!=null&&!cwmRuleGroups.isEmpty()) {
				for(int k=0;k<cwmRuleGroups.size();k++) {
					CwmRuleGroups cwmRuleGroup = cwmRuleGroups.get(k);
					JSONObject ruleGroupNode=new JSONObject(true);
					ruleGroupNode.put("label", cwmRuleGroup.getGroupName());
					ruleGroupNode.put("id", cwmRuleGroup.getId());
					ruleGroupNode.put("type", "ruleGroupEle");
					ruleGroupNode.put("draftFlag", StaticMethod.nullObject2String(cwmRuleGroup.getDraftFlag()));
					ruleArray.add(ruleGroupNode);
				}
				ruleNode.put("children", ruleArray);
			}
			ruleModuleInfoArray.add(ruleNode);

			//装配节点
			JSONObject assemblingNode=new JSONObject(true);
			//规则集array数据
			JSONArray assemblingArray  = new JSONArray();
			assemblingNode.put("label", "装配");
			assemblingNode.put("id", "assembling"+i);
			assemblingNode.put("type", "assembling");
			List<CwmRuleGroups> assemblingGroups = cwmRuleGroupsMapper.selectByModuleId(moduleId,"assembling");
			if(assemblingGroups!=null&&!assemblingGroups.isEmpty()) {
				for(int k=0;k<assemblingGroups.size();k++) {
					CwmRuleGroups assemblingGroup = assemblingGroups.get(k);
					JSONObject assemblingGroupNode=new JSONObject(true);
					assemblingGroupNode.put("label", assemblingGroup.getGroupName());
					assemblingGroupNode.put("id", assemblingGroup.getId());
					assemblingGroupNode.put("type", "assemblingEle");
					assemblingArray.add(assemblingGroupNode);
				}
				assemblingNode.put("children", assemblingArray);
			}
			ruleModuleInfoArray.add(assemblingNode);

			modulesNode.put("children", ruleModuleInfoArray);
			ruleModuleArray.add(modulesNode);
		}
		appNode.put("children", ruleModuleArray);
	}

	/**
	 * 获取对外接口相关输入参数信息
	 * @param groupId 规则集合id
	 * @param moduleId 规则模块id
	 * @return
	 */
	public List<ParamInterName> getParamInterInfo(String groupId,String moduleId) {
		//参数信息集合
		List<ParamInterName> paramInterNameList = new ArrayList<>();
		//规则集合关联的所有数据
		List<CwmRuleGroupRel> cwmRuleGroupRels = cwmRuleGroupRelMapper.selectRecursionByGroupId(groupId);
		List<String> ruleRelIds = new ArrayList<>();
		if(cwmRuleGroupRels!=null&&!cwmRuleGroupRels.isEmpty()) {
			for(int i=0;i<cwmRuleGroupRels.size();i++) {
				ruleRelIds.add(cwmRuleGroupRels.get(i).getRuleIdRel());
			}
			List<CwmRuleAtoms> cwmRuleAtoms=cwmRuleAtomsMapper.selectByAtomIds(ruleRelIds);
			if(cwmRuleAtoms!=null&&!cwmRuleAtoms.isEmpty()) {
				for(int i=0;i<cwmRuleAtoms.size();i++) {
					CwmRuleAtoms cwmRuleAtom =cwmRuleAtoms.get(i);
					//输入参数1的设置
					String inputParamType1 = cwmRuleAtom.getInputParamType1();
					if(!inputParamType1.contains("常量")&&!"计算变量".equals(inputParamType1)){
						ParamInterName paramInterName = new ParamInterName();
						paramInterName.setParamInterCnName(cwmRuleAtom.getInputCnParam1());
						paramInterName.setParamInterEnName(cwmRuleAtom.getInputEnParam1());
						paramInterNameList.add(paramInterName);
					}
					if("计算变量".equals(inputParamType1)){
						List<ParamInterName> paramInterList = getParamInterListInfo(moduleId,cwmRuleAtom.getInputEnParam1());
						paramInterNameList.addAll(paramInterList);
					}
					//输入参数2的设置
					String inputParamType2 = cwmRuleAtom.getInputParamType2();
					if(!inputParamType2.contains("常量")&&!"计算变量".equals(inputParamType2)){
						ParamInterName paramInterName2 = new ParamInterName();
						paramInterName2.setParamInterCnName(cwmRuleAtom.getInputCnParam2());
						paramInterName2.setParamInterEnName(cwmRuleAtom.getInputEnParam2());
						paramInterNameList.add(paramInterName2);
					}
					if("计算变量".equals(inputParamType2)){
						List<ParamInterName> paramInterList = getParamInterListInfo(moduleId,cwmRuleAtom.getInputEnParam2());
						paramInterNameList.addAll(paramInterList);
					}
					//输入参数3的设置
					String inputCnParam3 = cwmRuleAtom.getInputCnParam3();
					if(inputCnParam3!=null&&"".equals(inputCnParam3)) {
						String inputParamType3 =cwmRuleAtom.getInputParamType3();
						if(!inputParamType3.contains("常量")&&!"计算变量".equals(inputParamType3)){
							ParamInterName paramInterName3 = new ParamInterName();
							paramInterName3.setParamInterCnName(cwmRuleAtom.getInputCnParam3());
							paramInterName3.setParamInterEnName(cwmRuleAtom.getInputEnParam3());
							paramInterNameList.add(paramInterName3);
						}
						if("计算变量".equals(inputParamType3)){
							List<ParamInterName> paramInterList = getParamInterListInfo(moduleId,cwmRuleAtom.getInputEnParam3());
							paramInterNameList.addAll(paramInterList);
						}
					}
				}
			}
		}
		return removeInterDuplicate(paramInterNameList);
	}

	/**
	 * 获取输入参数信息
	 * @param groupId 规则集合id
	 * @param moduleId 规则模块id
	 * @return
	 */
	public List<ParamInfo> getParamInfo(String groupId,String moduleId) {
		//参数信息集合
		List<ParamInfo> paramInfos = new ArrayList<>();
		//规则集合关联的所有数据
		List<CwmRuleGroupRel> cwmRuleGroupRels = cwmRuleGroupRelMapper.selectRecursionByGroupId(groupId);
		List<String> ruleRelIds = new ArrayList<>();
		if(cwmRuleGroupRels!=null&&!cwmRuleGroupRels.isEmpty()) {
			for(int i=0;i<cwmRuleGroupRels.size();i++) {
				ruleRelIds.add(cwmRuleGroupRels.get(i).getRuleIdRel());
			}
			List<CwmRuleAtoms> cwmRuleAtoms=cwmRuleAtomsMapper.selectByAtomIds(ruleRelIds);
			if(cwmRuleAtoms!=null&&!cwmRuleAtoms.isEmpty()) {
				for(int i=0;i<cwmRuleAtoms.size();i++) {
					CwmRuleAtoms cwmRuleAtom =cwmRuleAtoms.get(i);
					//输入参数1的设置
                    String inputParamType1 = cwmRuleAtom.getInputParamType1();
                    if(!inputParamType1.contains("常量")&&!"计算变量".equals(inputParamType1)){
                        ParamInfo paramInfo1 = new ParamInfo();
                        paramInfo1.setInputCnParam(cwmRuleAtom.getInputCnParam1());
                        paramInfo1.setInputEnParam(cwmRuleAtom.getInputEnParam1());
                        paramInfo1.setInputParamType(inputParamType1);
                        paramInfos.add(paramInfo1);
                    }
                    if("计算变量".equals(inputParamType1)){
						List<ParamInfo> paramInfoList = getParamListInfo(moduleId,cwmRuleAtom.getInputEnParam1());
						paramInfos.addAll(paramInfoList);
					}
					//输入参数2的设置
                    String inputParamType2 = cwmRuleAtom.getInputParamType2();
                    if(!inputParamType2.contains("常量")&&!"计算变量".equals(inputParamType2)){
                        ParamInfo paramInfo2 = new ParamInfo();
                        paramInfo2.setInputCnParam(cwmRuleAtom.getInputCnParam2());
                        paramInfo2.setInputEnParam(cwmRuleAtom.getInputEnParam2());
                        paramInfo2.setInputParamType(inputParamType2);
                        paramInfos.add(paramInfo2);
                    }
					if("计算变量".equals(inputParamType2)){
						List<ParamInfo> paramInfoList = getParamListInfo(moduleId,cwmRuleAtom.getInputEnParam2());
						paramInfos.addAll(paramInfoList);
					}
					//输入参数3的设置
                    String inputCnParam3 = cwmRuleAtom.getInputCnParam3();
                    if(inputCnParam3!=null&&"".equals(inputCnParam3)) {
                        String inputParamType3 =cwmRuleAtom.getInputParamType3();
                        if(!inputParamType3.contains("常量")&&!"计算变量".equals(inputParamType3)){
                            ParamInfo paramInfo3 = new ParamInfo();
                            paramInfo3.setInputCnParam(inputCnParam3);
                            paramInfo3.setInputEnParam(cwmRuleAtom.getInputEnParam3());
                            paramInfo3.setInputParamType(cwmRuleAtom.getInputParamType3());
                            paramInfos.add(paramInfo3);
                        }
						if("计算变量".equals(inputParamType3)){
							List<ParamInfo> paramInfoList = getParamListInfo(moduleId,cwmRuleAtom.getInputEnParam3());
							paramInfos.addAll(paramInfoList);
						}
                    }
				}
			}
		}
		return removeDuplicate(paramInfos);
	}

	/**
	 * 规则集合
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	public RuleContent queryRuleContent(String groupId) throws Exception {
		//规则内容
		RuleContent ruleContent = new RuleContent();
		//规则集合信息的查询
		CwmRuleGroups cwmRuleGroups =cwmRuleGroupsMapper.selectByPrimaryKey(groupId);
		//模块id
		String moduleId = "";
		if(cwmRuleGroups!=null) {
			moduleId = StaticMethod.nullObject2String(cwmRuleGroups.getModuleId());
			ruleContent.setGroupName(StaticMethod.nullObject2String(cwmRuleGroups.getGroupName()));
			ruleContent.setGroupDescription(StaticMethod.nullObject2String(cwmRuleGroups.getGroupDescription()));
			List<ParamInterName> inputParamList = getParamInterInfo(groupId,moduleId);
			ruleContent.setInputParamList(inputParamList);
			//规则集合输出查询
			List<CwmRuleGroupOutput> ruleGroupOutputList =cwmRuleGroupOutputMapper.selectByGroupId(groupId);
			//规则集合输出id
			if(ruleGroupOutputList!=null&&!ruleGroupOutputList.isEmpty()) {
				//规则集合输出内容
				OutputParamInfo outputParamInfo = new OutputParamInfo();
				//规则集合输出参数的数量
				int outputParamNum = 0;
				//规则集合输出参数的查询
				//输出参数集合id
				for(int i=0;i<ruleGroupOutputList.size();i++){
					//规则集合输出实体
					CwmRuleGroupOutput cwmRuleGroupOutput = ruleGroupOutputList.get(i);
					String outputResult = cwmRuleGroupOutput.getGroupOutputResult();
					OutPutParamDetail outPutParamDetail = new OutPutParamDetail();
					outPutParamDetail.setOutputResult(outputResult);
					//规则集合输出参数
					List<CwmRuleGroupOutputParams> ruleGroupOutputParamsList = cwmRuleGroupOutputParamsMapper.selectByParamsId(cwmRuleGroupOutput.getGroupOutputParamsId());
					List<ParamInterName> outPutParamList = new ArrayList<>();
					if(ruleGroupOutputParamsList!=null&&ruleGroupOutputParamsList.size()>0){
						outputParamNum+=ruleGroupOutputParamsList.size();
						for(int j=0;j<ruleGroupOutputParamsList.size();j++){
							//该处获取的参数信息为规则集合输出的参数信息，只获取第一个参数作为对外接口的参数，其他通过传值赋值。
							ParamInterName paramInterName = new ParamInterName();
							paramInterName.setParamInterCnName(ruleGroupOutputParamsList.get(j).getOutputCnParam1());
							paramInterName.setParamInterEnName(ruleGroupOutputParamsList.get(j).getOutputEnParam1());
							outPutParamList.add(paramInterName);
						}
					}else{
						outputParamNum+=1;
					}
					outPutParamDetail.setOutputParamList(outPutParamList);
					if("true".equals(outputResult)){
						outputParamInfo.setOutputTrueInfo(outPutParamDetail);
					}else{
						outputParamInfo.setOutputFalseInfo(outPutParamDetail);
					}
				}
				outputParamInfo.setOutputParamNum(outputParamNum);
				ruleContent.setOutputParamInfo(outputParamInfo);
			}
		}
		return ruleContent;
	}

	/**
	 * 获取计算变量相关的对外参数信息
	 * @param moduleId 规则模块id
	 * @param inputEnParam 参数英文名
	 * @return
	 */
	public List<ParamInterName> getParamInterListInfo(String moduleId,String inputEnParam){
		List<ParamInterName> paramInterNameList = new ArrayList();
		//根据规则模块id和字段英文名查询数据
		List<CwmDataDetail> cwmDataDetailList =
				cwmDataDetailMapper.getDataDetailByModuleIdAndEnName(moduleId,inputEnParam);
		if(cwmDataDetailList!=null&&cwmDataDetailList.size()>0){
			CwmDataDetail cwmDataDetail = cwmDataDetailList.get(0);
			String calculate = StaticMethod.nullObject2String(cwmDataDetail.getFieldCalculate());
			calculate = calculate.replaceAll("\\+", ",");
			calculate = calculate.replaceAll("-", ",");
			calculate = calculate.replaceAll("\\*", ",");
			calculate = calculate.replaceAll("/", ",");
			String fieldEnNames[] = calculate.split(",");
			for(String feildEnName:fieldEnNames){
				List<CwmDataDetail> cwmDataDetails =
						cwmDataDetailMapper.getDataDetailByModuleIdAndEnName(moduleId,feildEnName);
				if(cwmDataDetails!=null&&cwmDataDetails.size()>0){
					CwmDataDetail thisCwmDataDetail = cwmDataDetails.get(0);
					String fieldCnName  =
							StaticMethod.nullObject2String(thisCwmDataDetail.getFieldCnName());
					String fieldEnName  =
							StaticMethod.nullObject2String(thisCwmDataDetail.getFieldEnName());
					ParamInterName paramInterName = new ParamInterName();
					paramInterName.setParamInterCnName(fieldCnName);
					paramInterName.setParamInterEnName(fieldEnName);
					paramInterNameList.add(paramInterName);
				}
			}
		}
		return paramInterNameList;
	}

	/**
	 * 获取计算变量相关的参数信息
	 * @param moduleId 规则模块id
	 * @param inputEnParam 参数英文名
	 * @return
	 */
	@Override
	public List<ParamInfo> getParamListInfo(String moduleId,String inputEnParam){
		List<ParamInfo> paramInfoList = new ArrayList();
		//根据规则模块id和字段英文名查询数据
		List<CwmDataDetail> cwmDataDetailList =
				cwmDataDetailMapper.getDataDetailByModuleIdAndEnName(moduleId,inputEnParam);
		if(cwmDataDetailList!=null&&cwmDataDetailList.size()>0){
			CwmDataDetail cwmDataDetail = cwmDataDetailList.get(0);
			String calculate = StaticMethod.nullObject2String(cwmDataDetail.getFieldCalculate());
			calculate = calculate.replaceAll("\\+", ",");
			calculate = calculate.replaceAll("-", ",");
			calculate = calculate.replaceAll("\\*", ",");
			calculate = calculate.replaceAll("/", ",");
			String fieldEnNames[] = calculate.split(",");
			for(String feildEnName:fieldEnNames){
				List<CwmDataDetail> cwmDataDetails =
						cwmDataDetailMapper.getDataDetailByModuleIdAndEnName(moduleId,feildEnName);
				if(cwmDataDetails!=null&&cwmDataDetails.size()>0){
					CwmDataDetail thisCwmDataDetail = cwmDataDetails.get(0);
					String fieldCnName  =
							StaticMethod.nullObject2String(thisCwmDataDetail.getFieldCnName());
					String fieldEnName  =
							StaticMethod.nullObject2String(thisCwmDataDetail.getFieldEnName());
					String fieldType =
							StaticMethod.nullObject2String(thisCwmDataDetail.getFieldType());
					ParamInfo paramInfo = new ParamInfo();
					paramInfo.setInputCnParam(fieldCnName);
					paramInfo.setInputEnParam(fieldEnName);
					paramInfo.setInputParamType(fieldType);
					paramInfoList.add(paramInfo);
				}
			}
		}
		return paramInfoList;
	}

	/**
	 * 规则集合信息查询
	 */
	public List<RuleGroupInfo> selectByGroupIds(String groupIds) {
		List<RuleGroupInfo> ruleGroupInfoList = new ArrayList<>();
		if(!"".equals(groupIds)) {
			//规则集合信息拼接
			String[] groupIdArr = groupIds.split(",");
			List<CwmRuleGroups> cwmRuleGroupsList = cwmRuleGroupsMapper.queryByGroupIds(groupIdArr);
			if(cwmRuleGroupsList!=null&&!cwmRuleGroupsList.isEmpty()) {
				for(int i=0;i<cwmRuleGroupsList.size();i++) {
					RuleGroupInfo ruleGroupInfo = new RuleGroupInfo();
					CwmRuleGroups cwmRuleGroups = cwmRuleGroupsList.get(i);
					//规则集合表内容
					ruleGroupInfo.setCwmRuleGroups(
							new CwmRuleGroups(
									cwmRuleGroups.getId(), cwmRuleGroups.getGroupName(), cwmRuleGroups.getGroupDescription()));
					//规则集合输入信息部分
					List<RuleGroupInputInfo> inputInfo = setRuleGroupInputInfo(cwmRuleGroups);
					//规则集合输出信息部分
					RuleGroupOutputInfo outputInfo = setRuleGroupOutputInfo(cwmRuleGroups);
					ruleGroupInfo.setRuleGroupInputInfoList(inputInfo);
					ruleGroupInfo.setRuleGroupOutPutInfo(outputInfo);
					ruleGroupInfoList.add(ruleGroupInfo);
				}
			}
		}
		return ruleGroupInfoList;
	}
	/**
	 * 规则集合输入信息内容
	 * @param cwmRuleGroups
	 * @return
	 */
	public List<RuleGroupInputInfo> setRuleGroupInputInfo(CwmRuleGroups cwmRuleGroups){
		List<RuleGroupInputInfo> ruleGroupInputInfoList = new ArrayList<>();
		String groupId = cwmRuleGroups.getId();
		List<CwmRuleGroupRel>  cwmRuleGroupRelList= cwmRuleGroupRelMapper.selectByGroupId(groupId);
		if(cwmRuleGroupRelList!=null&&!cwmRuleGroupRelList.isEmpty()) {
			for(int j=0;j<cwmRuleGroupRelList.size();j++) {
				RuleGroupInputInfo ruleGroupInputInfo = new RuleGroupInputInfo();
				CwmRuleGroupRel cwmRuleGroupRel = cwmRuleGroupRelList.get(j);
				ruleGroupInputInfo.setCwmRuleGroupRel(
						new CwmRuleGroupRel(cwmRuleGroupRel.getOrderBy(), cwmRuleGroupRel.getRuleInfoRel()));
				ruleGroupInputInfo.setParamInfoList(getParamInfo(cwmRuleGroupRel.getRuleIdRel(),cwmRuleGroups.getModuleId()));
				ruleGroupInputInfoList.add(ruleGroupInputInfo);
			}
		}
		return ruleGroupInputInfoList;
	}
	
	/**
	 * 规则集合输出信息内容
	 * @param cwmRuleGroups
	 * @return
	 */
	public RuleGroupOutputInfo  setRuleGroupOutputInfo(CwmRuleGroups cwmRuleGroups){
		RuleGroupOutputInfo ruleGroupOutPutInfo = new RuleGroupOutputInfo();
		String groupId = cwmRuleGroups.getId();
		List<CwmRuleGroupOutput>  ruleGroupOutputList= cwmRuleGroupOutputMapper.selectByGroupId(groupId);
		if(ruleGroupOutputList!=null&&!ruleGroupOutputList.isEmpty()) {
			CwmRuleGroupOutput cwmRuleGroupOutput = ruleGroupOutputList.get(0);
			ruleGroupOutPutInfo.setCwmRuleGroupOutput(new CwmRuleGroupOutput(cwmRuleGroupOutput.getGroupOutputResult(),cwmRuleGroupOutput.getGroupOutputExplain()));
		}
		return ruleGroupOutPutInfo;
	}

	/**
	 * 去除对外接口参数集合重复元素
	 * @param list
	 * @return
	 */
	public  List<ParamInterName> removeInterDuplicate(List<ParamInterName> list) {
		List<ParamInterName> result = new LinkedList<>();
		for (ParamInterName paramInfo : list) {
			boolean b = result.stream().anyMatch(p -> p.getParamInterEnName().equals(paramInfo.getParamInterEnName()));
			if (!b) {
				result.add(paramInfo);
			}
		}
		return result;
	}

	/**
	 * 规则集合关联重复元素内容,目前要求不重复
	 * @param list
	 * @return
	 */
	public String getDuplicateInfo(List<CwmRuleGroupRel> list) {
		StringBuilder duplicateInfo = new StringBuilder();
		//输出内容重复的校验集合
		List<String> duplicateId = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			String ruleIdRel_i = StaticMethod.nullObject2String(list.get(i).getRuleIdRel());
			String ruleInfoRel = StaticMethod.nullObject2String(list.get(i).getRuleInfoRel());
			int dataNum = 0;
			for (int i1 = 0; i1 < list.size(); i1++) {
				String ruleIdRel_i1 = StaticMethod.nullObject2String(list.get(i1).getRuleIdRel());
				if(ruleIdRel_i.equals(ruleIdRel_i1)){
					dataNum++;
				}
			}
			if(dataNum>1){
				if(!duplicateId.contains(ruleIdRel_i)){
					duplicateInfo.append("标识为"+ruleIdRel_i+"的"+ruleInfoRel+",");
				}
				duplicateId.add(ruleIdRel_i);
			}
		}
		return duplicateInfo.toString();
	}

	/**
	 * 去除参数集合重复元素
	 * @param list
	 * @return
	 */
	public  List<ParamInfo> removeDuplicate(List<ParamInfo> list) {
		List<ParamInfo> result = new LinkedList<>();
		for (ParamInfo paramInfo : list) {
			boolean b = result.stream().anyMatch(p -> p.getInputEnParam().equals(paramInfo.getInputEnParam()));
			if (!b) {
				result.add(paramInfo);
			}
		}
		return result;
	}

	/**
	 * 根据参数判断参数类型
	 * @param numStr 数字类型字典值
	 * @param characterStr 字符类型字典值
	 * @param timeStr 时间类型字典值
	 * @param paramType
	 * @return
	 */
	public String getFieldType(String numStr,String characterStr,String timeStr,String paramType){
		if(numStr.contains(paramType)){
			return "数字";
		}else if(characterStr.contains(paramType)){
			return "字符";
		}else if(timeStr.contains(paramType)){
			return "时间";
		}else{
			return "";
		}
	}

	/*@Override
	@Transactional
	public void saveRuleAtomConfig(CwmRuleAtomConfig cwmRuleAtomConfig) throws Exception {
		//原子规则保存
		CwmRuleAtoms cwmRuleAtoms = cwmRuleAtomConfig.getCwmRuleAtoms();
		String atomId = UUIDHexGenerator.getInstance().getID();
		if(cwmRuleAtoms!=null) {
			cwmRuleAtoms.setId(atomId);
			cwmRuleAtomsMapper.insert(cwmRuleAtoms);
		}
		//原子规则输入的保存
		CwmRuleAtomInput cwmRuleAtomInput=cwmRuleAtomConfig.getCwmRuleAtomInput();
		if(cwmRuleAtomInput!=null) {
			cwmRuleAtomInput.setAtomId(atomId);
			cwmRuleAtomInputMapper.insert(cwmRuleAtomInput);
		}
		//原子规则输出的保存
		List<CwmRuleAtomOutput> cwmRuleAtomOutputList=cwmRuleAtomConfig.getCwmRuleAtomOutputList();
		if(cwmRuleAtomOutputList!=null&&!cwmRuleAtomOutputList.isEmpty()) {
			for(int i=0;i<cwmRuleAtomOutputList.size();i++) {
				cwmRuleAtomOutputList.get(i).setAtomId(atomId);
			}
			cwmRuleAtomOutputMapper.batchInsert(cwmRuleAtomOutputList);
		}
		//原子规则集合关联的保存
		CwmRuleAtomGroupRel cwmRuleAtomGroupRel=cwmRuleAtomConfig.getCwmRuleAtomGroupRel();
		if(cwmRuleAtomGroupRel!=null) {
			cwmRuleAtomGroupRel.setAtomId(atomId);
			cwmRuleAtomGroupRelMapper.insert(cwmRuleAtomGroupRel);
		}
	}

	@Override
	public CwmRuleAtomConfig queryCwmRuleAtomConfig(String atomId) {
		CwmRuleAtomConfig cwmRuleAtomConfig = new CwmRuleAtomConfig();
		//查询原子规则信息
		CwmRuleAtoms cwmRuleAtoms=cwmRuleAtomsMapper.selectByPrimaryKey(atomId);
		cwmRuleAtomConfig.setCwmRuleAtoms(cwmRuleAtoms);
		//查询原子规则输入信息
		List<CwmRuleAtomInput> cwmRuleAtomInputList=cwmRuleAtomInputMapper.selectByAtomId(atomId);
		cwmRuleAtomConfig.setCwmRuleAtomInput(cwmRuleAtomInputList.get(0));
		//查询原子规则输出信息
		List<CwmRuleAtomOutput> cwmRuleAtomOutputList = cwmRuleAtomOutputMapper.selectByAtomId(atomId);
		cwmRuleAtomConfig.setCwmRuleAtomOutputList(cwmRuleAtomOutputList);
		return cwmRuleAtomConfig;
	}

	@Override
	public void updateRuleAtomConfig(CwmRuleAtomConfig cwmRuleAtomConfig) throws Exception {
		//原子规则修改
		CwmRuleAtoms cwmRuleAtoms = cwmRuleAtomConfig.getCwmRuleAtoms();
		if(cwmRuleAtoms!=null) {
			cwmRuleAtomsMapper.updateByPrimaryKey(cwmRuleAtoms);
		}
		//原子规则输入的修改
		CwmRuleAtomInput cwmRuleAtomInput=cwmRuleAtomConfig.getCwmRuleAtomInput();
		if(cwmRuleAtomInput!=null) {
			cwmRuleAtomInputMapper.updateByPrimaryKey(cwmRuleAtomInput);
		}
		//原子规则输出的修改
		List<CwmRuleAtomOutput> cwmRuleAtomOutputList=cwmRuleAtomConfig.getCwmRuleAtomOutputList();
		if(cwmRuleAtomOutputList!=null&&!cwmRuleAtomOutputList.isEmpty()) {
			cwmRuleAtomOutputMapper.batchUpdate(cwmRuleAtomOutputList);
		}
		//原子规则集合关联的修改
		CwmRuleAtomGroupRel cwmRuleAtomGroupRel=cwmRuleAtomConfig.getCwmRuleAtomGroupRel();
		if(cwmRuleAtomGroupRel!=null) {
			cwmRuleAtomGroupRelMapper.updateByPrimaryKey(cwmRuleAtomGroupRel);
		}
	}
	
	@Override
	public void saveRuleAtomGroupConfig(List<CwmRuleAtomGroupConfig> ruleAtomGroupConfigList) {
		//配置数据的解析
		List<CwmRuleAtomGroupOutput> cwmRuleAtomGroupOutputList= new ArrayList<>();
		List<CwmRuleAtomGroupOutputRel> cwmRuleAtomGroupOutputRelList = new ArrayList<>();
		if(ruleAtomGroupConfigList!=null&&!ruleAtomGroupConfigList.isEmpty()) {
			for(int i=0;i<ruleAtomGroupConfigList.size();i++) {
				cwmRuleAtomGroupOutputList.add(ruleAtomGroupConfigList.get(i).getCwmRuleAtomGroupOutput());
				cwmRuleAtomGroupOutputRelList.addAll(ruleAtomGroupConfigList.get(i).getCwmRuleAtomGroupOutputRelList());
			}
		}
		//原子规则集合输出保存
		if(cwmRuleAtomGroupOutputList!=null&&!cwmRuleAtomGroupOutputList.isEmpty()) {
			cwmRuleAtomGroupOutputMapper.batchInsert(cwmRuleAtomGroupOutputList);
		}
		//原子规则集合关联原子规则输出关系
		if(cwmRuleAtomGroupOutputRelList!=null&&!cwmRuleAtomGroupOutputRelList.isEmpty()) {
			cwmRuleAtomGroupOutputRelMapper.batchInsert(cwmRuleAtomGroupOutputRelList);
		}
	}

	@Override
	public List<CwmRuleAtomGroupConfig> getRuleAtomGroupConfig(String atomGroupId) {
		List<CwmRuleAtomGroupConfig> cwmRuleAtomGroupConfigList  = new ArrayList<>();
		//查询原子规则集合输出数据
		List<CwmRuleAtomGroupOutput> cwmRuleAtomGroupOutputList =
				cwmRuleAtomGroupOutputMapper.selectByAtomGroupId(atomGroupId);
		//封装cwmRuleAtomGroupConfigList
		if(cwmRuleAtomGroupOutputList!=null&&!cwmRuleAtomGroupOutputList.isEmpty()) {
			String atomGroupRelId = "";
			CwmRuleAtomGroupOutput cwmRuleAtomGroupOutput = null;
			for(int i=0;i<cwmRuleAtomGroupOutputList.size();i++) {
				cwmRuleAtomGroupOutput = cwmRuleAtomGroupOutputList.get(i);
				atomGroupRelId=cwmRuleAtomGroupOutput.getAtomGroupRelId();
				List<CwmRuleAtomGroupOutputRel> cwmRuleAtomGroupOutputRelList =
						cwmRuleAtomGroupOutputRelMapper.selectByAtomGroupRelId(atomGroupRelId);
				CwmRuleAtomGroupConfig cwmRuleAtomGroupConfig = new CwmRuleAtomGroupConfig();
				cwmRuleAtomGroupConfig.setCwmRuleAtomGroupOutput(cwmRuleAtomGroupOutput);
				cwmRuleAtomGroupConfig.setCwmRuleAtomGroupOutputRelList(cwmRuleAtomGroupOutputRelList);
				cwmRuleAtomGroupConfigList.add(cwmRuleAtomGroupConfig);
			}
		}
		//原子规则集合关联原子规则输出关系
		return cwmRuleAtomGroupConfigList;
	}

	*//**
	 * 规则集合关联原子规则集合信息查询
	 *//*
	@Override
	public List<CwmRuleGroupRelAtomGroupConfig> getRuleGroupRelAtomGroupConfig(String groupId) {
		//查询规则集合关联原子规则关联关系
		List<CwmRuleGroupRel> CwmRuleGroupRels =  cwmRuleGroupRelMapper.selectByGroupId(groupId);
		//查询所有原子规则集合
		List<CwmRuleAtomGroup> CwmRuleAtomGroups = cwmRuleAtomGroupMapper.findAll();
		//设置关联关系和列表展示数据
		List<CwmRuleGroupRelAtomGroupConfig> CwmRuleGroupRelAtomGroupConfigs = new ArrayList<CwmRuleGroupRelAtomGroupConfig>();
		for(int i=0;i<CwmRuleAtomGroups.size();i++) {
			CwmRuleAtomGroup atomGroup = CwmRuleAtomGroups.get(i);
			CwmRuleGroupRelAtomGroupConfig config = new CwmRuleGroupRelAtomGroupConfig();
			config.setRelated("false");
			for(int j=0;j<CwmRuleGroupRels.size();j++) {
				CwmRuleGroupRel rel = CwmRuleGroupRels.get(j);
				if(rel.getGroupId().equals(atomGroup.getId())) {
					//关联
					config.setGroupId(rel.getGroupId());
					config.setRelated("true");
				}
			}
			config.setAtomGroupId(atomGroup.getId());
			config.setAtomGroupDescription(atomGroup.getAtomGroupDescription());
			CwmRuleGroupRelAtomGroupConfigs.add(config);
		}
		return CwmRuleGroupRelAtomGroupConfigs;
	}
	
	@Override
	public void updateRuleAtomGroupConfig(List<CwmRuleAtomGroupConfig> ruleAtomGroupConfigList) {
		//配置数据的解析
		List<CwmRuleAtomGroupOutput> cwmRuleAtomGroupOutputList= new ArrayList<>();
		List<CwmRuleAtomGroupOutputRel> cwmRuleAtomGroupOutputRelList = new ArrayList<>();
		if(ruleAtomGroupConfigList!=null&&!ruleAtomGroupConfigList.isEmpty()) {
			for(int i=0;i<ruleAtomGroupConfigList.size();i++) {
				cwmRuleAtomGroupOutputList.add(ruleAtomGroupConfigList.get(i).getCwmRuleAtomGroupOutput());
				cwmRuleAtomGroupOutputRelList.addAll(ruleAtomGroupConfigList.get(i).getCwmRuleAtomGroupOutputRelList());
			}
		}
		//原子规则集合输出更改
		if(cwmRuleAtomGroupOutputList!=null&&!cwmRuleAtomGroupOutputList.isEmpty()) {
			cwmRuleAtomGroupOutputMapper.batchUpdate(cwmRuleAtomGroupOutputList);
		}
		//原子规则集合关联原子规则输出关系
		if(cwmRuleAtomGroupOutputRelList!=null&&!cwmRuleAtomGroupOutputRelList.isEmpty()) {
			cwmRuleAtomGroupOutputRelMapper.batchUpdate(cwmRuleAtomGroupOutputRelList);
		}
	}
	
	*//**
	 * 规则集合关联插入
	 * @param CwmRuleGroupRels
	 *//*
	@Override
	public void saveGroupRelAtomGroup(List<CwmRuleGroupRel> CwmRuleGroupRels) {
		//先删除所有和当前规则集合相关的关联 再插入
		if(CwmRuleGroupRels != null && CwmRuleGroupRels.size() > 0) {
			String groupId = StaticMethod.nullObject2String(CwmRuleGroupRels.get(0).getGroupId());
			cwmRuleGroupRelMapper.deleteByGroupId(groupId);
		}
		cwmRuleGroupRelMapper.batchInsert(CwmRuleGroupRels);
	}*/
}

