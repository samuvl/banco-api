package com.fpmislata.banco.presentacion.controladores;

import com.fpmislata.banco.business.domain.EntidadBancaria;
import com.fpmislata.banco.business.service.EntidadBancariaService;
import com.fpmislata.banco.presentacion.json.JsonTransformer;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
