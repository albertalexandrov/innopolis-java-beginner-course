package ru.innopolis.attestations.attestation01;

public enum UserFieldsLinePositionsEnum {
    ID(0),
    CREATED_AT(1),
    LOGIN(2),
    PASSWORD(3),
    CONFIRM_PASSWORD(4),
    LAST_NAME(5),
    FIRST_NAME(6),
    MIDDLE_NAME(7),
    AGE(8),
    IS_WORKER(9);

    private final int index;

    UserFieldsLinePositionsEnum(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
