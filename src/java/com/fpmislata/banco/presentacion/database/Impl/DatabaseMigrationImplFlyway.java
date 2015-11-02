package com.fpmislata.banco.presentacion.database.Impl;

import com.fpmislata.banco.presentacion.database.DatabaseMigration;
import org.flywaydb.core.Flyway;

public class DatabaseMigrationImplFlyway implements DatabaseMigration {

    @Override
    public void migrate(String datasourceName, String packageName) {

        Flyway flyway = new Flyway();
        
        flyway.setDataSource(dataSource);
        flyway.setLocations(packageName);
        flyway.setEncoding("utf-8");
        flyway.migrate();
    }

}
