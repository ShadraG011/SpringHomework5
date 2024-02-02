package ru.shadrag.hw5;

import java.util.List;

public interface TasksService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task createTask(Task note);
    Task updateTask(Long id, Task note);
    void deleteTask(Long id);
}
