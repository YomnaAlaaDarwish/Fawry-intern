package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Set<String> validUsers = new HashSet<>();

    static {
        validUsers.add("user1");
        validUsers.add("user2");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        if (validUsers.contains(username)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.getWriter().println("Login successful!");
        } else {
            response.getWriter().println("Invalid user!");
        }
    }
}
