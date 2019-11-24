package com.vilderlee.provider.config;



import com.vilderlee.api.SayHelloService;
import com.vilderlee.provider.service.GenericSayServiceImpl;
import org.apache.dubbo.config.*;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述:
 *
 * @package com.vilderlee.provider.config
 * @auther vilderlee
 * @date 2019/11/23 9:51 上午
 */
@Configuration
public class DubboConfig {

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig config = new ApplicationConfig();
        config.setName("Dubbo-Provider");
        config.setOwner("VilderLee");
        return config;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig config = new RegistryConfig();
        config.setAddress("zookeeper://localhost:2181");
        config.setCheck(true);
        return config;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig config = new ProtocolConfig();
        //默认为Dubbo（长连接、请求多、比较小的）
        config.setName("dubbo");
        config.setPort(20880);
        //dubbo的provider如何去分发事件：
        //  all(默认)：所有的事件都分发给线程池去处理，包括请求、响应、心跳、连接、断开
        //  direct:所有的消息都直接IO线程上处理，不发送线程池
        //  message:只有请求响应消息发到线程池上，其它连接断开事件，心跳等消息，直接在 IO 线程上执行
        //  execution:只有请求消息发送到线程池上处理，其他都在IO线程上处理。
        //  connection:IO线程上处理连接断开请求，将连接、断开请求放入队列中，其他消息发送给线程池处理
        config.setDispatcher("message");
        //线程池种类：基本上跟java线程池用法一致
        //  fixed（默认）：固定线程池，启动时创建，不关闭一直持有
        //  cache：缓存线程池，一分钟闲置后自动删除，需要时重建
        //  limited：可伸缩线程池，目前只会增长
        //  eager：优先创建Worker线程池。在任务数量大于corePoolSize但是小于maximumPoolSize时，
        //          优先创建Worker来处理任务。当任务数量大于maximumPoolSize时，将任务放入阻塞队列中。
        //          阻塞队列充满时抛出RejectedExecutionException。
        //          (相比于cached:cached在任务数量超过maximumPoolSize时直接抛出异常而不是将任务放入阻塞队列)
        config.setThreadpool("fixed");
        return config;
    }


    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig config = new MonitorConfig();
        //从注册中心发现监控中心地址
        config.setProtocol("registry");
        return config;
    }

    @Bean
    public void genericService(){
        ServiceConfig<GenericService> serviceConfig = new ServiceConfig<>();
        GenericService genericService = new GenericSayServiceImpl();
        serviceConfig.setApplication(applicationConfig());
        serviceConfig.setRegistry(registryConfig());
        serviceConfig.setRef(genericService);
        serviceConfig.setInterface(SayHelloService.class);
        serviceConfig.setGroup("generic");
        serviceConfig.export();
    }
}
