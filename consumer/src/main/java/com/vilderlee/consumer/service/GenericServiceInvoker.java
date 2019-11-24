package com.vilderlee.consumer.service;

import com.vilderlee.api.SayHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * 功能描述:
 *
 * @package com.vilderlee.consumer.service
 * @auther vilderlee
 * @date 2019/11/23 5:15 下午
 */
@Component
public class GenericServiceInvoker {

    @Reference(group = "generic")
    private SayHelloService sayHelloService;

    public void genericServiceInvoke(String words){
        String say = sayHelloService.say(words);
        System.out.println(say);
    }
}
