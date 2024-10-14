package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/catalog/products")
public class ProductServlet extends HttpServlet {
    private static final List<Product> products = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        //String username = request.getParameter("username");
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product(name, price, username);
        products.add(product);

        response.getWriter().println("Product added successfully!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        for (Product product : products) {
            response.getWriter().println(product + "<br>");
        }
    }

    static List<Product> getProductsByUser(String username) {
        List<Product> userProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getAddedBy().equals(username)) {
                userProducts.add(product);
            }
        }
        return userProducts;
    }

    static List<Product> getAllProducts() {
        return products;
    }
}
