package ru.shadrag.hw5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("/hw5")
public class TasksController {

    private boolean inIndexPage;
    private TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @RequestMapping(value = "/all-tasks", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("buttonStat", Status.ALL_TASKS);
        model.addAttribute("tasks", tasksService.getAllTasks());
        inIndexPage = true;
        return "all-tasks";
    }

    @RequestMapping(value = "/all-tasks/{id}/{status}", method = RequestMethod.GET)
    public String updateStatus(@PathVariable("status") Status status, @PathVariable("id") Long id) {
        Task task = tasksService.getTaskById(id);
        Status sortStatus = Arrays.stream(Status.values()).filter(it -> it.getDescription().equals(task.getStatus())).findFirst().get();
        task.setStatus(status.getDescription());
        task.setDate(new Date());
        tasksService.updateTask(task.getId(), task);
        if (inIndexPage)
            return "redirect:/hw5/all-tasks";
        else
            return "redirect:/hw5/sort-tasks/" + sortStatus;
    }

    @RequestMapping(value = "/update-task/{id}", method = RequestMethod.GET)
    public String updateTask(@PathVariable("id") Long id, Model model) {
        model.addAttribute("task", tasksService.getTaskById(id));
        return "update-task";
    }

    @RequestMapping(value = "/update-task/{id}", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute("task") Task task, @PathVariable("id") Long id, Model model) {
        model.addAttribute("buttonStat", task.getStatus());
        tasksService.updateTask(id, task);
        return "redirect:/hw5/all-tasks";
    }


    @RequestMapping(value = "/create-task", method = RequestMethod.GET)
    public String createTask(Model model) {
        model.addAttribute("task", new Task());
        return "create-task";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String createTask(@ModelAttribute Task task) {
        tasksService.createTask(task);
        return "redirect:/hw5/all-tasks";
    }

    @RequestMapping(value = "/delete-task/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable("id") Long id) {
        tasksService.deleteTask(id);
        return "redirect:/hw5/all-tasks";
    }

    @RequestMapping(value = "/sort-tasks/{status}", method = RequestMethod.GET)
    public String sortTaskByStatus(@PathVariable("status") Status status, Model model) {
        inIndexPage = false;
        model.addAttribute("buttonStat", status);
        model.addAttribute("tasks", tasksService.getAllTasks().stream().filter(it -> it.getStatus().equals(status.getDescription())));
        return "all-tasks";
    }

}
