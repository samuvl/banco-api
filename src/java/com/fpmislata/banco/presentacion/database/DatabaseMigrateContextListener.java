package com.fpmislata.banco.presentacion.database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DatabaseMigrateContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciadooooo");
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.autowireBean(this);
        

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       System.out.println("Destruidoooooo");
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.autowireBean(this);
        

    }
}
