package com.vilderlee.provider.service;

import com.vilderlee.api.SayHelloService;
import org.springframework.stereotype.Component;

/**
 * 功能描述:
 *
 * @package com.vilderlee.provider.service
 * @auther vilderlee
 * @date 2019/11/23 11:49 上午
 */
//@Component
public class SayServiceImplMock implements SayHelloService {

    @Override
    public String say(String words) {
        return "Error " + words;
    }
}
