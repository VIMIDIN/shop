package com.shop;

import com.shop.facade.OrderProcessingFacade;
import com.shop.models.Order;
import com.shop.models.ProductFactory;
import com.shop.payment.CreditCardPayment;

public class Main {
    public static void main(String[] args) {
        // Создаём заказ и передаём его в фасад
        OrderProcessingFacade orderFacade = new OrderProcessingFacade(new Order() {
            @Override
            public <IPaymentStrategy> void setPaymentStrategy(IPaymentStrategy iPaymentStrategy) {
            }
        });

        // Добавляем товары
        orderFacade.addProductToOrder(ProductFactory.createProduct("electronic", "Laptop", 1200));
        orderFacade.addProductToOrder(ProductFactory.createProduct("clothing", "T-Shirt", 25));

        // Устанавливаем способ оплаты
        orderFacade.setPaymentMethod(new CreditCardPayment("1234-5678-9876"));

        // Завершаем заказ
        orderFacade.completeOrder();
    }
}
