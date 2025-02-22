package ru.innopolis.homework.utils;

public class FormattingNumberInput {
    public static int parseCount(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Невалидное значение");
        }
    }

    public static int validateCount(String value) {
        try {
            return parseCount(value);
        } catch (NumberFormatException e) {
            // todo: условия 3д и 3е задания неясны
            return -999; // чтобы интепретатор не ругался
        }
    }

    public static double parseNumber(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Невалидное значение");
        }
    }

    public static double validateNumber(String value) {
        try {
            return parseCount(value);
        } catch (NumberFormatException e) {
            // todo: аналогично с методом validateCount
            return -999; // чтобы интепретатор не ругался
        }
    }

}
