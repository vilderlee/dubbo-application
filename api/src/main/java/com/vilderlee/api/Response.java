package com.vilderlee.api;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述:
 *
 * @package com.vilderlee.api
 * @auther vilderlee
 * @date 2019/11/23 10:51 下午
 */
@Data
public class Response implements Serializable {

    private static final long serialVersionUID = 715425594808344711L;

    private String response;
}
