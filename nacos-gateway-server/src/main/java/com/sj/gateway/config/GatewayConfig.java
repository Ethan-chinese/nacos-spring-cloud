package com.sj.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-07 16:35:51
 */
@Configuration
public class GatewayConfig {

    public static final long DEFAULT_TIMEOUT = 30000;

    public static String NACOS_SERVER_ADDR;

    public static String NACOS_ROUTE_DATA_ID;

    public static String NACOS_ROUTE_GROUP;

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    public void setNacosServerAddr(String nacosServerAddr){
        NACOS_SERVER_ADDR = nacosServerAddr;
    }

    @Value("${nacos.gateway.route.config.data-id}")
    public void setNacosRouteDataId(String nacosRouteDataId){
        NACOS_ROUTE_DATA_ID = nacosRouteDataId;
    }

    @Value("${nacos.gateway.route.config.group}")
    public void setNacosRouteGroup(String nacosRouteGroup){
        NACOS_ROUTE_GROUP = nacosRouteGroup;
    }
}
