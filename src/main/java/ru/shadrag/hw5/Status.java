package ru.shadrag.hw5;

public enum Status {

    NOT_START("Не начата"),

    IN_PROCESS("В процессе"),

    COMPLETED("Завершена");
    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
}
