package com.commandcenter.common.activemq.handler.bean;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author r25437
 * @create 2018-09-06 11:46
 * @desc Mapping
 **/
public class Mapping {
    private Mapping() {
    }

    ;

    /**
     * 多线程下也能保持单例的ConcurrentHashMap
     *
     * @author y26467
     */
    private static class GlobalMappingInstance {
        private static final ConcurrentHashMap<String, MappingBean> SAFEMAPPING = new ConcurrentHashMap<>(100);
    }

    public static ConcurrentHashMap<String, MappingBean> getSafeMapping() {
        return GlobalMappingInstance.SAFEMAPPING;
    }
}
