package ru.shadrag.hw5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TasksServiceImpl implements TasksService {

    private TasksRepository repository;

    @Autowired
    public TasksServiceImpl(TasksRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return repository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task oldTask = getTaskById(id);
        oldTask.setTitle(task.getTitle());
        oldTask.setDescription(task.getDescription());
        oldTask.setDate(task.getDate());
        oldTask.setStatus(task.getStatus());
        return repository.save(oldTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task deletedTask = getTaskById(id);
        repository.delete(deletedTask);
    }
}
