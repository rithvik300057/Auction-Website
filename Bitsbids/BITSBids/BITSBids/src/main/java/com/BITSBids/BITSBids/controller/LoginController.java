package com.BITSBids.BITSBids.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class LoginController {

    @GetMapping("/")
    public String home() {
        return "redirect:http://127.0.0.1:5500/BidLoggedOut.html";
    }
    @GetMapping("/oauth2/authorization/google")
    public String redirectToFrontend() {
        return "redirect:http://127.0.0.1:5500/BidLoggedIn.html";
    }
}
