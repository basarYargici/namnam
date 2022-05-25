/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Visitor;

/**
 *
 * @author fthsl
 */

@WebFilter("/*") 
public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String url = req.getRequestURI();
        Visitor u = (Visitor) req.getSession().getAttribute("valid_user");
        if (u == null){
            if(url.contains("recipe")){
                res.sendRedirect(req.getContextPath()+"/login.xhtml");
            }else{
            chain.doFilter(request, response);
            }
        }else{
            if(url.contains("login") || url.contains("signup")){
                res.sendRedirect(req.getContextPath()+"/recipe.xhtml");
            }else if(url.contains("recipebooks")){
                req.getSession().invalidate();
                res.sendRedirect(req.getContextPath()+"/index.xhtml");
            }else{
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
