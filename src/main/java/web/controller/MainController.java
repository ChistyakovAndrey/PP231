package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getMainPage(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        System.out.println("getUsersPage");
        return "index";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user_info";
    }
    @GetMapping("saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/";
    }
    @GetMapping("user_info")
    public String updateUser(@RequestParam("id") Integer id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user_info";
    }
    @GetMapping("delete")
    public String deleteUser(@RequestParam("id") int id){
        userService.deleteUserById(id);
        return "redirect:/";
    }
}
