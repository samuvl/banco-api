<%@page import="com.fpmislata.banco.business.service.impl.EntidadBancariaServiceImpl"%>
<%@page import="com.fpmislata.banco.business.service.EntidadBancariaService"%>
<%@page import="com.fpmislata.banco.business.service.EntidadBancariaService"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.fpmislata.banco.business.domain.EntidadBancaria"%>

<%
    EntidadBancariaService entidadBancariaService = new EntidadBancariaServiceImpl();
    List<EntidadBancaria> entidadesBancarias = entidadBancariaService.findAll();
%>
<% int contador=0; %>
[
<% for (EntidadBancaria entidadBancaria : entidadesBancarias) {%>

{   "idEntidadBancaria":<%=entidadBancaria.getIdEntidadBancaria()%>,
    "codigoEntidad": <%=entidadBancaria.getCodigoEntidad()%>, 
    "nombre": "<%=entidadBancaria.getNombre()%>",
    "direccion": "<%=entidadBancaria.getDireccion()%>",
    "fechaCreacion": "<%=entidadBancaria.getFechaCreacion()%>",
    "cif": "<%=entidadBancaria.getCif()%>"
<%if ( contador == entidadesBancarias.size()-1 ){%>
}
<% } else{ %>
},
<%}%>
<% contador++;%>
<%}%>
]