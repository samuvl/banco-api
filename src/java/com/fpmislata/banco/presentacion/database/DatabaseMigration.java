package com.fpmislata.banco.presentacion.database;

public interface DatabaseMigration {
    void migrate(String datasourceName, String packageName);
}
