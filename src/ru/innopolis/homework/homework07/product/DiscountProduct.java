package ru.innopolis.homework.homework07.product;

import java.time.LocalDate;

public class DiscountProduct extends Product {
    private double discount;
    private boolean applyDiscount;

    public DiscountProduct(String name, double price, double discount, boolean applyDiscount) {
        super(name, price);
        this.discount = discount;
        this.applyDiscount = applyDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public boolean getApplyDiscount() {
        return applyDiscount;
    }

    public void setApplyDiscount(boolean applyDiscount) {
        this.applyDiscount = applyDiscount;
    }

    @Override
    public double getPrice() {
        if (applyDiscount) {
            return super.getPrice() * (1 - discount / 100);
        } else {
            return super.getPrice();
        }
    }

    @Override
    public String toString() {
        return "DiscountProduct{" +
                "name='" + super.getName() + '\'' +
                ", price=" + super.getPrice() +
                ", discount=" + discount +
                ", applyDiscount=" + applyDiscount +
                "} ";
    }
}
