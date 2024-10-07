package ru.onegin.springcourse.FirstSecurityApp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.onegin.springcourse.FirstSecurityApp.security.PersonDetails;

/**
 * @author onegines
 * @date 07.10.2024
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @RequestMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        System.out.println(personDetails.getPerson());
        return "hello";
    }
}
