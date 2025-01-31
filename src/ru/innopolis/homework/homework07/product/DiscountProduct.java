package ru.innopolis.homework.homework07.product;

import java.time.LocalDate;

public class DiscountProduct extends Product {
    private double discount;
    private LocalDate discountValidTill;

    public DiscountProduct(String name, double price, double discount, LocalDate discountValidTill) {
        super(name, price);
        this.discount = discount;
        this.discountValidTill = discountValidTill;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public LocalDate getDiscountValidTill() {
        return discountValidTill;
    }

    public void setDiscountValidTill(LocalDate discountValidTill) {
        this.discountValidTill = discountValidTill;
    }

    @Override
    public double getPrice() {
        if (LocalDate.now().isBefore(discountValidTill)) {
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
                ", discountValidTill=" + discountValidTill +
                "} ";
    }
}
