package com.fpmislata.banco.presentacion.controladores;

import com.fpmislata.banco.business.domain.EntidadBancaria;
import com.fpmislata.banco.business.service.EntidadBancariaService;
import com.fpmislata.banco.persistence.BussinessException;
import com.fpmislata.banco.persistence.BussinessMessage;
import com.fpmislata.banco.presentacion.json.JsonTransformer;
import java.io.IOException;
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
 * @author Samuel Lao
 */
@Controller
public class EntidadBancariaController {

    @Autowired
    EntidadBancariaService entidadBancariaService;

    @Autowired
    JsonTransformer jsonTransformer;


    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @RequestBody requestbody
     * @param idEntidadBancaria
     */
    @RequestMapping(value = {"/entidadbancaria/{idEntidadBancaria}"}, method = RequestMethod.GET, produces = "application/json")
    public void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idEntidadBancaria") int idEntidadBancaria) {
        try {
            EntidadBancaria entidadBancaria = entidadBancariaService.get(idEntidadBancaria);
            String jsonSalida = jsonTransformer.objectToJson(entidadBancaria);
            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/entidadbancaria", method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            List<EntidadBancaria> entidadesBancarias;
            String name = httpServletRequest.getParameter("nombre");
            if (name == null) {
                entidadesBancarias = entidadBancariaService.findAll();
            } else {
                entidadesBancarias = entidadBancariaService.findByNombre(name);
            }
            String jsonSalida = jsonTransformer.objectToJson(entidadesBancarias);
            httpServletResponse.getWriter().println(jsonSalida);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = {"/entidadbancaria"}, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            EntidadBancaria entidadBancaria = (EntidadBancaria) jsonTransformer.jsonToObject(jsonEntrada, EntidadBancaria.class);
            entidadBancariaService.insert(entidadBancaria);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.getWriter().println(jsonTransformer.objectToJson(entidadBancaria));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = {"/entidadbancaria/{idEntidadBancaria}"}, method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idEntidadBancaria") int idEntidadBancaria) {

        try {
            EntidadBancaria entidadBancaria = (EntidadBancaria) jsonTransformer.jsonToObject(jsonEntrada, EntidadBancaria.class);
            entidadBancariaService.update(entidadBancaria);
            String jsonSalida = jsonTransformer.objectToJson(entidadBancaria);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
        }
    }

    @RequestMapping(value = "/entidadbancaria/{idEntidadBancaria}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idEntidadBancaria") int idEntidadBancaria) {
        try {
            entidadBancariaService.delete(idEntidadBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(ex);
        }
    }
}
