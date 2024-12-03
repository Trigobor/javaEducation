package org.website.servlets;

import org.website.entity.User;
import org.website.DAO.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/login"})
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("errorMessage", "Username and/or password cannot be empty");
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
            return;
        }

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
                user = userDAO.getUserByLoginAndPassword(username, password);
                req.getSession().setAttribute("user", user);
                Cookie cookie = new Cookie("userId", user.getId().toString());
                cookie.setMaxAge(60 * 60 * 24);
                resp.addCookie(cookie);
            }
            if (user.getRole().equals("ADMIN")) {
                req.getRequestDispatcher("/pages/admin.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/pages/user.jsp").forward(req, resp);
            }
        }catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }
    }
}
