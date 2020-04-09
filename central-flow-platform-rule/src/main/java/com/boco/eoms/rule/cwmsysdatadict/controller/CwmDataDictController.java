package com.boco.eoms.rule.cwmsysdatadict.controller;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.rule.cwmsysdatadetail.model.CwmDataDetail;
import com.boco.eoms.rule.cwmsysrulerel.controller.CwmRuleRelRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.cwmsysdatadict.model.CwmDataDict;
import com.boco.eoms.rule.cwmsysdatadict.service.ICwmDataDictService;
import com.boco.eoms.rule.cwmsysdatamodule.model.CwmDataModule;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

/**

* 创建时间：2019年7月18日 上午11:33:28

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：字典数据表restful接口

*/
@RestController
@RequestMapping("/api/v1/datadict")
@Api(value="数据字典表ontroller",tags={"数据字典restful接口"})
public class CwmDataDictController {
	
	@Autowired
	private ICwmDataDictService cwmDataDictService;

	private Logger logger = LoggerFactory.getLogger(CwmDataDictController.class);

	/**
	 *批量操作数据字典信息
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="批量操作数据字典信息",notes="批量操作数据字典信息")
	@ApiResponses({ @ApiResponse(code = 500, message = "保存异常") })
	@RequestMapping(method = RequestMethod.POST)
	public JSONObject saveOperate(@RequestBody List<CwmDataDict> cwmDataDictList){
		try {
			return cwmDataDictService.saveOperate(cwmDataDictList);
		} catch (Exception e) {
			logger.error("批量操作数据字典信息报错",e);
		}
		return null;
	}

	/**
	 *批量增加数据字典
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(value="批量增加数据字典",notes="批量增加数据字典")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody List<CwmDataDict> cwmDataDictList){
		try {
			cwmDataDictService.batchInsert(cwmDataDictList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/
	
	/**
	 *修改数据字典信息，单个修改
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(value="数据字典修改",notes="数据字典修改")
	@ApiResponses({ @ApiResponse(code = 500, message = "修改异常") })
	@RequestMapping(method=RequestMethod.PUT)
	public void update(@RequestBody CwmDataDict cwmDataDict){
		try {
			cwmDataDictService.updateByPrimaryKey(cwmDataDict);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/**
	 *条件删除数据字典
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteById",method=RequestMethod.DELETE)
	@ApiOperation(value="数据字典删除",notes="数据字典标识id")
	@ApiResponses({ @ApiResponse(code = 500, message = "删除异常") })
	@ApiImplicitParams({
			@ApiImplicitParam(name="id",value="数据字典id",required=true,paramType="query",dataType = "String"),
			@ApiImplicitParam(name="dataModuleId",value="数据模块id",required=true,paramType="query",dataType = "String"),
			@ApiImplicitParam(name="dictEnName",value="数据字典英文名",required=true,paramType="query",dataType = "String")
	})
	public JSONObject deleteByCondition(String id, String dataModuleId, String dictEnName){
		try {
			return cwmDataDictService.deleteByCondition(id,dataModuleId,dictEnName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取数据字典的数据
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@GetMapping("getDataDict")
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiOperation(value="数据字典查询",notes="根据字典中文名查询数据字典",response = CwmDataDict.class)
	@ApiImplicitParams({
	    @ApiImplicitParam(name="dictCnName",value="字典中文名",required=false,paramType="query",dataType = "String"),
	    @ApiImplicitParam(name="parentDictId",value="数据表常量类型字段id",required=true,paramType="query",dataType = "String")
	})
	public Object query(@ApiParam(name="dictCnName",value="字典中文名",required=false) String dictCnName,@RequestParam String parentDictId){
		try {
			return cwmDataDictService.selectByCondition(StaticMethod.nullObject2String(dictCnName),parentDictId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

