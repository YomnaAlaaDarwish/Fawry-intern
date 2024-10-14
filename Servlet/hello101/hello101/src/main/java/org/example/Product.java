package org.example;

public class Product {
    private String name;
    private double price;
    private String addedBy;

    public Product(String name, double price, String addedBy) {
        this.name = name;
        this.price = price;
        this.addedBy = addedBy;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getAddedBy() {
        return addedBy;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", addedBy='" + addedBy + "'}";
    }
}
