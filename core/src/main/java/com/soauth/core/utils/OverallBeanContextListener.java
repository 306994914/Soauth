package com.soauth.core.utils;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

/**
 *
 * @author zhoujie
 * @date 2017/11/8
 *
 * web.xml   <listener-class>com.soauth.server.utils.OverallBeanContextListener</listener-class>
 *
 * ͨ����������ͨ���л�ȡSpringBean
 */
public class OverallBeanContextListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
        SpringBeanUtils.initialize(applicationContext);
    }
}
