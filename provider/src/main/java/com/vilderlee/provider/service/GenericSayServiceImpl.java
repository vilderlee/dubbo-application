package com.vilderlee.provider.service;

import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * 功能描述:
 *
 * @package com.vilderlee.provider.service
 * @auther vilderlee
 * @date 2019/11/23 5:05 下午
 */
public class GenericSayServiceImpl implements GenericService {

    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        if (method.equals("say")){
            return "GenericSayServiceImpl Hello !!" + args[0];
        }

        return null;
    }
}
