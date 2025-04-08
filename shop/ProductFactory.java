package com.shop.models;

import com.shop.interfaces.IProduct;

public class ProductFactory {
    public static IProduct createProduct(String type, String name, double price) {
        return switch (type.toLowerCase()) {
            case "electronic" -> new ElectronicProduct(name, price);
            case "clothing" -> new ClothingProduct(name, price);
            default -> throw new IllegalArgumentException("Unknown product type");
        };
    }
}
