package com.fpmislata.banco.presentacion.controllers;

import com.fpmislata.banco.business.domain.Rol;
import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.persistence.security.PasswordManager;
import com.fpmislata.banco.presentacion.security.WebSession;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Samuel Lao
 */
@Controller
public class LoginController {

    @Autowired
    PasswordManager passwordManager;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            HttpSession httpSession = httpServletRequest.getSession();
            
            Usuario usuario = new Usuario("Juan", passwordManager.encrypt("password"));
            usuario.setRol(Rol.Administrador);
            
            WebSession webSession = new WebSession(usuario, new Date());
            httpSession.setAttribute("webSession", webSession);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
