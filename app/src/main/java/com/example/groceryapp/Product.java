package com.example.groceryapp;

public class Product {
    String product_name;
    String brand;
    String product_Id;
    String price;

    public Product(String product_Id, String product_name, String pirce, String brand){
        this.brand = brand;
        this.product_Id = product_Id;
        this.price = price;
        this.product_name = product_name;
    }
}
