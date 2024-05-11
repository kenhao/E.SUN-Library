package com.example.Spring.boot.test.controller;

import com.example.Spring.boot.test.model.User;
import com.example.Spring.boot.test.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user_login")
    public String loginUser() {
        return "user_login";
    }

    @GetMapping("/register_form")
    public String registerUser() {
        return "user_registration";
    }

    @PostMapping("/register_process")
    public String processRegistration(@RequestParam("phone_number") String phoneNumber,
            @RequestParam("user_name") String userName,
            @RequestParam("password_hash") String password,
            @RequestParam("confirm_password") String confirmPassword,
            RedirectAttributes redirectAttributes) {

        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match.");
            return "redirect:/register_form";
        }

        if (userService.isPhoneNumberExists(phoneNumber)) {
            redirectAttributes.addFlashAttribute("error", "Phone number already exists.");
            return "redirect:/register_form";
        }

        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setUserName(userName);
        user.setPassword(password); // Consider hashing the password
        userService.registerUser(user);

        return "redirect:/user_login?success=registration";
    }

    @PostMapping("/login_process")
    public ModelAndView login(
            @RequestParam("phone_number") String phoneNumber,
            @RequestParam("password_hash") String password,
            HttpSession session) {

        User user = userService.authenticateUser(phoneNumber, password);

        if (user != null) {
            session.setAttribute("userId", user.getUserId()); // 將使用者 ID 存入 session
            session.setAttribute("userName", user.getUserName());
            return new ModelAndView("redirect:/book_inventory");
        } else {
            ModelAndView modelAndView = new ModelAndView("user_login");
            modelAndView.addObject("error", "Invalid phone number or password.");
            return modelAndView;
        }
    }

    // Logout method
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return "redirect:/user_login";
    }
}
