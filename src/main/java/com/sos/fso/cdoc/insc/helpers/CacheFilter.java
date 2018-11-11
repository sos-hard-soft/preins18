/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.helpers;

import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author master05
 */
public class CacheFilter implements Filter{

    FilterConfig cf;
    public void init(FilterConfig filterConfig) throws ServletException{
        cf = filterConfig;
    }
    public void doFilter (ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        
    }
    @Override
    public boolean isLoggable(LogRecord record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
