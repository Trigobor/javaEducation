package org.website.filters;

import jakarta.servlet.http.HttpSession;
import org.website.DAO.UserDAO;
import org.website.entity.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/user/*")
public class UserAccessFilter implements Filter {
    private UserDAO userDAO = new UserDAO();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        String loginURI = req.getContextPath() + "/login";

        try {
            User user = null;
            Cookie[] cookies = req.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userId")) {
                        String userId = cookie.getValue();
                        user = userDAO.getUserById(Integer.parseInt(userId));
                        if (user != null) {
                            chain.doFilter(req, resp);
                        }
                    }
                }
            }

            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);

        }catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
