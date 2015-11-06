package com.fpmislata.banco.presentacion.security.impl;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterImplSecurity implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      /*  String principal = "Permisos";
        if (principal.equals("Permisos")) {
            System.out.println("TRUE");
            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            System.out.println("FALSE");
        }*/

    }

    @Override
    public void destroy() {
    }

}
