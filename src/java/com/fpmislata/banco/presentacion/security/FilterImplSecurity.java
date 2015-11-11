package com.fpmislata.banco.presentacion.security;

import com.fpmislata.banco.business.domain.Rol;
import com.fpmislata.banco.persistence.security.Authorization;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class FilterImplSecurity implements Filter {

    @Autowired
    WebSessionProvider webSessionProvider;

    @Autowired
    Authorization authorization;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        
                WebSession webSession = webSessionProvider.getWebSession(httpServletRequest, httpServletResponse);

        
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());
        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.autowireBean(this);
        
        
        if (authorization.isAuthorizedURL(webSession.getUser(), httpServletRequest.getRequestURI(), httpServletRequest.getMethod())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }

    }

    @Override
    public void destroy() {
    }

}
