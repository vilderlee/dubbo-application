package com.vilderlee.provider.service;

import com.vilderlee.api.Request;
import com.vilderlee.api.Response;
import com.vilderlee.api.UserService;
import org.apache.dubbo.config.annotation.Service;

/**
 * 功能描述:
 *
 * @package com.vilderlee.provider.service
 * @auther vilderlee
 * @date 2019/11/23 10:58 下午
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Response getUser(Request request) {
        String userName = "Name:" + request.getId();
        Response response = new Response();
        response.setResponse(userName);
        return response;
    }
}
