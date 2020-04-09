package com.boco.eoms.rule.cwmsysrulegrouplibraryrel.service.impl;

import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.mapper.CwmRuleGroupLibraryRelMapper;
import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.model.CwmRuleGroupLibraryRel;
import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.service.ICwmRuleGroupLibraryRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ssh
 * @description:
 * @date 2019/8/28 14:40
 */
@Service("cwmRuleGroupLibraryRelService")
public class CwmRuleGroupLibraryRelServiceImpl implements ICwmRuleGroupLibraryRelService {

    @Autowired
    private CwmRuleGroupLibraryRelMapper cwmRuleGroupLibraryRelMapper;

    @Override
    public void batchInsert(List<CwmRuleGroupLibraryRel> list) {
        cwmRuleGroupLibraryRelMapper.batchInsert(list);
    }

    @Override
    public void deleteByGroupId(String groupId) {
        cwmRuleGroupLibraryRelMapper.deleteByGroupId(groupId);
    }
}
