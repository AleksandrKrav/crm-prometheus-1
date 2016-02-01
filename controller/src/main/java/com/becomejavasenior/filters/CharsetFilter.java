package com.becomejavasenior.filters;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("requestEncoding");

        if (encoding == null) encoding = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Respect the client-specified character encoding
        // (see HTTP specification section 3.4.1)
        if (servletRequest.getCharacterEncoding() == null)
            servletRequest.setCharacterEncoding(encoding);


        /**
         * Set the default response content type and encoding
         */
        servletResponse.setContentType("text/html; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");


        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }
}