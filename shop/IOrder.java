package com.shop.interfaces;

public interface IOrder {
    void addProduct(IProduct product);
    <IPaymentStrategy> void setPaymentStrategy(IPaymentStrategy strategy);

    void setPaymentStrategy(IPaymentStrategy paymentStrategy);

    void processOrder();
}
