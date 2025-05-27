package org.website.servlets;

import jakarta.servlet.http.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

import static org.website.utils.URLConstants.LOGIN_URL;

@WebServlet({"/", "/index"})
public class IndexServlet extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        //убери это
        if (session != null) {
            session.invalidate();
        }

        request.getRequestDispatcher(request.getContextPath() + LOGIN_URL).forward(request, response);
    }
}
