package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    //why am I injecting 18-24? to be able to access object inside UserService
    private RoleService roleService;
    private UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model) {

        // why we need new UserDTO(), whenever i hit this end point, it will be an empty object
        model.addAttribute("user", new UserDTO());
        // put all the users in the drop-down
        model.addAttribute("roles", roleService.listAllRoles());
        model.addAttribute("users", userService.listAllUsers());

        return "/user/create";
    }
//
//
//    @PostMapping("/create")
//    public String insertUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//
//            model.addAttribute("roles", roleService.findAll());
//            model.addAttribute("users", userService.findAll());
//
//            return "/user/create";
//
//        }
//
//        userService.save(user);
//        return "redirect:/user/create";
//
//    }
//
//    @GetMapping("/update/{username}")
//    public String editUser(@PathVariable("username") String username, Model model) {
//
//        model.addAttribute("user", userService.findById(username));
//        model.addAttribute("roles", roleService.findAll());
//        model.addAttribute("users", userService.findAll());
//
//        return "/user/update";
//
//    }
//
//    @PostMapping("/update")
//    public String updateUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//
//            model.addAttribute("roles", roleService.findAll());
//            model.addAttribute("users", userService.findAll());
//
//            return "/user/update";
//
//        }
//
//        userService.update(user);
//        return "redirect:/user/create";
//
//    }
//
//    @GetMapping("/delete/{username}")
//    public String deleteUser(@PathVariable("username") String username) {
//        userService.deleteById(username);
//        return "redirect:/user/create";
//    }

}
