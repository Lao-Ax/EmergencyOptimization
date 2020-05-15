package com.UdSU;

/**
 * Класс представляющий мероприятия и средства, которые доступны компании. Описывается названием, вкладом в решение,
 * ценой и признаком, является ли средство инвариантным для решения.
 */
public class Мероприятие {
    private String name;
    private double value;
    private double price;
    private boolean isConstant = false; // по умолчанию мероприятия не являются контстантными.

    public Мероприятие(String name, double value, double price, boolean isConstant) {
        this.name = name;
        this.value = value;
        this.price = price;
        this.isConstant = isConstant;
    }

    public Мероприятие(String name, double value, double price) {
        this.name = name;
        this.value = value;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isConstant() {
        return isConstant;
    }

    public void setConstant(boolean isConstant) {
        this.isConstant = isConstant;
    }
}
