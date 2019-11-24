package com.vilderlee.consumer.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述:
 *
 * @author vilderlee
 * @package com.vilderlee.consumer.controller
 * @auther vilderlee
 * @date 2019/11/23 10:03 下午
 */
@Data
public class InvokeRequest implements Serializable {
    
    String interfaceName ;
    String methodInvokeName ;
    String requestEntityName ;
    String requestJson ;
    String registryAddress ;


}
