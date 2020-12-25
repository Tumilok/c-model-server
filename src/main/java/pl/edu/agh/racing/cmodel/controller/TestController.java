package pl.edu.agh.racing.cmodel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/newbie")
    public String newbieAccess() {
        return "Newbie Content.";
    }

    @GetMapping("/user")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/moderator")
    public String moderatorAccess() {
        return "Moderator Content.";
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin Content.";
    }
}
