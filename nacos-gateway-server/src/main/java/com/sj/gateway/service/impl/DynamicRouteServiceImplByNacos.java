package com.sj.gateway.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.utils.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sj.gateway.config.GatewayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-07 16:52:04
 */
@Component
@Slf4j
@DependsOn({"gatewayConfig"})
public class DynamicRouteServiceImplByNacos {

    private final DynamicRouteServiceImpl dynamicRouteService;

    public DynamicRouteServiceImplByNacos(DynamicRouteServiceImpl dynamicRouteService) {
        this.dynamicRouteService = dynamicRouteService;
    }

    private ConfigService configService;

    @PostConstruct
    public void init() {
        log.info("gateway route init...");
        try {
            configService = initConfigService();
            if (configService == null) {
                log.warn("initConfigService fail");
                return;
            }
            String configInfo = configService.getConfig(GatewayConfig.NACOS_ROUTE_DATA_ID, GatewayConfig.NACOS_ROUTE_GROUP, GatewayConfig.DEFAULT_TIMEOUT);
            log.info("获取网关当前配置:\r\n{}", configInfo);
            List<RouteDefinition> definitionList = transYamlConfigData(configInfo);
            for (RouteDefinition definition : definitionList) {
                log.info("update route : {}", definition.toString());
                dynamicRouteService.add(definition);
            }
        } catch (Exception e) {
            log.error("初始化网关路由时发生错误", e);
        }
        dynamicRouteByNacosListener(GatewayConfig.NACOS_ROUTE_DATA_ID, GatewayConfig.NACOS_ROUTE_GROUP);
    }

    /**
     * 监听Nacos下发的动态路由配置
     *
     * @param dataId
     * @param group
     */
    public void dynamicRouteByNacosListener(String dataId, String group) {
        try {
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("进行网关更新:\n\r{}", configInfo);
                    List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);
                    log.info("update route : {}", definitionList.toString());
                    dynamicRouteService.updateList(definitionList);
                }

                @Override
                public Executor getExecutor() {
                    log.info("getExecutor\n\r");
                    return null;
                }
            });
        } catch (NacosException e) {
            log.error("从nacos接收动态路由配置出错!!!", e);
        }
    }

    /**
     * 初始化网关路由 nacos config
     *
     * @return
     */
    private ConfigService initConfigService() {
        try {
            Properties properties = new Properties();
            properties.setProperty("serverAddr", GatewayConfig.NACOS_SERVER_ADDR);
            return configService = NacosFactory.createConfigService(properties);
        } catch (Exception e) {
            log.error("初始化网关路由时发生错误", e);
            return null;
        }
    }

    /**
     * 将yaml格式的路由集合转为路由实体
     *
     * @param yamlConfigInfo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("all")
    private List<RouteDefinition> transYamlConfigData(String yamlConfigInfo) throws Exception {
        if (StringUtils.isEmpty(yamlConfigInfo)) {
            return new ArrayList<>();
        }
        Yaml yaml = new Yaml();
        List<LinkedHashMap<String, Object>> yamlDataList = Optional.ofNullable((List<LinkedHashMap<String, Object>>) yaml.load(yamlConfigInfo))
                .orElse(new ArrayList<>());
        /*
         * 将过滤器中的数组列表值，平铺防止转换为路由实体时失败
         * */
        yamlDataList.stream()
                .filter(s -> s.containsKey("filters"))
                .flatMap((Function<LinkedHashMap<String, Object>, Stream<Object>>) map -> ((ArrayList) map.get("filters")).stream())
                .filter(s -> s instanceof LinkedHashMap).forEach(o -> {
            LinkedHashMap<String, Object> argsMap = (LinkedHashMap) ((LinkedHashMap) o).get("args");
            for (Map.Entry<String, Object> entry : argsMap.entrySet()) {
                if (entry.getValue() instanceof ArrayList) {
                    final int size = ((ArrayList) entry.getValue()).size();
                    for (int i = 0; i < size; i++) {
                        argsMap.put(entry.getKey() + "." + i, ((ArrayList) entry.getValue()).get(i));
                    }
                    argsMap.remove(entry.getKey());
                }
            }
        });
        JsonMapper mapper = new JsonMapper();
        return mapper.readValue(mapper.writeValueAsString(yamlDataList), new TypeReference<List<RouteDefinition>>() {
        });
    }
}
