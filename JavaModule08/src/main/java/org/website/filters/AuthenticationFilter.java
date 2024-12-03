package org.website.filters;

import org.website.DAO.UserDAO;
import org.website.entity.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private UserDAO userDAO = new UserDAO();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String loginURI = req.getContextPath() + "/login";

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

            if (user == null) {
                req.getRequestDispatcher("pages/index.jsp").forward(req, resp);
            } else {
                chain.doFilter(req, resp);
            }

        }catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }

    }

    @Override
    public void destroy() {
    }
}
