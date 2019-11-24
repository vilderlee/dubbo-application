package com.vilderlee.provider.service;

import com.vilderlee.api.SayHelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * 功能描述:
 *
 * @package com.vilderlee.provider.service
 * @auther vilderlee
 * @date 2019/11/23 11:49 上午
 */
@Service(group = "bye")
public class SayByeServiceImpl implements SayHelloService {
    @Override
    public String say(String words) {
        return "Hello " + words;
    }
}
