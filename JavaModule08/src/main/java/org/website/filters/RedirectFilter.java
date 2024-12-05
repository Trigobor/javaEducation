package org.website.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebFilter("/*")
public class RedirectFilter implements Filter {
    private Map<String, String> redirectMap;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        redirectMap = new HashMap<>();
        redirectMap.put("/JavaModule08_war_exploded/pages/index.jsp", "/index");
        redirectMap.put("/JavaModule08_war_exploded/pages/adminBlock.jsp", "/adminBlock");
        redirectMap.put("/JavaModule08_war_exploded/pages/admin.jsp", "/admin");
        redirectMap.put("/JavaModule08_war_exploded/pages/login.jsp", "/login");
        redirectMap.put("/JavaModule08_war_exploded/pages/user.jsp", "/user");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        if (redirectMap.containsKey(requestURI)) {
            String redirectTo = redirectMap.get(requestURI);
            httpResponse.sendRedirect(httpRequest.getContextPath() + redirectTo);
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
    }
}
