package com.vilderlee.consumer.utils;

/**
 * 功能描述:
 *
 * @package com.vilderlee.consumer.utils
 * @auther vilderlee
 * @date 2019/11/23 10:14 下午
 */
public final class StringUtils {
    private StringUtils() {
    }

    public static String transferSetMethod(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
