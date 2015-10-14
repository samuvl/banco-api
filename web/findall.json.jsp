<%@page import="com.fpmislata.banco.presentacion.json.jsonImpl.JsonTransformerImplJackson"%>
<%@page import="com.fpmislata.banco.presentacion.JsonTransformer"%>
<%@page import="com.fpmislata.banco.presentacion.JsonTransformer"%>
<%@page import="com.fpmislata.banco.business.domain.EntidadBancaria"%>
<%@page import="com.fpmislata.banco.business.service.impl.EntidadBancariaServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="com.fpmislata.banco.business.service.EntidadBancariaService"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>


<%
    EntidadBancariaService entidadBancariaService = new EntidadBancariaServiceImpl();
    List<EntidadBancaria> entidadesBancarias = entidadBancariaService.findAll();


    JsonTransformer jsonTransformer = new JsonTransformerImplJackson();
    String jsonEntidadesBancarias = jsonTransformer.objectToJson(entidadesBancarias);

    out.println(jsonEntidadesBancarias);
%>