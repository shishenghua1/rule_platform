package com.boco.eoms.rule.cwmsysruleatomgroup.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boco.eoms.rule.cwmsysruleatomgroup.model.CwmRuleAtomGroup;
import com.boco.eoms.rule.cwmsysruleatomgroup.service.ICwmRuleAtomGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**

* 创建时间：2019年6月25日 上午9:35:59

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：原子规则集合restful 相关接口

*/
@RestController
@RequestMapping("/api/v1/atom/group")
@Api(value="原子规则集合controller",tags={"原子规则集合restful接口"})
public class CwmRuleAtomGroupRestController {/*
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleAtomGroupRestController.class);
	
	@Autowired
	private ICwmRuleAtomGroupService cwmRuleAtomGroupService;
	
	*//**
	 * 获取规则场景信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)  
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiOperation(value="原子规则集合查询",notes="")
	public List<CwmRuleAtomGroup> query(){
		try {
			return cwmRuleAtomGroupService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	*//**
	 *原子规则集合的创建
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@ApiOperation(value="原子规则集合插入",notes="原子规则集合插入")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(method = RequestMethod.POST)
	public void saveCwmRuleAtomGroup(@RequestBody CwmRuleAtomGroup CwmRuleAtomGroup) {
		try {
			cwmRuleAtomGroupService.insert(CwmRuleAtomGroup);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/}

