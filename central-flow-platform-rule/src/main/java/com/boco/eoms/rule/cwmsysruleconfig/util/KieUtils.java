package com.boco.eoms.rule.cwmsysruleconfig.util;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Objects;

/**
 * drools静态对象类
 */
public class KieUtils {

    private static KieContainer kieContainer;

    private static KieSession kieSession;

    private static KieServices kieServices;

    private static KieRepository kieRepository;

    private static KieFileSystem kieFileSystem;

    public static void initAndNotClear(){
        if (Objects.isNull(kieServices))
            kieServices = KieServices.Factory.get();

        if (Objects.isNull(kieRepository))
            kieRepository = kieServices.getRepository();

        if (Objects.isNull(kieFileSystem))
            kieFileSystem = kieServices.newKieFileSystem();
    }

    public static KieContainer getKieContainer() {
        return kieContainer;
    }

    public static void setKieContainer(KieContainer kieContainer) {
        KieUtils.kieContainer = kieContainer;
    }

    public static KieSession getKieSession() {
        return kieSession;
    }

    public static void setKieSession(KieSession kieSession) {
        KieUtils.kieSession = kieSession;
    }

    public static KieServices getKieServices() {
        return kieServices;
    }

    public static void setKieServices(KieServices kieServices) {
        KieUtils.kieServices = kieServices;
    }

    public static KieRepository getKieRepository() {
        return kieRepository;
    }

    public static void setKieRepository(KieRepository kieRepository) {
        KieUtils.kieRepository = kieRepository;
    }

    public static KieFileSystem getKieFileSystem() {
        return kieFileSystem;
    }

    public static void setKieFileSystem(KieFileSystem kieFileSystem) {
        KieUtils.kieFileSystem = kieFileSystem;
    }
}
