package org.website.filters;

import jakarta.servlet.http.Cookie;
import org.website.DAO.UserDAO;
import org.website.entity.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private UserDAO userDAO = new UserDAO();

    // я в курсе, что такое конфигурация
    private static final String[] ADMIN_URLS = {"/JavaModule08_war_exploded/admin"};
    private static final String ADMIN_BLOCK_URL = "/adminBlock";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        try {
            User user = null;
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userId")) {
                        String userId = cookie.getValue();
                        user = userDAO.getUserById(Integer.parseInt(userId));
                        req.setAttribute("user", user);
                    }
                }
            }

            boolean isAdminUrl = false;
            for (String adminUrl : ADMIN_URLS) {
                if (adminUrl.equals(uri)) {
                    isAdminUrl = true;
                    break;
                }
            }

            if ((user == null || user.getRole().getRoleName().equals("USER")) && isAdminUrl) {
                resp.sendRedirect(req.getContextPath() + ADMIN_BLOCK_URL);
            } else {
                chain.doFilter(request, response);
            }

        }catch (Exception e) {
            // если будет время, сделай так, чтобы не просто перебрасывало
            // на страницу логина, а еще бы и ошибка там отображалась
            req.setAttribute("errorMessage", e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/login");
        }

    }

    @Override
    public void destroy() {
    }
}
