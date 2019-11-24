package com.vilderlee.provider;

import com.vilderlee.api.SayHelloService;
import com.vilderlee.provider.service.GenericSayServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.vilderlee.provider.service")
public class ProviderApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ProviderApplication.class, args);
//        genericService(context.getBean(ApplicationConfig.class),context.getBean(RegistryConfig.class),context.getBean(GenericSayServiceImpl.class));
    }


    public  static void genericService(ApplicationConfig applicationConfig, RegistryConfig registryConfig,GenericService genericService){
        ServiceConfig<GenericService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRef(genericService);
        serviceConfig.setInterface(SayHelloService.class);
        serviceConfig.setGroup("generic");
        serviceConfig.export();
    }
}
