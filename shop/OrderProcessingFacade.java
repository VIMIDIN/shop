package com.shop.facade;

import com.shop.interfaces.IOrder;
import com.shop.interfaces.IPaymentStrategy;
import com.shop.interfaces.IProduct;

public class OrderProcessingFacade {
    private final IOrder order;

    public OrderProcessingFacade(IOrder order) {
        this.order = order;
    }

    public void addProductToOrder(IProduct product) {
        order.addProduct(product);
        System.out.println("✅ " + product.getName() + " added to order.");
    }

    public void setPaymentMethod(IPaymentStrategy paymentStrategy) {
        order.setPaymentStrategy(paymentStrategy);
        System.out.println("💳 Payment method set.");
    }

    public void completeOrder() {
        order.processOrder();
        System.out.println("🎉 Order completed successfully!");
    }
}
