package com.fpmislata.banco.presentacion.database;

import com.fpmislata.banco.persistence.migration.DatabaseMigration;
import javax.activation.DataSource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DatabaseMigrateContextListener implements ServletContextListener {

    @Autowired
    DatabaseMigration databaseMigration;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciadooooo");
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.autowireBean(this);

       databaseMigration.migrate();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       System.out.println("Destruidoooooo");
    }
}
