package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;

    public TaskController(TaskService taskService, ProjectService projectService, UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createTask(Model model) {

        model.addAttribute("task", new TaskDTO()); //send new object to show new form in the page
        model.addAttribute("projects", projectService.listAllProjects()); //i need to see all projects in the drop down
        model.addAttribute("employees", userService.listAllByRole("employee"));//in the task we have a drop down, we need to assign to this task
        model.addAttribute("tasks", taskService.listAllTasks());// show all tasks

        return "task/create";
    }

    @PostMapping("/create")
    public String insertTask(@ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.listAllProjects());
            model.addAttribute("employees", userService.listAllByRole("employee"));
            model.addAttribute("tasks", taskService.listAllTasks());

            return "/task/create";

        }

        taskService.save(task);

        return "redirect:/task/create";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.delete(taskId);
        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId, Model model) {

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("employees", userService.listAllByRole("employee"));
        model.addAttribute("tasks", taskService.listAllTasks());

        return "task/update";

    }

//    @PostMapping("/update/{taskId}")
//    public String updateTask(@PathVariable("taskId") Long taskId, TaskDTO task) {
//        task.setId(taskId);
//        taskService.update(task);
//        return "redirect:/task/create";
//    }
//
    @PostMapping("/update/{id}")
    public String updateTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.listAllProjects());
            model.addAttribute("employees", userService.listAllByRole("employee"));
            model.addAttribute("tasks", taskService.listAllTasks());

            return "/task/update";

        }

        taskService.update(task);
        return "redirect:/task/create";
    }
//@29:49, in employee pending page, I want to see all tasks, except the ones with status as Completed
    @GetMapping("/employee/pending-tasks")
    public String employeePendingTasks(Model model) {
        model.addAttribute("tasks", taskService.listAllTasksByStatusIsNot(Status.COMPLETE));
        return "task/pending-tasks";
    }

    @GetMapping("/employee/edit/{id}")
    public String employeeEditTask(@PathVariable("id") Long id, Model model) {

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("tasks", taskService.listAllTasksByStatusIsNot(Status.COMPLETE));
        model.addAttribute("statuses", Status.values());

        return "task/status-update";

    }
//we need to remove all the @Valid
    @PostMapping("/employee/update/{id}")
    public String employeeUpdateTask(@ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("tasks", taskService.listAllTasksByStatusIsNot(Status.COMPLETE));
            model.addAttribute("statuses", Status.values());

            return "/task/status-update";

        }
//42:15
        taskService.updateStatus(task);
        return "redirect:/task/employee/pending-tasks";

    }

    @GetMapping("/employee/archive")
    public String employeeArchivedTasks(Model model) {
        model.addAttribute("tasks", taskService.listAllTasksByStatus(Status.COMPLETE));
        return "task/archive";
    }

}
