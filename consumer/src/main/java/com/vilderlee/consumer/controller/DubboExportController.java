package com.vilderlee.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.vilderlee.consumer.entity.InvokeRequest;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:
 *
 * @package com.vilderlee.provider.controller
 * @auther vilderlee
 * @date 2019/11/23 9:34 下午
 */
@RestController
public class DubboExportController {

    @Autowired
    private ApplicationConfig applicationConfig;

    @PostMapping("/invokeRequest")
    public String invoke(InvokeRequest invokeRequest) throws Exception {
        Class<?> requestClass = Class.forName(invokeRequest.getRequestEntityName());

        ReferenceConfig<GenericService> config = new ReferenceConfig<>();
        config.setGeneric(true);
        config.setInterface(invokeRequest.getInterfaceName());
        config.setApplication(applicationConfig);
        config.setRegistry(generateRegistry(invokeRequest.getRegistryAddress()));
        GenericService genericService = config.get();

        Object request = JSONObject.parseObject(invokeRequest.getRequestJson(), requestClass);
        Object response = genericService.$invoke(invokeRequest.getMethodInvokeName(), new String[]{invokeRequest.getRequestEntityName()}, new Object[]{request});

        return JSONObject.toJSONString(response);
    }

    private RegistryConfig generateRegistry(String registryAddress) {
        RegistryConfig config = new RegistryConfig();
        config.setAddress(registryAddress);
        config.setCheck(true);
        return config;
    }
}
