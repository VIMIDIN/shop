package com.shop.models;

public class ElectronicProduct extends Product {
    public ElectronicProduct(String name, double price) {
        super(name, price);
    }

    @Override
    public String getType() {
        return "Electronic";
    }
}
