package com.vilderlee.provider.web;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * 功能描述:
 *
 * @package com.vilderlee.provider.web
 * @auther vilderlee
 * @date 2019/11/23 7:49 下午
 */
public class Listener extends ContextLoaderListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
    }
}
