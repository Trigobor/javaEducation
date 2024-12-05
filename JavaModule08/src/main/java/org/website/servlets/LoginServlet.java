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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Username and/or password cannot be empty");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            User user = null;
            Cookie[] cookies = request.getCookies();

            // не очень классно, что у тебя каждый раз, когда юзер нажимает
            // залогиниться, он автоматически разлогинивается, если будет время - исправь

            // у тебя похожая логика в AuthenticationServlet,
            // попробуй еще раз вынести код в класс-утилиту
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userId")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }

            user = userDAO.getUserByLoginAndPassword(username, password);
            request.getSession().setAttribute("user", user);
            Cookie cookie = new Cookie("userId", user.getId().toString());
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);

            // не очень классно, что у тебя в сервлете по авторизации происходит
            // логика по выбору следующей страницы, будет время - исправь
            if (user.getRole().getRoleName().equals("ADMIN")) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                response.sendRedirect(request.getContextPath() + "/user");
            }
        } catch (Exception e) {
            // если будет время, сделай так, чтобы не просто перебрасывало
            // на страницу логина, а еще бы и ошибка там отображалась
            request.setAttribute("errorMessage", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
