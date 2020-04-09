package com.boco.eoms.rule.cwmsysdatadetail.controller;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.cwmsysdatadetail.model.CwmDataDetail;
import com.boco.eoms.rule.cwmsysdatadetail.service.ICwmDataDetailService;
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

* 创建时间：2019年6月25日 上午9:35:59

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：数据详情表restful 相关接口

*/
@RestController
@RequestMapping("/api/v1/datadetail")
@Api(value="数据详情关联controller",tags={"数据详情restful接口"})
public class CwmDataDetailController {
	
	@Autowired
	private ICwmDataDetailService cwmDataDetailService;
	
	/**
	 * 获取数据详情的数据
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@GetMapping("getDatadetail")
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiOperation(value="数据详情查询",notes="根据字段中文名查询数据详情",response = CwmDataModule.class)
	@ApiImplicitParams({
	    @ApiImplicitParam(name="fieldCnName",value="字段中文名",required=false,paramType="query",dataType = "String"),
	    @ApiImplicitParam(name="dataModuleId",value="数据模块id",required=true,paramType="query",dataType = "String")
	})
	public Object query(@ApiParam(name="fieldCnName",value="字段中文名",required=false) String fieldCnName,
			@ApiParam(name="dataModuleId",value="数据模块id",required=true)  String dataModuleId){
		try {
			return cwmDataDetailService.selectByFieldCnName(StaticMethod.nullObject2String(fieldCnName),dataModuleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 *批量操作数据详情信息
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="批量保存数据详情信息",notes="批量保存数据详情信息")
	@ApiResponses({ @ApiResponse(code = 500, message = "保存异常") })
	@RequestMapping(method = RequestMethod.POST)
	public JSONObject saveOperate(@RequestBody List<CwmDataDetail> cwmDataDetailList){
		try {
			return cwmDataDetailService.saveOperate(cwmDataDetailList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *批量增加数据详情信息
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(value="批量增加数据详情信息",notes="批量增加数据详情信息")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody List<CwmDataDetail> cwmDataDetailList){
		try {
			cwmDataDetailService.batchInsert(cwmDataDetailList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/

	/**
	 *增加数据详情信息，单个增加
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(value="数据详情插入",notes="数据详情插入")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody CwmDataDetail cwmDataDetail){
		try {
			cwmDataDetailService.insert(cwmDataDetail);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/
	
	/**
	 *修改数据详情信息，单个修改
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(value="数据详情修改",notes="数据详情修改")
	@ApiResponses({ @ApiResponse(code = 500, message = "修改异常") })
	@RequestMapping(method=RequestMethod.PUT)
	public void update(@RequestBody CwmDataDetail cwmDataDetail){
		try {
			cwmDataDetailService.updateByPrimaryKey(cwmDataDetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	/**
	 *条件删除数据详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteById",method=RequestMethod.DELETE)
	@ApiOperation(value="数据详情删除",notes="数据详情标识id")
	@ApiResponses({ @ApiResponse(code = 500, message = "删除异常") })
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="数据详情id",required=true,paramType="query",dataType = "String"),
		@ApiImplicitParam(name="dataModuleId",value="数据模块id",required=true,paramType="query",dataType = "String"),
		@ApiImplicitParam(name="fieldEnName",value="数据详情字段英文名",required=true,paramType="query",dataType = "String")
	})
	public JSONObject deleteByCondition(String id,String dataModuleId,String fieldEnName){
		try {
			return cwmDataDetailService.deleteByCondition(id,dataModuleId,fieldEnName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *数据详情的excel导入
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="数据详情导入",notes="数据详情导入")
	@ApiResponses({ @ApiResponse(code = 500, message = "导入失败") })
	@ApiImplicitParam(name="dataModuleId",value="数据模块id",dataType="String", paramType = "query")
	@PostMapping("upload")
	public JSONObject upload(MultipartFile file, @RequestParam(value="dataModuleId",required=false)String dataModuleId){
		try {
			return cwmDataDetailService.upload(file,dataModuleId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *数据详情的excel导出
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="数据详情导出",notes="数据详情导出")
	@ApiResponses({ @ApiResponse(code = 500, message = "导出失败") })
	@ApiImplicitParam(name="dataModuleId",value="数据模块id",dataType="String", paramType = "path")
	@GetMapping("download")
	public void download(String dataModuleId,HttpServletResponse response){
		try {
			cwmDataDetailService.download(dataModuleId,response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 模版下载
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="模版下载",notes="模版下载")
	@ApiResponses({ @ApiResponse(code = 500, message = "模版下载") })
	@GetMapping("downloadTemplate")
	public void downloadTemplate(HttpServletResponse response){
		try {
			cwmDataDetailService.downloadTemplate(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

