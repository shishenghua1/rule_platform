package com.boco.eoms.rule.cwmsysrulegrouplibraryrel.controller;

import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.model.CwmRuleGroupLibraryRel;
import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.service.ICwmRuleGroupLibraryRelService;
import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ssh
 * @description:
 * @date 2019/8/2815:35
 */
@RestController
@RequestMapping("/api/v1/library")
@Api(value="规则集合库关联controller",tags={"规则集合库关联restful接口"})
public class CwmRuleGroupLibraryRelController {

    @Autowired
    private ICwmRuleGroupLibraryRelService cwmRuleGroupLibraryRelService;

    /**
     *根据规则集合id删除规则集合关联库
     * @return
     * @throws Exception
     */
    /*@RequestMapping(value="/{groupId}",method= RequestMethod.DELETE)
    @ApiOperation(value="规则集合库关联删除",notes="规则集合关联标识id")
    @ApiResponses({ @ApiResponse(code = 500, message = "删除异常") })
    @ApiImplicitParam(paramType="path", name = "groupId", value = "规则集合id", required = true, dataType = "String")
    public void delete(@PathVariable("groupId") String groupId) {
        try {
            cwmRuleGroupLibraryRelService.deleteByGroupId(groupId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     *增加规则集合库关联信息
     * @return
     * @throws Exception
     */
	/*@ApiOperation(value="规则集合库关联插入",notes="规则集合库关联插入")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(method = RequestMethod.POST)
	public void saveRuleGroupLibraryRel(@RequestBody List<CwmRuleGroupLibraryRel> ruleGroupLibraryRelList) {
		try {
            cwmRuleGroupLibraryRelService.batchInsert(ruleGroupLibraryRelList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
