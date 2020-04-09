package com.boco.eoms.rule.cwmsysruleconfig.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.boco.eoms.rule.cwmsysruleconfig.service.LoadRuleService;

/**
 * 项目启动加载规则到内存
 */
@Component
@Order(value=1)
public class AutoLoadRule implements CommandLineRunner {

    @Autowired
    private LoadRuleService loadRuleService;

    @Override
    public void run(String... args) throws Exception {
//        loadRuleService.loadService(loadRuleService.getAllDrl());
    }
}