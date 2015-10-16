package com.fpmislata.banco.presentacion.controladores;

import com.fpmislata.banco.business.domain.EntidadBancaria;
import com.fpmislata.banco.business.service.EntidadBancariaService;
import com.fpmislata.banco.persistence.BussinessException;
import com.fpmislata.banco.persistence.BussinessMessage;
import com.fpmislata.banco.presentacion.json.JsonTransformer;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @RequestMapping(value = {"/entidadbancaria"})
    public void prueba(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) throws IOException {

        String jsonEntidadBancaria = jsonTransformer.objectToJson(entidadBancariaService.get(3));

        httpServletResponse.getWriter().println(jsonEntidadBancaria);

    }

    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @RequestBody requestbody
     * @param idEntidadBancaria
     */
    @RequestMapping(value = "/entidadbancaria/{idEntidadBancaria}", method = RequestMethod.GET, produces = "application/json")
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
    public void findall(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        try {
            List<EntidadBancaria> entidadesBancarias = entidadBancariaService.findAll();
            String jsonSalida = jsonTransformer.objectToJson(entidadesBancarias);
            httpServletResponse.getWriter().println(jsonSalida);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param jsonEntrada
     */
    @RequestMapping(value = "/entidadbancaria", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            /*jsonEntrada = "{'idEntidadBancaria':15,'nombre':'HUALA'','codigoEntidad':693569,'fechaCreacion':'2013-09-25','direccion':'EN SU KELLY wweeCOLEGA','cif':'00024000'}";*/
            EntidadBancaria entidadBancaria = (EntidadBancaria) jsonTransformer.jsonToObject(jsonEntrada, EntidadBancaria.class);
            entidadBancariaService.insert(entidadBancaria);
            String jsonSalida = jsonTransformer.objectToJson(entidadBancaria);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/entidadbancaria/{idEntidadBancaria}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idEntidadBancaria") int idEntidadBancaria) {
        try {
            entidadBancariaService.delete(idEntidadBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (BussinessException ex) {
            List<BussinessMessage> bussinessMessage = ex.getBussinessMessages();
            String jsonSalida = jsonTransformer.objectToJson(bussinessMessage);

            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonSalida);
            } catch (IOException ex1) {
                Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
    }
}
