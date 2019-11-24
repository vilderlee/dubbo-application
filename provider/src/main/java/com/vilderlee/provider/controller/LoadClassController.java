package com.vilderlee.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.vilderlee.api.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 功能描述:
 *
 * @package com.vilderlee.provider.controller
 * @auther vilderlee
 * @date 2019/11/23 7:52 下午
 */
@Controller
public class LoadClassController {


    @GetMapping("/getHtml")
    public String getHtml() {
        return "request";
    }

    @PostMapping("/loadJarClass")
    public String loadJarClass(String className, HttpServletRequest request) throws Exception {
        File file = new File("/Users/vilderlee/jar");
        String[] list = new String[0];
        if (file.isDirectory()) {
            list = file.list();
        }
        List<URL> urls = new ArrayList<>();
        Stream.of(list).forEach(s -> {
            try {
                urls.add(new URL("file:/Users/vilderlee/jar/" + s));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        URL[] urls1 = new URL[list.length];
        urls.toArray(urls1);

        ClassLoader classLoader = new URLClassLoader(urls1);
        Class<?> clz = classLoader.loadClass(className);
        Object object = clz.newInstance();
        Field[] fields = clz.getDeclaredFields();
        Stream.of(fields).forEach(field -> {
            try {
                field.setAccessible(true);
                Class<?> type = field.getType();
                if (String.class == type) {
                    String name = field.getName();
                    name = transferSetMethod(name);

                    Method method = clz.getDeclaredMethod(name, type);
                    method.invoke(object, "");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        Object o = JSONObject.toJSONString(object);
        request.setAttribute("json", o);
        return "show";
    }

    private String transferSetMethod(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }


}
