
<%@page import="com.fpmislata.banco.presentacion.json.jsonImpl.JsonTransformerImplJackson"%>
<%@page import="com.fpmislata.banco.presentacion.JsonTransformer"%>
<%@page import="com.fpmislata.banco.business.domain.EntidadBancaria"%>
<%@page import="com.fpmislata.banco.business.service.impl.EntidadBancariaServiceImpl"%>
<%@page import="com.fpmislata.banco.business.service.EntidadBancariaService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    EntidadBancariaService entidadBancariaService = new EntidadBancariaServiceImpl();

    int idEntidadBancaria = Integer.parseInt(request.getParameter("idEntidadBancaria"));
    EntidadBancaria entidadBancaria = entidadBancariaService.get(idEntidadBancaria);

    JsonTransformer jsonTransformer = new JsonTransformerImplJackson();
    String jsonEntidadesBancarias = jsonTransformer.objectToJson(entidadBancaria);

    out.println(jsonEntidadesBancarias);
%>