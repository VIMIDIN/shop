package com.shop.observer;

import com.shop.interfaces.IOrderObserver;

public class SMSNotification implements IOrderObserver {
    @Override
    public void update(String message) {
        System.out.println("SMS Notification: " + message);
    }
}
