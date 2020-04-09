package com.boco.eoms.rule.cwmsysdatamodule.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boco.eoms.rule.cwmsysdatamodule.model.CwmDataModule;
import com.boco.eoms.rule.cwmsysdatamodule.service.ICwmDataModuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**

* 创建时间：2019年7月2日 上午10:47:46

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：数据模块相关restful接口

*/
@RestController
@RequestMapping("/api/v1/datamodule")
@Api(value="数据模块controller",tags={"数据模块restful接口"})
public class CwmDataModuleController {
	
	@Autowired
	private ICwmDataModuleService cwmDataModuleService;
	
	/**
	 * 获取数据模块的数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*过期@ResponseBody
	@RequestMapping(value = "{dataModule}", method = RequestMethod.GET)  
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiOperation(value="数据模块查询",notes="根据数据所属模块查询数据模块",response = CwmDataModule.class)
    @ApiImplicitParam(paramType="path", name = "dataModule", value = "数据所属模块", required = true, dataType = "String")
	public Object query(@PathVariable("dataModule") String dataModule){
		try {
			return cwmDataModuleService.selectByDataModule(StaticMethod.nullObject2String(dataModule));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	/**
	 * 获取数据模块的数据
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@GetMapping("getDataModule")
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiOperation(value="数据模块查询",notes="条件查询数据模块",response = CwmDataModule.class)
	@ApiImplicitParams({
	    @ApiImplicitParam(name="moduleId",value="模块分类对应标识	",required=true,paramType="query",dataType = "String"),
	    @ApiImplicitParam(name="dataType",value="数据类型",required=true,paramType="query",dataType = "String")
	})
	public Object query(@RequestParam String moduleId,@RequestParam String dataType){
		try {
			return cwmDataModuleService.selectByCondition(moduleId, dataType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *增加数据模块信息，单个增加
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="数据模块插入",notes="数据模块插入")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(method = RequestMethod.POST)
	public JSONObject save(@RequestBody CwmDataModule cwmDataModule){
		try {
			return cwmDataModuleService.insert(cwmDataModule);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *修改数据模块信息，单个修改
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="数据模块修改",notes="数据模块修改")
	@ApiResponses({ @ApiResponse(code = 500, message = "修改异常") })
	@RequestMapping(method=RequestMethod.PUT)
	public JSONObject update(@RequestBody CwmDataModule cwmDataModule){
		try {
			return cwmDataModuleService.updateByPrimaryKey(cwmDataModule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *条件删除数据模块
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ApiOperation(value="数据模块删除",notes="数据模块标识id")
	@ApiResponses({ @ApiResponse(code = 500, message = "删除异常") })
	@ApiImplicitParam(paramType="path", name = "id", value = "数据模块标识id", required = true, dataType = "String")
	public JSONObject delete(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
		try {
			return cwmDataModuleService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *数据模块的excel导出
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*过期@ApiOperation(value="数据模块导出",notes="全量数据导出")
	@ApiResponses({ @ApiResponse(code = 500, message = "导出失败") })
	@GetMapping("download")
	public void download(HttpServletResponse response){
		try {
			cwmDataModuleService.download(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/
}

