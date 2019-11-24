package com.vilderlee.consumer.mock;

import com.vilderlee.api.SayHelloService;

/**
 * 功能描述:
 *
 * @package com.vilderlee.consumer.mock
 * @auther vilderlee
 * @date 2019/11/24 12:07 上午
 */
public class SayHelloServiceMock implements SayHelloService {

    public SayHelloServiceMock() {
    }

    @Override
    public String say(String words) {
        return "======This is Mock!!======";
    }
}
