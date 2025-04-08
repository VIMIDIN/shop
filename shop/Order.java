package com.shop.models;

import com.shop.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Order implements IOrder {
    private final List<IProduct> products = new ArrayList<>();
    private IPaymentStrategy paymentStrategy;
    private final List<IOrderObserver> observers = new ArrayList<>();

    public void addObserver(IOrderObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (IOrderObserver observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public void addProduct(IProduct product) {
        products.add(product);
    }

    @Override
    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public void processOrder() {
        double total = products.stream().mapToDouble(IProduct::getPrice).sum();
        notifyObservers("Order processed with total $" + total);
        if (paymentStrategy != null) {
            paymentStrategy.pay(total);
        } else {
            System.out.println("No payment method selected.");
        }
    }
}
