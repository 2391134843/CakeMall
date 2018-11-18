package com.hnist.Cake.web;

import javax.servlet.*;
import java.io.IOException;


/**
 * 字符集的过滤器
 */
public class CharsetEncodingFilter implements Filter{
    private String encoding;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }


    public void destroy() {

    }
}
