package com.shop.models;

public class ClothingProduct extends Product {
    public ClothingProduct(String name, double price) {
        super(name, price);
    }

    @Override
    public String getType() {
        return "Clothing";
    }
}
