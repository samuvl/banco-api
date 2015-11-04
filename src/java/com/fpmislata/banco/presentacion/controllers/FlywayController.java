
package com.fpmislata.banco.presentacion.controllers;

import com.fpmislata.banco.persistence.migration.DatabaseMigration;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public class FlywayController {
    
    @Autowired
    DatabaseMigration databaseMigration;


    @RequestMapping(value = {"/migrate"}, method = RequestMethod.POST)
    public void write(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable int id) throws IOException {
        databaseMigration.migrate("jdbc:mysql://localhost:3306/banco", "packageName");
    }
}
