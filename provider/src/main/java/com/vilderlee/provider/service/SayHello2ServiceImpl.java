package com.vilderlee.provider.service;

import com.vilderlee.api.SayHelloService;
import org.apache.dubbo.config.annotation.Service;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @package com.vilderlee.provider.service
 * @auther vilderlee
 * @date 2019/11/23 11:49 上午
 */
@Service(group = "hello", version = "2.0.0")
public class SayHello2ServiceImpl implements SayHelloService {
    @Override
    public String say(String words) {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello 2" + words;
    }
}
