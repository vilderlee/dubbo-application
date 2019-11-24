package com.vilderlee.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.vilderlee.consumer.entity.InvokeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

import static com.vilderlee.consumer.utils.ReflectUtils.processNullForEntity;

/**
 * 功能描述:
 *
 * @package com.vilderlee.provider.controller
 * @auther vilderlee
 * @date 2019/11/23 7:52 下午
 */
@Controller
public class LoadClassController {

    @Value("${file.jar.path}")
    private String filePath;
    @Value("${file.jar.path.prefix}")
    private String filePathPrefix;

    @GetMapping("/getHtml")
    public String getHtml() {
        return "request";
    }

    @PostMapping("/loadJarClass")
    public String loadJarClass(InvokeRequest request, HttpServletRequest httpServletRequest) throws Exception {

        File file = new File(filePath);
        String[] list = new String[0];
        if (file.isDirectory()) {
            list = file.list();
        }
        List<URL> urls = new ArrayList<>();
        Stream.of(list).forEach(s -> {
            try {
                urls.add(new URL(filePathPrefix + filePath + s));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        URL[] urls1 = new URL[list.length];
        urls.toArray(urls1);

        ClassLoader classLoader = new URLClassLoader(urls1);
        Class<?> clz = classLoader.loadClass(request.getRequestEntityName());
        Object object = clz.newInstance();
        processNullForEntity(clz, object);
        String requestJson = JSONObject.toJSONString(object);
        httpServletRequest.setAttribute("requestJson", requestJson);
        request.setRequestJson(requestJson);
        httpServletRequest.setAttribute("invokeRequest", request);

        return "show";
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePathPrefix() {
        return filePathPrefix;
    }

    public void setFilePathPrefix(String filePathPrefix) {
        this.filePathPrefix = filePathPrefix;
    }
}
