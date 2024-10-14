package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int count = 0;
        if (request.getServletContext().getAttribute("counter") != null) {
            count = (Integer) request.getServletContext().getAttribute("counter");
        }
        request.getServletContext().setAttribute("counter", count + 1);

        System.out.println("Requests count: " + count);


        HttpSession session = request.getSession();
        int sessionCounter = 0;
        if (session.getAttribute("counter") != null) {
            sessionCounter = (Integer) session.getAttribute("counter");
        }
        session.setAttribute("counter", sessionCounter + 1);
        System.out.println("Requests per session count: " + sessionCounter);


        String user = getCookieValue(request, "user")
                .orElseGet(() -> request.getParameter("user"));

        System.out.println("User name is " + user);

        response.getWriter().println("Hello ya <b>" + user + "</b> <br />");
        response.getWriter().println("Time is <b>" + new Date() + "</b>");

        if (user != null) {
            Cookie cookie = new Cookie("user", user);
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
        }
    }

    private Optional<String> getCookieValue(HttpServletRequest request, String cookieName) {
        System.out.println("Get cookie value for " + cookieName);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if(cookieName.equals(cookie.getName())) {
                    System.out.println("Get cookie value for " + cookieName + " found");
                    return Optional.of(cookie.getValue());
                }
            }
        }
        System.out.println("Get cookie value for " + cookieName + " not found");
        return Optional.empty();
    }
}
