package com.vilderlee.consumer.service;

import com.vilderlee.api.SayHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 分组
 *
 * @package com.vilderlee.consumer.service
 * @auther vilderlee
 * @date 2019/11/23 4:38 下午
 */
@Component
public class GroupInvoker {

    @Reference(group = "hello")
    private SayHelloService sayHelloService;
    @Reference(group = "hello", version = "2.0.0" , mock = "com.vilderlee.consumer.mock.SayHelloServiceMock",timeout = 3000)
    private SayHelloService sayHelloService2;
    @Reference(group = "bye")
    private SayHelloService sayByeService;

    public void sayHelloService(String words) {
        String say = sayHelloService.say(words);
        System.out.println("sayHelloService" + say);
    }

    public void sayHelloService2(String words) {
        String say = sayHelloService2.say(words);
        System.out.println("sayHelloService2" + say);
    }

    public void sayByeService(String words) {
        String say = sayByeService.say(words);
        System.out.println("sayByeService" + say);

    }
}
