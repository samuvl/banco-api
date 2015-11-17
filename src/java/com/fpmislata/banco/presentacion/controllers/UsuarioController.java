package com.fpmislata.banco.presentacion.controllers;

import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.business.service.UsuarioService;
import com.fpmislata.banco.presentacion.json.JsonTransformer;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author samu_
 */
@Controller
public class UsuarioController {
    @Autowired 
    UsuarioService usuarioService;
    
    @Autowired 
    JsonTransformer jsonTransformer;
    
    @RequestMapping(value = {"/usuario/{idUsuario}"}, method = RequestMethod.GET, produces = "application/json")
    public void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idUsuario") int idUsuario) {
        try {
            Usuario usuario = usuarioService.get(idUsuario);
            String jsonSalida = jsonTransformer.objectToJson(usuario);
            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @RequestMapping(value = {"/usuario"}, method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            List<Usuario> usuarios = usuarioService.findAll();
            String jsonSalida = jsonTransformer.objectToJson(usuarios);
            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @RequestMapping(value = {"/usuario"}, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            Usuario usuario = jsonTransformer.jsonToObject(jsonEntrada, Usuario.class);
            usuarioService.insert(usuario);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.getWriter().println(jsonTransformer.objectToJson(usuario));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @RequestMapping(value = {"/usuario/{idUsuario}"}, method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idUsuario") int idUsuario) {
        try {
            Usuario usuario = jsonTransformer.jsonToObject(jsonEntrada, Usuario.class);
            usuarioService.update(usuario);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonTransformer.objectToJson(usuario));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @RequestMapping(value = "/usuario/{idUsuario}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idUsuario") int idUsuario) {
        try {
            usuarioService.delete(idUsuario);
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(ex);
        }
    }
}
