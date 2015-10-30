package com.fpmislata.banco.presentacion.database;

import com.fpmislata.banco.business.domain.EntidadBancaria;
import com.fpmislata.banco.presentacion.json.JsonTransformer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DatabaseMigrateContextListener implements ServletContextListener {

    @Autowired
    JsonTransformer jsonTransformer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciadooooo");
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.autowireBean(this);
        
        EntidadBancaria eb = new EntidadBancaria("hola", 111);
        System.out.println(jsonTransformer.objectToJson(eb));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Destruidoooooo");
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.autowireBean(this);
        
        EntidadBancaria eb = new EntidadBancaria("xaop", 111);
        System.out.println(jsonTransformer.objectToJson(eb));
    }
}
