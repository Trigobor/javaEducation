package org.website.servlets;

import org.website.entity.User;
import org.website.DAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("errorMessage", "Username and/or password cannot be empty");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
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
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
