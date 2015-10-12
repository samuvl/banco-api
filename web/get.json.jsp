<%-- 
    Document   : get.json
    Created on : 01-oct-2015, 13:23:49
    Author     : alumno
--%>

<%@page import="com.fpmislata.banco.business.service.impl.EntidadBancariaServiceImpl"%>
<%@page import="com.fpmislata.banco.business.service.EntidadBancariaService"%>
<%@page import="com.fpmislata.banco.business.domain.EntidadBancaria"%>
<%@page import="java.util.List"%>
<%@page import="com.fpmislata.banco.persistence.dao.impl.jdbc.EntidadBancariaDAOImplJDBC"%>
<%@page import="com.fpmislata.banco.persistence.dao.EntidadBancariaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    EntidadBancariaService entidadBancariaService = new EntidadBancariaServiceImpl();
    
    int idEntidadBancaria = Integer.parseInt(request.getParameter("idEntidadBancaria"));
    EntidadBancaria entidadBancaria = entidadBancariaService.get(idEntidadBancaria);
%>

{
"idEntidadBancaria":<%=entidadBancaria.getIdEntidadBancaria()%>,
"codigoEntidad": <%=entidadBancaria.getCodigoEntidad()%>, 
"nombre": "<%=entidadBancaria.getNombre()%>",
"direccion": "<%=entidadBancaria.getDireccion()%>",
"fechaCreacion": "<%=entidadBancaria.getFechaCreacion()%>",
"cif": "<%=entidadBancaria.getCif()%>"
}

