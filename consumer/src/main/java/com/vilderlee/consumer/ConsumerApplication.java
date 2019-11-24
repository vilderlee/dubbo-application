package com.vilderlee.consumer;

import com.vilderlee.api.SayHelloService;
import com.vilderlee.consumer.service.GenericServiceInvoker;
import com.vilderlee.consumer.service.GroupInvoker;
import com.vilderlee.consumer.service.OnlyInvoke;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.vilderlee.consumer.service")
public class ConsumerApplication {

    public static void main(String[] args) {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(ConsumerApplication.class);
        ConfigurableApplicationContext run = builder.web(WebApplicationType.NONE).run(args);

        groupAndVersion(run);
    }


    /**
     * 直连模式无注册中心
     *
     * @param context
     */
    private static void onlyInvokeWithoutRegistry(ConfigurableApplicationContext context) {
        OnlyInvoke bean = context.getBean(OnlyInvoke.class);
        bean.test("vilderlee");
    }


    /**
     * 分组适用于接口的不同实现
     * 版本适用于灰度发布
     *
     * @param context
     */
    private static void groupAndVersion(ConfigurableApplicationContext context) {
        GroupInvoker groupInvoker = context.getBean(GroupInvoker.class);
        groupInvoker.sayHelloService("vilderlee");
        groupInvoker.sayHelloService2("vilderlee");

        groupInvoker.sayByeService("vilderlee");
    }


    /**
     * 泛化设计，可用于rest请求。
     * <p>
     * 泛化接口调用方式主要用于客户端没有 API 接口及模型类元的情况，
     * 参数及返回值中的所有 POJO 均用 Map 表示，通常用于框架集成，
     * 比如：实现一个通用的服务测试框架，可通过 GenericService 调用所有服务实现。
     */
    private static void genericReference() {
        ReferenceConfig<GenericService> config = new ReferenceConfig<>();
        config.setInterface(SayHelloService.class);
        config.setGeneric(true);
        config.setGroup("hello");
        GenericService genericService = config.get();
        Object say = genericService.$invoke("say", new String[]{String.class.getName()}, new Object[]{"VilderLee Generic"});
        System.out.println(say);
    }


    /**
     * 泛化服务，可以用做远程mock测试设计
     *
     * @param context
     */
    private static void genericServiceInvoke(ConfigurableApplicationContext context) {
        GenericServiceInvoker bean = context.getBean(GenericServiceInvoker.class);
        bean.genericServiceInvoke("oh my god !");
    }
}
