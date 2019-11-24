package com.vilderlee.provider.controller;

import com.vilderlee.provider.service.GenericSayServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 功能描述:
 *
 * @package com.vilderlee.provider.controller
 * @auther vilderlee
 * @date 2019/11/23 9:34 下午
 */
@Controller
public class DubboExportController {

    @Autowired
    private ApplicationConfig config;



    public void export(){
        String interfaceName = "";
        String registryAddress = "";
        GenericService genericService = new GenericSayServiceImpl();
        ServiceConfig<GenericService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(config);
        serviceConfig.setRegistry(generateRegistry(registryAddress));
        serviceConfig.setInterface(interfaceName);
        serviceConfig.setGeneric("true");
        serviceConfig.setRef(genericService);

    }

    private RegistryConfig generateRegistry(String registryAddress) {
        RegistryConfig config = new RegistryConfig();
        config.setAddress(registryAddress);
        config.setCheck(true);
        return config;
    }
}
