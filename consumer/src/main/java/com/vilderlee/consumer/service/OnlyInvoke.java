package com.vilderlee.consumer.service;

import com.vilderlee.api.SayHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 直连
 *
 * @package com.vilderlee.consumer.service
 * @auther vilderlee
 * @date 2019/11/23 11:59 上午
 */
@Component
public class OnlyInvoke {

    @Reference(url = "dubbo://localhost:20880", group = "hello", mock = "true", timeout = 2000)
    private SayHelloService sayHelloService;

    public void test(String words) {
        String result = sayHelloService.say(words);
        System.out.println(result);
    }
}
