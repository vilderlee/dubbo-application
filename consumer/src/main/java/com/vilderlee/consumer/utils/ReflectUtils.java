package com.vilderlee.consumer.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

import static com.vilderlee.consumer.utils.StringUtils.transferSetMethod;

/**
 * 功能描述:
 *
 * @package com.vilderlee.consumer.utils
 * @auther vilderlee
 * @date 2019/11/23 10:14 下午
 */
public final class ReflectUtils {

    private ReflectUtils() {
    }

    public static void processNullForEntity(Class<?> clz, Object object) {
        Field[] fields = clz.getDeclaredFields();
        Stream.of(fields).forEach(field -> {
            try {
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    if (String.class == type) {
                        String name = field.getName();
                        name = transferSetMethod(name);

                        Method method = clz.getDeclaredMethod(name, type);
                        method.invoke(object, "");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
