package com.shop.observer;

import com.shop.interfaces.IOrderObserver;

public class EmailNotification implements IOrderObserver {
    @Override
    public void update(String message) {
        System.out.println("Email Notification: " + message);
    }
}
