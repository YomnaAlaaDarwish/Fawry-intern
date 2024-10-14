package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/catalog/stats")
public class StatsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = ProductServlet.getAllProducts();

        int numberOfProducts = products.size();
        double totalPrice = products.stream().mapToDouble(Product::getPrice).sum();

        response.setContentType("text/html");
        response.getWriter().println("Number of products: " + numberOfProducts + "<br>");
        response.getWriter().println("Total price of all products: " + totalPrice + "<br>");
    }
}
