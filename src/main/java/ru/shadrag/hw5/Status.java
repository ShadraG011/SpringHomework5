package ru.shadrag.hw5;


import java.util.ArrayList;
import java.util.List;

public enum Status {

    ALL_TASKS ("Все заметки"),

    NOT_START("Не начата"),

    IN_PROCESS("В процессе"),

    COMPLETED("Завершена");
    private String description;

    private static List<Status> tasksStatusesList = new ArrayList<>();

    static {
        tasksStatusesList.add(Status.values()[1]);
        tasksStatusesList.add(Status.values()[2]);
        tasksStatusesList.add(Status.values()[3]);
    }

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static List<Status>  getTasksStatusesList() {
        return tasksStatusesList;
    }
}
